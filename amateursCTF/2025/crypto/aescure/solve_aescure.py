import itertools

from Crypto.Cipher import AES
from utils.flag_strings import alpha_flag_chars_lower

pt = b'\x00' * 16
ct = bytes.fromhex("5aed095b21675ec4ceb770994289f72b")
# Guess that the challenge is using AES-128
for t in itertools.product(alpha_flag_chars_lower, repeat=16 - len("amateursCTF{}")):
    key = ''.join(["amateursCTF{", *t, "}"])
    cipher = AES.new(key.encode(), AES.MODE_ECB)
    if cipher.encrypt(pt) == ct:
        print(key)
