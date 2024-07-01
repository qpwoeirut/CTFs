from pwn import remote


r = remote("snore-signatures.chal.uiuc.tf", 1337, ssl=True)
print("Connected!")

p = int(r.recvline().decode().split('=')[1].strip())
q = int(r.recvline().decode().split('=')[1].strip())
g = int(r.recvline().decode().split('=')[1].strip())

for i in range(10):
    y = int(r.recvline().decode().split('=')[1].strip())

    assert r.recvline() == b'you get one query to the oracle\n'

    resp = r.recv(timeout=1)
    assert resp == b'm = ', resp
    print("m1 =", i)
    r.sendline(str(i).encode())

    s = int(r.recvline().decode().split('=')[1].strip())
    e = int(r.recvline().decode().split('=')[1].strip())

    assert r.recvline() == b'can you forge a signature?\n'

    resp = r.recv(timeout=1)
    assert resp == b'm = ', resp
    r.sendline(str(i + p).encode())
    print("m1 =", i + p)

    resp = r.recv(timeout=1)
    assert resp == b's = ', resp
    r.sendline(str(s))

    assert r.recvline() == b'correct signature!\n'

print(r.recvall(timeout=3))
