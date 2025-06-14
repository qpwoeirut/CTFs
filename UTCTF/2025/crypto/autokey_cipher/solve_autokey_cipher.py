import itertools
import string

enc = "lpqwma{rws_ywpqaauad_rrqfcfkq_wuey_ifwo_xlkvxawjh_pkbgrzf}"


def sub(a: str, b: str) -> str:
    return chr(((ord(a) - ord(b)) % 26) + ord('a'))


def decrypt(s: str, key: str) -> str:
    assert all(c.isalpha() and c.islower() for c in key)

    out = ""
    j = 0
    for i, c in enumerate(s):
        if not c.isalpha():
            out += c
            continue

        out += sub(c, key[j])
        key += out[-1]
        j += 1
    return out


prefix = "utflag"
primer = "".join([sub(e, p) for e, p in zip(enc, prefix)])
print(primer)

# for i in range(len(enc)):
#     print(i, decrypt(enc, primer + 'a' * i))

for t in itertools.product(string.ascii_lowercase, repeat=2):
    print(decrypt(enc, primer + ''.join(t)))
