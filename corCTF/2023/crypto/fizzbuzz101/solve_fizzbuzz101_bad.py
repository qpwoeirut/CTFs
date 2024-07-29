from os import urandom

from Crypto.Util.number import bytes_to_long

flag = b"corCTF{somemuchmuchlongerflagforlocaltesting}"
flag = bytes_to_long(urandom(16) + flag + urandom(16))

print(flag)
