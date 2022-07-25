import itertools
import logging
from math import isqrt
from pwn import remote, process, context
from sympy import isprime
from sympy.ntheory.modular import crt


context.log_level = logging.WARNING

TEST_FLAG = int(b'LITCTF{w3_4r3_l1k3_saaaaaaaaaaaaaaaaaaa}'.hex(),16)


# https://math.stackexchange.com/questions/771997/help-with-proving-that-ap-equiv-a-mod-p-does-not-mean-that-a-is-diagonal
# https://math.stackexchange.com/questions/2310831/matrix-analogue-of-fermats-little-theorem
# https://math.stackexchange.com/questions/241764/eigenvalues-and-power-of-a-matrix
# https://oeis.org/A005574


def binsearch():
    return 797816480923169762331293935901407264148150406427
    # lo = 1 << 140
    # hi = 1 << 200
    # while lo < hi:
    #     mid = (lo + hi + 1) // 2
    #     r = remote("litctf.live", 31782)
    #     r = process(["python3", "susss_edited.py"], stderr=None)
    #     r.recvline()
    #     r.sendline(str(mid).encode())
    #     try:
    #         r.recvline()
    #         lo = mid
    #     except EOFError:
    #         hi = mid - 1
    #     r.close()
    #     print(mid)
    # return lo


def generate_k(max_mod: int, ct: int) -> list[int]:
    k = isqrt(max_mod)
    results = []
    while len(results) < ct:
        if isprime(k*k + 1):
            results.append(k)
        k -= 1
    return results


def run(k: int) -> int:
    # r = process(["python3", "susss_edited.py"], stderr=None)
    r = remote("litctf.live", 31782)

    p = k*k + 1

    line = r.recvline().decode().strip()
    # print(line)

    line = r.recv().decode().strip()
    # print(line)

    r.sendline(str(p).encode())
    # print(p)

    line = r.recvline().decode().strip()
    # print(line)

    rand_p = int(line.split()[-1])
    # print(rand_p)

    try:
        pw = pow(rand_p, -1, p - 1)
        # print(rand_p * pw % p)
    except ValueError:
        r.close()
        return run(k)

    results = []
    for g in [1, -1, k, -k]:
        line = r.recv().decode().strip()
        # print(line, pw)

        r.sendline(str(pw).encode())

        line = r.recv().decode().strip()
        # print(line, g % p)

        r.sendline(str(g % p).encode())

        line = r.recvline().decode().strip()
        # print(line)
        result = int(line.split()[-1])
        results.append(result)

    A, B, C, D = results

    trace_multiple = k * (A - B) + C - D
    trace = (trace_multiple * pow(4 * k, -1, p)) % p

    print(trace)
    # print(TEST_FLAG % p)
    # print()

    r.close()

    return trace


def main():
    # binsearch to find max possible mod allowed (which also reveals first part of flag)
    max_mod = binsearch()
    print(max_mod)

    # generate the k for 3 primes of form k^2 + 1
    k_list = generate_k(max_mod, 3)
    print(k_list)
    p_list = [k*k + 1 for k in k_list]

    # find flag mod p, where p=k^2+1
    # this doesn't get the right answer most of the time, but it at least gets the right one sometimes
    possible = dict()
    for k in k_list:
        possible[k] = set()
        for _ in range(20):
            possible[k].add(run(k))
        print("k, len:", k, len(possible[k]))

    # for each possibility, try it. up to 20^3 combinations total
    for m1, m2, m3 in itertools.product(*[possible[k] for k in k_list]):
        m = [m1, m2, m3]
        val, mod = crt(p_list, m, check=False)
        val = int(val)
        flag = val.to_bytes((val.bit_length() + 7) // 8, 'big')
        if b"LIT" in flag:
            print(flag)
        flag = val.to_bytes((val.bit_length() + 7) // 8, 'little')
        if b"LIT" in flag:
            print(flag)


if __name__ == '__main__':
    main()
