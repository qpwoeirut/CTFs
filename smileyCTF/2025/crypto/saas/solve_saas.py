import math

from pwn import remote


e = 0x10001


def main():
    r = remote("smiley.cat", 46177)

    vals = set()
    while len(vals) < 4:
        r.sendline(b'1')
        num = int(r.recvline().lstrip(b'>>> '))
        vals.add(num)

    vals = list(sorted(vals))
    assert vals[0] == 1

    _, a, b, n = vals
    n += 1

    p = math.gcd(a + 1, n)
    q = n // p

    assert p * q == n
    d = pow(e, -1, (p - 1) * (q - 1))

    r.sendline(b'next')

    m = int(r.recvline().lstrip(b'>>> m = '))
    s = pow(m, d, n)
    r.sendline(str(s).encode())

    print(r.recvall(timeout=1))


if __name__ == '__main__':
    main()
