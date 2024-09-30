import random
import time

from pwn import remote


def main():
    while time.time() - int(time.time()) > 0.001:
        time.sleep(0.0001)

    r = remote("challs.pwnoh.io", 13421)
    seed = int(time.time())
    random.seed(seed)

    assert r.recvline() == b"Welcome to zkwarmup!\n"

    n = int(r.recvline(keepends=False).decode().split(' = ')[1])
    y = int(r.recvline(keepends=False).decode().split(' = ')[1])

    x = random.randrange(1, n)
    assert pow(x, 2, n) == y
    assert r.recvline() == b"Can you prove that you know sqrt(y) without revealing it to the verifier?\n"

    for i in range(128):
        print(f"{i = }")
        r.recvuntil(b"Provide s: ")

        s = (i + 1) ** 2
        r.sendline(str(s).encode())
        b = int(r.recvline(keepends=False).decode().split(' = ')[1])

        print(f"{b = }")

        r.recvuntil(b"Provide z: ")

        if b == 1:
            z = i + 1
        else:
            z = (i + 1) * x
        r.sendline(str(z).encode())

        print(r.recvline(keepends=False).decode())

    print(r.recvall(timeout=1).decode())


if __name__ == '__main__':
    main()
