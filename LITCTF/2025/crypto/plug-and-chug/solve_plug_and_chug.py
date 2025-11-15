import math

from pwn import remote

r = remote("litctf.org", 31789)
x = math.ceil(996491788296388609 ** 0.5)
a = []
for i in range(1, 11):
    r.recvuntil(b"Gimme a number (or type 'guess' to guess): ")
    r.sendline(str(x * i).encode())
    a.append(int(r.recvline()))

diff = [a[i] - a[i - 1] * a[0] for i in range(1, 10)]
n = math.gcd(*diff)

print(a)
print(diff)
print(n)

r.sendline(str(n).encode())
print(r.recvall(timeout=1))
