import random

from pwn import remote, process
from time import sleep, time


def xor(a: bytearray, b: bytearray) -> bytearray:
    assert len(a) == len(b)
    return bytearray([a[i] ^ b[i] for i in range(len(a))])


def main():
    while int(time()) % 10 >= 2:
        sleep(0.1)
    seed = int(time() // 10)  # cast is redundant, copied from server code

    random.seed(seed)
    padding = b''
    while len(padding) < 16:
        b = bytes([random.randrange(256)])
        if b not in padding:
            padding += b
    padding = bytearray(padding)
    print(padding)

    r = remote("tjc.tf", 31566)
    # r = process(["python3", "server.py"])

    print(r.recvuntil(b"secret here: ").decode())
    enc_line = r.recvline(keepends=False).decode()

    iv = bytearray.fromhex(enc_line[:32])
    enc = bytearray.fromhex(enc_line[32:])

    assert len(enc) % 16 == 0, len(enc)

    blocks = [enc[i:i+16] for i in range(0, len(enc), 16)]
    print(padding)
    print(iv)
    print(enc)
    print(blocks)

    previous_blocks = bytearray()
    c_1 = iv
    plaintext = bytearray()

    def padding_ok(query: bytearray) -> bool:
        r.recvuntil(b"Ciphertext: ")
        r.sendline(query.hex().encode())
        resp = r.recvline(keepends=False).decode().strip()
        assert "ok" in resp or "error!!!" in resp, resp
        return "ok" in resp

    while blocks:
        c_2 = blocks.pop(0)  # O(n) is fine, there aren't that many blocks
        p_2 = bytearray(16)

        print(len(blocks))
        for i in reversed(range(16)):
            print("i =", i)
            c_m1 = c_1[:i] + bytearray(1) + xor(padding[1:16 - i], p_2[i+1:])
            for value in range(256):
                c_m1[i] = value
                query = previous_blocks + c_m1 + c_2
                if padding_ok(query):
                    p_2[i] = value ^ padding[0]
                    break
            else:
                raise ValueError("No value found")

        plaintext += p_2

        previous_blocks += c_1
        c_1 = c_2.copy()

    print()
    print(plaintext)
    print(enc)
    print(iv)

    print(xor(plaintext[:16], iv))
    print(xor(plaintext[16:32], xor(plaintext[:16], iv)))


if __name__ == '__main__':
    main()