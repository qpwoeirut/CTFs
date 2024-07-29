import ast
from pwn import remote


r = remote("be.ax", 31105)
assert r.recvline() == b"I caught a monkfish in the sea! \n"

m0 = ast.literal_eval(r.recvline().decode().removeprefix("m0 = "))
m1 = ast.literal_eval(r.recvline().decode().removeprefix("m1 = "))
m2 = ast.literal_eval(r.recvline().decode().removeprefix("m2 = "))

# taking advantage of python's negative indexes
m0[0] -= 5
m1[0] -= 5
m2[0] -= 5
r.sendline(str(m0).encode())
r.sendline(str(m1).encode())
r.sendline(str(m2).encode())

print(r.recvall(timeout=3).decode())
