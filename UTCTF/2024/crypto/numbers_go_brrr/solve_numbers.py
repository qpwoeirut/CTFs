from Crypto.Cipher import AES
from pwn import remote


def decrypt(message, seed):
    key = b''
    for i in range(8):
        seed = int(str(seed * seed).zfill(12)[3:9])
        key += (seed % (2 ** 16)).to_bytes(2, 'big')
    cipher = AES.new(key, AES.MODE_ECB)
    return cipher.decrypt(message)


def main():
    r = remote("betta.utctf.live", 7356)
    r.sendline(b'1')
    r.recvuntil(b'Here is the encrypted flag: ')
    enc = bytes.fromhex(r.recvall().decode().strip())

    for i in range(1000000):
        dec = decrypt(enc, i)
        if dec.isascii():
            print(dec)


if __name__ == '__main__':
    main()
