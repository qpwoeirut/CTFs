# https://github.com/li-ch/mind/blob/master/scripts/MDP.py


import numpy as np


class MDP:
    '''A simple MDP class.  It includes the following members'''

    def __init__(self, T, R):
        '''Constructor for the MDP class

        Inputs:
        T -- Transition function: |A| x |S| x |S'| array
        R -- Reward function: |A| x |S| array
        discount -- discount factor: scalar in [0,1)

        The constructor verifies that the inputs are valid and sets
        corresponding variables in a MDP object'''

        assert T.ndim == 3, "Invalid transition function: it should have 3 dimensions"
        self.nActions = T.shape[0]
        self.nStates = T.shape[1]
        assert T.shape == (
        self.nActions, self.nStates, self.nStates), "Invalid transition function: it has dimensionality " + repr(
            T.shape) + ", but it should be (nActions,nStates,nStates)"
        assert (abs(T.sum(
            2) - 1) < 1e-5).all(), "Invalid transition function: some transition probability does not equal 1"
        self.T = T
        assert R.ndim == 2, "Invalid reward function: it should have 2 dimensions"
        assert R.shape == (self.nActions, self.nStates), "Invalid reward function: it has dimensionality " + repr(
            R.shape) + ", but it should be (nActions,nStates)"
        self.R = R
        self.discount = 0.99

    def valueIteration(self, initialV, nIterations=np.inf, tolerance=0.01):
        '''Value iteration procedure
        V <-- max_a R^a + gamma T^a V

        Inputs:
        initialV -- Initial value function: array of |S| entries
        nIterations -- limit on the # of iterations: scalar (default: infinity)
        tolerance -- threshold on ||V^n-V^n+1||_inf: scalar (default: 0.01)

        Outputs:
        V -- Value function: array of |S| entries
        iterId -- # of iterations performed: scalar
        epsilon -- ||V^n-V^n+1||_inf: scalar'''

        V = initialV
        iterId = 0
        while iterId < nIterations:
            Q = self.R + self.discount * np.dot(self.T, V)
            newV = Q.max(0)
            epsilon = np.linalg.norm(newV - V, np.inf)
            V = newV
            iterId += 1
            if epsilon <= tolerance: break
        return [V, iterId, epsilon]

    def extractPolicy(self, V):
        '''Procedure to extract a policy from a value function
        pi <-- argmax_a R^a + gamma T^a V

        Inputs:
        V -- Value function: array of |S| entries

        Output:
        policy -- Policy: array of |S| entries'''

        Q = self.R + self.discount * np.dot(self.T, V)
        policy = Q.argmax(0)
        return policy

    def evaluatePolicy(self, policy):
        '''Evaluate a policy by solving a system of linear equations
        V^pi = R^pi + gamma T^pi V^pi

        Input:
        policy -- Policy: array of |S| entries

        Ouput:
        V -- Value function: array of |S| entries'''

        T = self.T[policy, np.arange(self.nStates), :]
        R = self.R[policy, np.arange(self.nStates)]
        A = np.identity(self.nStates) - self.discount * T
        V = np.linalg.solve(A, R)
        return V

    def policyIteration(self, initialPolicy, nIterations=np.inf):
        '''Policy iteration procedure: alternate between policy
        evaluation (solve V^pi = R^pi + gamma T^pi V^pi) and policy
        improvement (pi <-- argmax_a R^a + gamma T^a V^pi).

        Inputs:
        initialPolicy -- Initial policy: array of |S| entries
        nIterations -- limit on # of iterations: scalar (default: inf)

        Outputs:
        policy -- Policy: array of |S| entries
        V -- Value function: array of |S| entries
        iterId -- # of iterations peformed by modified policy iteration: scalar
        epsilon -- ||V^n-V^n+1||_inf: scalar'''

        policy = initialPolicy
        V = self.evaluatePolicy(policy)
        iterId = 0
        while iterId < nIterations:
            newPolicy = self.extractPolicy(V)
            iterId += 1
            if (newPolicy == policy).all(): break
            policy = newPolicy
            V = self.evaluatePolicy(policy)
        return [policy, V, iterId]

    def evaluatePolicyPartially(self, policy, initialV, nIterations=np.inf, tolerance=0.01):
        '''Partial policy evaluation:
        Repeat V^pi <-- R^pi + gamma T^pi V^pi

        Inputs:
        policy -- Policy: array of |S| entries
        initialV -- Initial value function: array of |S| entries
        nIterations -- limit on the # of iterations: scalar (default: infinity)
        tolerance -- threshold on ||V^n-V^n+1||_inf: scalar (default: 0.01)

        Outputs:
        V -- Value function: array of |S| entries
        iterId -- # of iterations performed: scalar
        epsilon -- ||V^n-V^n+1||_inf: scalar'''

        V = initialV
        T = self.T[policy, np.arange(self.nStates), :]
        R = self.R[policy, np.arange(self.nStates)]
        iterId = 0
        while iterId < nIterations:
            newV = R + self.discount * np.dot(T, V)
            epsilon = np.linalg.norm(newV - V, np.inf)
            V = newV
            iterId += 1
            if epsilon <= tolerance: break
        return [V, iterId, epsilon]

    def modifiedPolicyIteration(self, initialPolicy, initialV, nEvalIterations=5, nIterations=np.inf, tolerance=0.01):
        '''Modified policy iteration procedure: alternate between
        partial policy evaluation (repeat a few times V^pi <-- R^pi + gamma T^pi V^pi)
        and policy improvement (pi <-- argmax_a R^a + gamma T^a V^pi)

        Inputs:
        initialPolicy -- Initial policy: array of |S| entries
        initialV -- Initial value function: array of |S| entries
        nEvalIterations -- limit on # of iterations to be performed in each partial policy evaluation: scalar (default: 5)
        nIterations -- limit on # of iterations to be performed in modified policy iteration: scalar (default: inf)
        tolerance -- threshold on ||V^n-V^n+1||_inf: scalar (default: 0.01)

        Outputs:
        policy -- Policy: array of |S| entries
        V -- Value function: array of |S| entries
        iterId -- # of iterations peformed by modified policy iteration: scalar
        epsilon -- ||V^n-V^n+1||_inf: scalar'''

        policy = initialPolicy
        V = initialV
        iterId = 0
        while iterId < nIterations:
            [V, _, _] = self.evaluatePolicyPartially(policy, V, nIterations=nEvalIterations)
            newPolicy = self.extractPolicy(V)
            [newV, _, _] = self.valueIteration(initialV=V, nIterations=1)
            epsilon = np.linalg.norm(newV - V, np.inf)
            policy = newPolicy
            V = newV
            iterId += 1
            if epsilon < tolerance: break
        return [policy, V, iterId, epsilon]


def main():
    nActions = 4  # the actions are the integers 0 to 3 inclusive
    nStates = 65  # the states are the integers 0 to 64 inclusive
    T = np.load("T.npy")
    R = np.load("R.npy")

    for i in range(nActions):
        R[i] -= 1e-10 * i  # prioritize lower numbered actions

    mdp = MDP(T, R)

    policy = [0 for _ in range(nStates)]
    for _ in range(10):
        policy, V, iterId = mdp.policyIteration(policy, nIterations=10)
        print(*list(policy), sep='')
    print(len(policy))

    for r in range(8):
        for c in range(8):
            print(round(R[policy[8 * r + c]][8 * r + c], ndigits=2), end = ' ')
        print()
    print(R[0][64])


if __name__ == '__main__':
    main()

# enter policy WITHOUT SPACES: 20111202200002332010020011000200000011122011000220000302110000000
# TUCFT{Congr@ts_onthe@PP!3_pi}
# yes the flag prefix is misspelled but it works
