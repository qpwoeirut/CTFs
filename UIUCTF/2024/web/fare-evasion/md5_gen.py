import hashlib


def startswith(b: bytes, s: str):
    for i, c in enumerate(s):
        if b[i] != ord(c):
            return False
    return True

for i in range(100, 100000000):
    value = hashlib.md5(i.to_bytes(16)).digest()
    if startswith(value, "' OR 1") or startswith(value, "' or 1"):
        print(i, value)
        break
