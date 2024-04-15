from Crypto.Cipher import AES
from pwn import remote


def check_nonce_repeat():
    key = b'\x00' * 16
    ctr_cipher1 = AES.new(key, AES.MODE_CTR, nonce=b'\x01')
    ctr_cipher2 = AES.new(key, AES.MODE_CTR, nonce=b'\x01\x00')
    assert ctr_cipher1.encrypt(b'\x00' * 16) == ctr_cipher2.encrypt(b'\x00' * 16)


def main():
    check_nonce_repeat()

    r = remote('gold.b01le.rs', 5002)
    r.recvline()

    enc = bytes.fromhex(r.recvline().decode())
    print(enc)

    for i in range(256):
        r.recvuntil(b'Give me something to encrypt: ')
        r.sendline(b'00' * len(enc))
        cur = bytes.fromhex(r.recvline().decode())

        print(i, ''.join([chr(x ^ y) for x, y in zip(enc, cur)]))


if __name__ == '__main__':
    main()
