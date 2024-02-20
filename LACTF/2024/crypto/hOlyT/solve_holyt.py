from pwn import remote, process

r = process(["python3", "server.py"])

ct = int(r.recvline().strip().decode().split(' = ')[1])
N = int(r.recvline().strip().decode().split(' = ')[1])
e = int(r.recvline().strip().decode().split(' = ')[1])

assert e == 65537

values = set()
for _ in range(25):
    r.recvuntil(b"What do you want to ask? >")
    r.sendline(b'1')
    n = int(r.recvline().strip().decode())
    values.add(n)

values.remove(1)
values.remove(N - 1)

assert len(values) == 2
s, t = values
am = ((s + 1) * pow(2, -1, N)) % N
bn = ((t + 1) * pow(2, -1, N)) % N

assert (am + bn) % N == 1

# teammate solved
