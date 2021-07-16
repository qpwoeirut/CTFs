import itertools
import string

LOWERCASE_OFFSET = ord("a")
ALPHABET = string.ascii_lowercase[:16]

HEX_CHARS = set("abcdef0123456789")
CHARS = set(ALPHABET)


def b16_encode(plain):
    enc = ""
    for c in plain:
        binary = "{0:08b}".format(ord(c))
        enc += ALPHABET[int(binary[:4], 2)]
        enc += ALPHABET[int(binary[4:], 2)]
    return enc


def shift(c, k):
    t1 = ord(c) - LOWERCASE_OFFSET
    t2 = ord(k) - LOWERCASE_OFFSET
    return ALPHABET[(t1 + t2) % len(ALPHABET)]


def encrypt(flag, key):
    assert all([c in "abcdef0123456789" for c in flag])
    assert all([k in ALPHABET for k in key]) and len(key) < 15

    b16 = b16_encode(flag)
    enc = ""
    for i, c in enumerate(b16):
        enc += shift(c, key[i % len(key)])
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


def unshift(c, k):
    t2 = ord(k) - LOWERCASE_OFFSET
    t3 = ALPHABET.index(c)
    t1 = (t3 - t2) % len(ALPHABET)
    return ALPHABET[t1]


def decrypt(enc, key):
    assert all([k in ALPHABET for k in key])
    assert len(key) < 15

    flag = ""
    for i, c in enumerate(enc):
        flag += unshift(c, key[i % len(key)])
    # return flag
    return b16_decode(flag)


def test_key_len(options, key_len: int) -> bool or list:
    ret = []
    for i in range(key_len):
        opts = options[i].copy()
        for j in range(i, len(options), key_len):
            opts.intersection_update(options[j])
        if len(opts) == 0:
            return False
        ret.append(opts)
    return ret


# key length is 9
def get_key_len(key_opts):
    print(key_opts)

    for i in range(1, 15):
        print(i, test_key_len(key_opts, i))


def main():
    enc = "lejjlnjmjndkmjinkilbmlljjkmnakmmighhocmllojhjmaijpiohnlojmokjkja"
    # enc = encrypt("abcdef01234567899876543210fedcba", "abcdefg")

    STEP = 2
    key_opts = [set() for _ in range(64 // STEP)]

    for L in range(0, len(enc), STEP):
        print("L =", L)
        for key_tuple in itertools.product(ALPHABET, repeat=STEP):
            key = ''.join(key_tuple)
            plain = decrypt(enc[L:L + STEP], key)
            if all([c in HEX_CHARS for c in plain]):
                key_opts[L // STEP].add(key)

    get_key_len(key_opts)

    key_choices = test_key_len(key_opts, 9)[:5]
    print(key_choices)
    for key_tuple in itertools.product(*key_choices):
        key = ''.join(key_tuple)
        if key[0] != key[-1]:
            continue
        key = key[:-1]
        plain = decrypt(enc, key)
        if all([c in HEX_CHARS for c in plain]):
            print(plain, key)


if __name__ == '__main__':
    main()
