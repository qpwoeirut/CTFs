from itertools import combinations
from math import prod
from queue import PriorityQueue

from Crypto.Util.number import isPrime
from sympy import discrete_log, mod_inverse
from sympy.ntheory.modular import crt


# https://stackoverflow.com/questions/77459155/is-there-a-way-to-find-a-carmichael-number-having-n-prime-factors-in-a-given-ran
def gen_5_smooth():
    primes = [2, 3, 5]
    queue = PriorityQueue()

    for i, p in enumerate(primes):
        queue.put((p, i))

    yield 1
    while True:
        x, i = queue.get()
        yield x
        for j, q in enumerate(primes[i:], i):
            queue.put((q * x, j))


def solve():
    mult_lower = 10 ** 6 + 10 ** 5
    mult_upper = 10 ** 7

    smooth_factors = []
    for fact in gen_5_smooth():
        if fact > 500:
            break
        smooth_factors.append(fact)

    for mult in range(mult_lower, mult_upper):
        primes = [fact * mult + 1 for fact in smooth_factors if isPrime(fact * mult + 1)]

        for comb in combinations(primes, 20):
            product = prod(comb)
            if all((product - 1) % (x - 1) == 0 for x in comb):
                yield comb


# from sympy
def discrete_log_pohlig_hellman(n, a, b, f, order):
    l = [0] * len(f)

    for i, (pi, ri) in enumerate(f.items()):
        for j in range(ri):
            gj = pow(b, l[i], n)
            aj = pow(a * mod_inverse(gj, n), order // pi ** (j + 1), n)
            bj = pow(b, order // pi, n)
            cj = discrete_log(n, aj, bj, pi, True)
            l[i] += cj * pi ** j

    d, _ = crt([pi ** ri for pi, ri in f.items()], l)
    return d


def main():
    # gen = solve()
    # while True:
    #     factors = next(gen)
    #     c = prod(factors)
    #     print(factors)
    #     print(c.bit_length())
    #     if c.bit_length() > 512:
    #         break
    factors = (3305611, 13222441, 16528051, 35259841, 44074801, 59500981, 70519681, 82640251, 89251471, 99168301, 105779521, 148752451, 158669281, 198336601, 220374001, 238003921, 267754411, 396673201, 495841501, 528897601)
    c = prod(factors)
    print(c)

    # this is all wrong, ended up using alpertron to solve the discrete log part
    # r = remote("groups.chal.uiuc.tf", 1337, ssl=True)
    # assert r.recv(timeout=0.1) == b'c = '
    # r.sendline(str(c).encode())
    #
    # a = int(r.recvline().decode().split('=')[1].strip())
    # b = int(r.recvline().decode().split()[2].strip())
    # print(a)
    # print(b)
    # print(r.recvall(timeout=1))
    #
    # result = discrete_log_pohlig_hellman(c, a, b, {fact: 1 for fact in factors})
    # print(result)


if __name__ == '__main__':
    main()
