from binascii import unhexlify

from pwn import remote, process

KEY_LEN = 50000


def check(s):
    ln = r.recvline().strip()
    assert ln.decode() == s, ln.decode()


r = remote("mercury.picoctf.net", 36981)
# r = process(["python3", "/Users/Stanley/CTF/CTFs/picoCTF/2021Spring/crypto/easy_peasy/otp.py"])

check("******************Welcome to our OTP implementation!******************")
check("This is the encrypted flag!")

enc = r.recvline().strip().decode()
flag_len = len(enc) >> 1
assert len(enc) % 2 == 0
print("enc:", enc)

r.sendline("\x00" * (KEY_LEN - flag_len))
check("")
check("What data would you like to encrypt? Here ya go!")
r.recvline()

r.sendline("\x00" * KEY_LEN)
check("")
check("What data would you like to encrypt? Here ya go!")
encoded_key = r.recvline().strip().decode()
encoded_key = unhexlify(encoded_key)
# print(unhexlify(encoded_key))

enc_bytes = [int(enc[i:i+2], 16) for i in range(0, len(enc), 2)]

# key = b"\x19\xabi2#8P\x98\xa4\xd9\x881\x92\x92I\x18\x95\x11z\xeb\xf3\x10\xca&\xc3\x1bge@\xa2{"
key = encoded_key
key_bytes = [int(c) for c in key]


flag = ""
for enc_byte, key_byte in zip(enc_bytes, key_bytes):
    flag += chr(enc_byte ^ key_byte)

print("flag:", flag)
