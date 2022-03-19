import hashlib
import sys
from sympy.matrices import Matrix

ITERS = int(2e7)
VERIF_KEY = "96cc5f3b460732b442814fd33cf8537c"
ENCRYPTED_FLAG = bytes.fromhex("42cbbce1487b443de1acf4834baed794f4bbd0dfb5df5e6f2ad8a2c32b")


def m_func(i):
    if i == 0: return 1
    if i == 1: return 2
    if i == 2: return 3
    if i == 3: return 4

    return 55692 * m_func(i - 4) - 9549 * m_func(i - 3) + 301 * m_func(i - 2) + 21 * m_func(i - 1)


def decrypt_flag(sol):
    sol = sol % (10 ** 10000)
    sol = str(sol)
    sol_md5 = hashlib.md5(sol.encode()).hexdigest()

    if sol_md5 != VERIF_KEY:
        print("Incorrect solution")
        sys.exit(1)

    key = hashlib.sha256(sol.encode()).digest()
    flag = bytearray([char ^ key[i] for i, char in enumerate(ENCRYPTED_FLAG)]).decode()

    print(flag)


def main():
    for i in range(10):
        print(i, m_func(i))
    A = Matrix([4, 3, 2, 1])

    T = Matrix([
        [21, 301, -9549, 55692],
        [1, 0, 0, 0],
        [0, 1, 0, 0],
        [0, 0, 1, 0],
    ])

    X = 100
    MOD = 10 ** 10000
    print(T * A)
    T = T**(ITERS // X) % MOD
    print("finished t1")
    T = T**X % MOD
    print()
    out = T * A
    sol = out[3]
    decrypt_flag(sol)


if __name__ == "__main__":
    main()
