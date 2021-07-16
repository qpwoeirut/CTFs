import string

LOWERCASE_OFFSET = ord("a")
ALPHABET = string.ascii_lowercase[:16]


def b16_encode(plain):
    enc = ""
    for c in plain:
        binary = "{0:08b}".format(ord(c))
        enc += ALPHABET[int(binary[:4], 2)]
        enc += ALPHABET[int(binary[4:], 2)]
    return enc


def b16_decode(enc):
    plain = ""
    for i in range(0, len(enc), 2):
        a = bin(ALPHABET.index(enc[i]))[2:].rjust(4, '0')
        b = bin(ALPHABET.index(enc[i + 1]))[2:].rjust(4, '0')
        binary = int(a + b, 2)
        c = chr(binary)
        plain += c
    return plain


assert b16_decode(b16_encode("picoCTF{fake_flag12345}")) == "picoCTF{fake_flag12345}", b16_decode(
    b16_encode("picoCTF{fake_flag12345}"))


def shift(c, k):
    t1 = ord(c) - LOWERCASE_OFFSET
    t2 = ord(k) - LOWERCASE_OFFSET
    return ALPHABET[(t1 + t2) % len(ALPHABET)]


def unshift(c, k):
    t2 = ord(k) - LOWERCASE_OFFSET
    t3 = ALPHABET.index(c)
    t1 = (t3 - t2) % len(ALPHABET)
    return ALPHABET[t1]


def decrypt(enc, key):
    assert all([k in ALPHABET for k in key])
    assert len(key) == 1

    flag = ""
    for i, c in enumerate(enc):
        flag += unshift(c, key[i % len(key)])
    return b16_decode(flag)


encrypted_flag = "dcebcmebecamcmanaedbacdaanafagapdaaoabaaafdbapdpaaapadanandcafaadbdaapdpandcac"
for k in ALPHABET:
    print(decrypt(encrypted_flag, k))