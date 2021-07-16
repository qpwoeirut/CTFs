from binascii import unhexlify, hexlify

from Crypto.Cipher import DES
from pwn import remote
import itertools


def pad(msg):
    block_len = 8
    over = len(msg) % block_len
    pad = block_len - over
    return (msg + " " * pad).encode()


def calculate_table():
    table = dict()

    for key_tuple in itertools.product("0123456789", repeat=6):
        key = pad(''.join(key_tuple))
        table[DES.new(key, DES.MODE_ECB).encrypt(b" " * 8)] = key
    return table


def main():
    table = calculate_table()
    print("finished table")

    r = remote("mercury.picoctf.net", 5958)
    r.recvline()
    enc = unhexlify(r.recvline().strip().decode())
    print("flag_enc:", enc)

    r.sendline(hexlify(b' '))
    space_enc = unhexlify(r.recvline().strip().split()[-1].decode())
    print("space_enc:", space_enc)
    k1, k2 = find_keys(table, space_enc)
    print("k1, k2:", k1, k2)

    middle = DES.new(k2, DES.MODE_ECB).decrypt(enc)
    flag = DES.new(k1, DES.MODE_ECB).decrypt(middle)

    print(flag)


def find_keys(table, enc) -> tuple:
    for key_tuple in itertools.product("0123456789", repeat=6):
        key = pad(''.join(key_tuple))
        middle = DES.new(key, DES.MODE_ECB).decrypt(enc)
        if middle in table:
            return table[middle], key
    raise Exception("no key found")


if __name__ == "__main__":
    main()
