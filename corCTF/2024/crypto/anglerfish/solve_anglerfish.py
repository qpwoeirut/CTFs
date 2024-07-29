import ast
from pwn import remote


m = 100


r = remote("be.ax", 31106)
assert r.recvline() == b"I caught an anglerfish in the sea! \n"

m0 = ast.literal_eval(r.recvline().decode().removeprefix("m0 = "))
m1 = ast.literal_eval(r.recvline().decode().removeprefix("m1 = "))
m2 = ast.literal_eval(r.recvline().decode().removeprefix("m2 = "))

for x in range(64):
    print(x)
    m0_i = [a for a in m0]
    m1_i = [a for a in m1]
    m2_i = [a for a in m2]

    # taking advantage of python's negative indexes
    for i in range(m):
        if ((x + 1) >> i) & 1:
            m0_i[i] -= 5
            m1_i[i] -= 5
            m2_i[i] -= 5
    r.sendline(str(m0_i).encode())
    r.sendline(str(m1_i).encode())
    r.sendline(str(m2_i).encode())

print(r.recvall(timeout=3).decode())
