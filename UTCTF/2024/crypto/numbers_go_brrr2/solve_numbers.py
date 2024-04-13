from Crypto.Cipher import AES
from Crypto.Util.Padding import pad

def decrypt(message, seed):
    key = b''
    for i in range(8):
        seed = int(str(seed * seed).zfill(12)[3:9])
        key += (seed % (2 ** 16)).to_bytes(2, 'big')
    cipher = AES.new(key, AES.MODE_ECB)
    return cipher.decrypt(message)


def encrypt(message, seed):
    key = b''
    for i in range(8):
        seed = int(str(seed * seed).zfill(12)[3:9])
        key += (seed % (2 ** 16)).to_bytes(2, 'big')
    cipher = AES.new(key, AES.MODE_ECB)
    return cipher.encrypt(pad(message, AES.block_size)), key


def main():
    pt = b'a'

    for i in range(1000000):
        enc, key = encrypt(pt, i)
        if enc.hex() == '3edb0df8a8d93bb832e24472667e3602':
            print(i, key.hex())


if __name__ == '__main__':
    main()
