# 2^m = 2^(aw - bv) (mod p)
# m = aw - bv (mod p-1)
from hashlib import sha512

from pwn import process, remote
from sympy import symbols, diophantine

# r = process(["python3", "server.py"])
r = remote("crypto.2023.cakectf.com", 10444)
p = int(r.recvline().split()[2])
g = int(r.recvline().split()[2])
vkey = tuple(map(lambda x: int(x.strip(b'(,)')), r.recvline().split()[2:]))
w, v = vkey

print(p)
print(g)
print(vkey)

magic_word = "cake_does_not_eat_cat"

r.recvuntil(b"[S]ign, [V]erify: ")
r.sendline(b'V')
r.sendline(magic_word.encode())

m = int(sha512(magic_word.encode()).hexdigest(), 16)

a, b = symbols("a b", integer=True, positive=True)
[solution] = diophantine(a * w - b * v - m)
[t_0] = solution[0].free_symbols
a = int(solution[0].subs({t_0: 0})) % (p - 1)
b = int(solution[1].subs({t_0: 0})) % (p - 1)

r.recvuntil(b"s: ")
r.sendline(str(pow(g, a, p)).encode())
r.recvuntil(b"t: ")
r.sendline(str(pow(g, b, p)).encode())

print(r.recvall(timeout=1))
