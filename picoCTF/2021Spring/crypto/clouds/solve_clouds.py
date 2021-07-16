import binascii
from collections import Counter

from pwn import remote, process
from random import randint

from sympy import mod_inverse

delta = (1 << 63) - 2
ci = dict()
MOD = 1 << 64

# r = remote("mercury.picoctf.net", 24402)
r = process(["python2", "/Users/Stanley/CTF/CTFs/picoCTF/2021Spring/crypto/clouds/clouds.py"])

cur_idx = 1


def pad_bin(n: int) -> str:
    return bin(n)[2:].rjust(64, '0')


def encrypt(pt: int) -> int:
    global cur_idx

    r.recvuntil("Selection? ")
    r.sendline("1")
    hex_pt = hex(pt)[2:].rjust(16, '0')
    r.sendline(hex_pt)

    r.recvuntil("Selection? ")
    r.sendline("2")
    r.sendline(str(cur_idx))
    cur_idx += 1
    r.recvuntil("What index would you like to read? ")
    enc = r.recvline().strip().decode()
    return int(enc, 16)


def gen_plaintexts():
    rand_plaintexts = [randint(0, (1 << 64) - 1) & delta for _ in range(128)]

    plain_part0 = [(pt, pt ^ delta) for pt in rand_plaintexts]
    plain_part1 = [(pt[0] + (1 << 63), pt[1] + (1 << 63)) for pt in plain_part0]
    plain_part2 = [(pt[0] + 1, pt[1] + 1) for pt in plain_part0]
    plain_part3 = [(pt[0] + (1 << 63) + 1, pt[1] + (1 << 63) + 1) for pt in plain_part0]

    plain = [plain_part0, plain_part1, plain_part2, plain_part3]
    return plain


def regen_key():
    import os
    with open("./key", "wb") as f:
        f.write(os.urandom(40))


def matches_condition_1(pt):
    ct_diff = (ci[pt[0]] ^ ci[pt[1]]) % 4
    is_even = ci[pt[0]] % 2 == 0 and ci[pt[1]] % 2 == 0
    # print(ct_diff, is_even)
    return ct_diff == 2 and is_even


def check_enc(plain):
    import binascii
    BLOCK_LEN = 8
    HEX_BLOCK_LEN = 16
    ROUNDS = 5

    def pad(p):
        if len(p) % BLOCK_LEN != 0:
            return p + "\0" * (BLOCK_LEN - (len(p) % BLOCK_LEN))
        else:
            return p

    def g(i):
        b = bin(i).lstrip("0b").rstrip("L").rjust(BLOCK_LEN * 8, "0")
        return int(b[::-1], 2)

    def encrypt_block(b):
        k = open("./key", 'rb').read().rstrip()
        assert (len(b) * ROUNDS) == len(k)
        result = int(binascii.hexlify(b), 16)
        for i in range(2):
            key = int(binascii.hexlify(k[i * BLOCK_LEN:(i + 1) * BLOCK_LEN]), 16)
            key_odd = key | 1
            result ^= key
            result = g(result)
            result = (result * key_odd) % (1 << 64)
        return hex(result).lstrip("0x").rstrip("L").rjust(HEX_BLOCK_LEN, "0")

    def enc(msg):
        plain = pad(msg)
        result = ""
        for i in range(0, len(plain), BLOCK_LEN):
            result += encrypt_block(plain[i:i + BLOCK_LEN])
        return result

    for part in plain:
        for p0, p1 in part:
            bytes0 = binascii.unhexlify(hex(p0)[2:].rjust(HEX_BLOCK_LEN, '0'))
            bytes1 = binascii.unhexlify(hex(p1)[2:].rjust(HEX_BLOCK_LEN, '0'))
            ct0 = hex(ci[p0])[2:].rjust(HEX_BLOCK_LEN, '0')
            ct1 = hex(ci[p1])[2:].rjust(HEX_BLOCK_LEN, '0')
            assert enc(bytes0) == ct0, f"{enc(bytes0)} {ct0}"
            assert enc(bytes1) == ct1, f"{enc(bytes1)} {ct1}"


def main():
    plain = gen_plaintexts()

    for part in plain:
        for pt0, pt1 in part:
            ci[pt0] = encrypt(pt0)
            ci[pt1] = encrypt(pt1)
            # assert cipher[pt0] % 2 == 1, f"{pad_bin(pt0)} {pad_bin(cipher[pt0])}"
            # assert cipher[pt1] % 2 == 1, f"{pad_bin(pt1)} {pad_bin(cipher[pt1])}"
            # print(pad_bin(pt0), pad_bin(pt1))
            # print(pad_bin(cipher[pt0]), pad_bin(cipher[pt1]))
        # print()

    # check_enc(plain)

    ct = [0, 0, 0, 0]
    s = set()
    for part_idx, part in enumerate(plain):
        for idx, pt in enumerate(part):
            assert pt[0] ^ pt[1] == delta
            if delta == ci[pt[0]] ^ ci[pt[1]]:
                # print(part_idx, idx, pad_bin(pt[0]), pad_bin(pt[1]))
                # print('   ', pad_bin(cipher[pt[0]]), pad_bin(cipher[pt[1]]))
                ct[part_idx] += 1
                s.add(idx)

    tmp = plain
    plain = []
    for part_idx, part in enumerate(tmp):
        if ct[part_idx] == 0:
            continue
        plain.append(part)

    ctr = Counter()
    for part_idx, part in enumerate(plain):
        for idx, pt in enumerate(part):
            if matches_condition_1((pt[0], pt[1])):
                print("match")
                print(part_idx, pad_bin(pt[0]), pad_bin(pt[1]))
                print(' ', pad_bin(ci[pt[0]]), pad_bin(ci[pt[1]]))
                print(' ', pad_bin(ci[pt[0]] ^ ci[pt[1]]))

                k = (ci[pt[0]] + ci[pt[1]]) // 2 * mod_inverse(delta >> 1, 1 << 64)
                k %= 1 << 64
                print(k)
                ctr[k] += 1
                # ct[part_idx] += 1
                # s.add(idx)

            # print()
    print(ctr)
    assert ctr.most_common(2)[1][1] > 1

    k5o = [ctr.most_common(1)[0][0], ctr.most_common(2)[1][0]]
    print(k5o)

    pdec = [dict(), dict()]

    for part_idx, part in enumerate(plain):
        for pt in part:
            for i in [0, 1]:
                for p in [0, 1]:
                    pdec[i][pt[p]] = ci[pt[p]] * mod_inverse(k5o[i], MOD)

    for part in plain:
        for pt in part:
            for i in [0, 1]:
                if delta == pdec[i][pt[0]] ^ pdec[i][pt[1]]:
                    print(pad_bin(pt[0]), pad_bin(pt[1]))


if __name__ == '__main__':
    # regen_key()
    BLOCK_LEN = 8
    k = open("./key", 'rb').read().rstrip()
    for i in range(5):
        key = int(binascii.hexlify(k[i * BLOCK_LEN:(i + 1) * BLOCK_LEN]), 16)
        print(key)
    main()
