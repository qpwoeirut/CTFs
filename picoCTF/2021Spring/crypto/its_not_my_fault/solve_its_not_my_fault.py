from hashlib import md5
from pwn import remote


def solve_PoW(prefix, tail):
    x = 0
    while True:
        if md5((prefix + str(x)).encode()).hexdigest().endswith(tail):
            return prefix + str(x)
        x += 1


# r = remote("mercury.picoctf.net", 48006)
r = remote("mercury.picoctf.net", 2185)

PoW = r.recvline().strip().decode()
print("solving PoW...")
r.sendline(solve_PoW(PoW[33:38], PoW[-6:]))
print("solved PoW")

n = int(r.recvline().split(b" : ")[1].decode())
clue = int(r.recvline().split(b" : ")[1].decode())

print("n =", n)
print("clue =", clue)

r.interactive()
