import base64
import time
from hashlib import md5

from pwn import process, remote

xor = lambda a, b: bytes([x ^ y for x, y in zip(a, b)])

# r = process(["python3", "server.py"])
r = remote("crypto.2023.cakectf.com", 11111)


def send(b: bytes):
    r.sendline(b)
    print(b.decode())


def register(username: bytes) -> bytes:
    time.sleep(0.001)  # just in case

    # print(r.recvline(keepends=False).decode())
    # print(r.recv().decode(), end='')
    send(b'1')
    # print(r.recv().decode(), end='')
    send(base64.b64encode(username))
    # print(r.recvuntil(b"your cookie => ").decode(), end='')
    r.recvuntil(b"your cookie => ")
    cookie = r.recvline().decode().strip()
    # print(cookie)
    return base64.b64decode(cookie)


def find_prefix_length() -> int:
    empty1 = register(b'')[16:]
    empty2 = register(b'')[16:]
    blocks = 0
    for i in range(1, len(empty1) // 16):
        if empty1[:16 * i] == empty2[:16 * i]:
            blocks = i
        else:
            break
    for i in range(1, 128):
        cur1 = register(b"a" * i + b'a')[16:]
        cur2 = register(b"a" * i + b'b')[16:]
        if cur1[:16 * blocks] == cur2[:16 * blocks]:
            return (16 * blocks) - len(b'|user=' + b'a' * i)
    raise ValueError()


def main():
    prefix_length = find_prefix_length()
    assert prefix_length == 17

    for prefix_end in range(256):
        print(f"prefix_end = {prefix_end}")

        block1 = b'a' * 9
        assert (prefix_length + len(b'|user=' + block1)) % 16 == 0, prefix_length + len(b'|user=' + block1)
        initial = register(block1)

        target_block = bytes([prefix_end]) + b'|user=root|2023'
        assert len(target_block) == 16, len(target_block)

        c1 = md5(initial[16:32]).digest()
        c2 = md5(initial[32:48]).digest()

        block2 = xor(target_block, xor(c1, c2))
        extended = register(block1 + block2)
        assert len(initial) + 16 == len(extended)

        print([initial[i:i + 16] for i in range(0, len(initial), 16)])
        print([extended[i:i + 16] for i in range(0, len(extended), 16)])

        root = extended[:32] + extended[48:]

        r.sendline(b'2')
        r.recvuntil(b"cookie: ")
        r.sendline(base64.b64encode(root))
        resp = r.recvline().decode().strip()
        # print(resp)
        if "Authentication unsuccessful" not in resp:
            print(r.recvall(timeout=1))
            break

    r.close()


if __name__ == '__main__':
    main()
