from pwn import remote, process

r = remote("tjc.tf", 31996)
# r = process(["python3", "server.py"])

line = r.recvline(keepends=False).decode()
print(line)
n = int(line.split()[5].strip('(').strip(','))
s = n * n

line = r.recvline(keepends=False).decode()
print(line)
e4 = int(line.split()[2])

m = int.from_bytes(b"Please give me the flag", "big")
k = (pow(4, -1, n) * m) % n
c = pow(e4, k, s)

r.sendline(str(c).encode())

print(r.recvall().decode())
