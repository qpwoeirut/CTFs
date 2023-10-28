import hashlib
import itertools
from Crypto.Cipher import AES

plain = b'This is a non-secret message....'
cipher = b'7\xcf7\xce\xa6 \xbe\t\xba\x03\xe4\xac\x9e\x86\x85\xf5YZYa_7\xae\xa1\xe6\xc1\xd1\xad\xfb\x9c\x99s'
assert len(plain) == 32
assert len(cipher) == 32

single_enc = {
    AES.new(bytearray(key) + b'A' * 29, AES.MODE_ECB).encrypt(plain): bytearray(key)
    for key in itertools.product(range(256), repeat=3)
}

print("Finished computing single_enc")

for key in itertools.product(range(256), repeat=3):
    single_dec = AES.new(bytearray(key) + b'A' * 29, AES.MODE_ECB).decrypt(cipher)
    if single_dec in single_enc:
        k1 = bytearray(key) + b'A' * 29
        k2 = single_enc[single_dec] + b'A' * 29
        print(k1, k2)
        sha256 = hashlib.sha256(k1 + k2).hexdigest()
        print("CTF{" + sha256 + "}")
        sha256 = hashlib.sha256(k2 + k1).hexdigest()
        print("CTF{" + sha256 + "}")

# bytearray(b'\x06K\xf7AAAAAAAAAAAAAAAAAAAAAAAAAAAAA') bytearray(b's\x00\x15AAAAAAAAAAAAAAAAAAAAAAAAAAAAA')
# CTF{91e6611654e4fe66d6876f728b8dfd54999ed752f89239ab82ecd9e520c1e003}