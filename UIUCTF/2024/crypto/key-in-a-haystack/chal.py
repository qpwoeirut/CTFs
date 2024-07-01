from Crypto.Util.number import getPrime
from Crypto.Util.Padding import pad
from Crypto.Cipher import AES

from hashlib import md5
from math import prod
import sys

# from secret import flag

sys.set_int_max_str_digits(0)
key = getPrime(20)
haystack = [ getPrime(256) for _ in range(300) ]
key_in_haystack = key * prod(haystack)
print(key_in_haystack)

enc_flag = AES.new(
	key = md5(b"%d" % key).digest(),
	mode = AES.MODE_ECB
).encrypt(pad(flag, 16))

sys.set_int_max_str_digits(0)

print(f"enc_flag: {enc_flag.hex()}")
print(f"haystack: {key_in_haystack}")

exit(0)
