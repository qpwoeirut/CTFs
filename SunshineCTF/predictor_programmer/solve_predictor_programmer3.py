from math import gcd
from pwn import remote
from random import randint
from sympy.matrices import ImmutableMatrix
from sympy import randprime

secret_m = randprime(1 << 31, 1 << 32)
secret_a = randint(1 << 30, secret_m - 1)
secret_b = randint(1 << 30, secret_m - 1)
secret_seed = randint(1 << 30, secret_m - 1)
# secret_m = (1 << 31) - 1
# secret_a = 16807
# secret_b = 78125
# secret_seed = 207560540


def lcg(state, a, b, m) -> int:
    return (a * state + b) % m


def reverse_lcg(state, a, b, m) -> int:
    return ((state - b) * pow(a, -1, m)) % m


def solve_lcg(numbers: list[int]) -> tuple[int, int, int]:
    matrices = [ImmutableMatrix([
        [numbers[i], numbers[i+1], 1],
        [numbers[i+1], numbers[i+2], 1],
        [numbers[i+2], numbers[i+3], 1]
    ]) for i in range(len(numbers) - 3)]
    determinants = [matrix.det() for matrix in matrices]
    m = gcd(*determinants)

    a = ((numbers[3] - numbers[2]) * pow(numbers[2] - numbers[1], -1, m)) % m
    b = (numbers[2] - a * numbers[1]) % m
    print((a * numbers[1] + b) % m)
    return a, b, m


def test():
    numbers = [secret_seed]
    while len(numbers) < 8:
        numbers.append(lcg(numbers[-1], secret_a, secret_b, secret_m))

    a, b, m = solve_lcg(numbers)
    print("secret a,b,m:", secret_a, secret_b, secret_m)
    print("found  a,b,m:", a, b, m)
    print(numbers)
    s = numbers[-1]
    for i in range(len(numbers)):
        s = reverse_lcg(s, a, b, m)
        print(s)


def main():
    r = remote("predictor.sunshinectf.games", 22203)
    r.recvuntil(b"the future...")
    r.recvline()
    numbers = [int(r.recvline().decode().split()[4].strip('.')) for _ in range(6)]
    a, b, m = solve_lcg(numbers)
    seed = reverse_lcg(numbers[0], a, b, m)
    r.sendline(str(seed))
    print(r.recvall().decode().strip())

if __name__ == '__main__':
    main()