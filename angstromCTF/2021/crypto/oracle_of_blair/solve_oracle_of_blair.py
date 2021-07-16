from binascii import hexlify, unhexlify
from pwn import remote
from utils.flag_strings import alpha_flag_chars

CHARS = alpha_flag_chars

r = remote("crypto.2021.chall.actf.co", 21112)


def decrypt(enc_bytes: bytes) -> bytes:
    r.recvuntil('give input: ')
    r.sendline(hexlify(enc_bytes))

    return unhexlify(r.recvline().strip())


def main():
    cur = b'\x00' * 64
    flag = b""
    while True:
        print(cur)
        cur = cur[1:]
        dec_flag = decrypt(cur.rstrip(flag) + b'{}')
        if decrypt(cur)[16:] == dec_flag[16:]:
            break
        for c in CHARS:
            if dec_flag[16:].startswith(decrypt(cur + c.encode())[16:]):
                cur += c.encode()
                flag += c.encode()
                break
        assert len(cur) == 64

    print("FLAG:", flag.decode())


if __name__ == '__main__':
    main()

# actf{cbc_more_like_ecb_c}
