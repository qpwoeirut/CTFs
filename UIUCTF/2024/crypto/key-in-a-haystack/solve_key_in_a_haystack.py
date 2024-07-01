import hashlib
import logging
import sys

from Crypto.Cipher import AES
from pwn import remote

logging.getLogger("pwnlib").level = logging.WARNING
sys.set_int_max_str_digits(100000)

best = None
best_enc = None
for i in range(100):
    r = remote("key-in-a-haystack.chal.uiuc.tf", 1337, ssl=True)
    enc, haystack = r.recvall().decode().strip().split('\n')

    enc = enc.split()[1]
    haystack = int(haystack.split()[1])
    if best is None or best > haystack:
        best = haystack
        best_enc = enc
        with open("input.txt", 'a') as f:
            f.write(str(best) + '\n')
            f.write(best_enc + '\n')

    print(i, best.bit_length(), haystack.bit_length(), 1024 * 300 + 40)
