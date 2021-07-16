from itertools import combinations
from math import prod

from sympy import factorint

class Generator():
    DIGITS = 8
    def __init__(self, seed):
        self.seed = seed

    def getNum(self):
        self.seed = int(str(self.seed**2).rjust(self.DIGITS*2, "0")[self.DIGITS//2:self.DIGITS + self.DIGITS//2])
        return self.seed


out1 = 4858011338006160
out2 = 157633475398496
out3 = 1537253420391990

factor_dict = factorint(out1)
factors = []
for factor, count in factor_dict.items():
    factors.extend([factor] * count)

for ct in range(len(factors)):
    for tup in combinations(factors, ct+1):
        seed1 = prod(tup)
        seed2 = out1 // seed1

        gen1 = Generator(seed1)
        gen2 = Generator(seed2)
        if gen1.getNum() * gen2.getNum() == out2:
            assert gen1.getNum() * gen2.getNum() == out3

            print(gen1.getNum() * gen2.getNum())
            print(gen1.getNum() * gen2.getNum())

            exit()