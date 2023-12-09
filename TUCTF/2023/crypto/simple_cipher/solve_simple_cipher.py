import itertools
import random
import string

from pwn import process, remote

BLOCK = 48
KEY_LEN = 6
NULL_KEY = b'\x00' * KEY_LEN
SECRET = "110010100001000100101101001010111110010111001011100100100001010110111111111010001110011111011101101100000001100100001001111111110101010011110011000100000011000010000100111100011001010111010111101111100011010110100110100010010000011111100001100100100001100110100100100100110111001001010101"

# r = process(["python3", "SimpleCipher_Pub.py"])
r = remote("chal.tuctf.com", 30004)


def encrypt(pt: bytes, key: bytes) -> str:
    assert len(key) == 6

    r.sendline(b"Encrypt a message with a custom key")
    # print("Encrypt a message with a custom key")

    resp = r.recvuntil(b"Enter your plaintext: ")
    # print(resp.decode().strip())
    r.sendline(pt)
    # print(pt)

    resp = r.recvuntil(b"Enter your 6 byte key (ex. 0011AABBCCDD): ")
    # print(resp.decode().strip())
    r.sendline(key.hex().upper().encode())
    # print(key.hex().upper())

    resp = r.recvuntil(b"Your ciphertext is: \n")
    # print(resp.decode().strip())

    resp = r.recvline().decode().strip()
    # print(resp)

    return resp


def decrypt(ct: str, key: bytes) -> str:
    assert len(key) == 6

    r.sendline(b"Decrypt a message")

    r.recvuntil(b"Enter your ciphertext as binary (ex. 0011001101010101000011110000000011111111): ")
    r.sendline(ct.encode())

    r.recvuntil(b"Enter your 6 byte key (ex. 0011FFDDCCBB): ")
    r.sendline(key.hex().upper().encode())

    r.recvuntil(b"Here is your plaintext back: \n ")
    return r.recvline().strip().decode()


def to_bits(msg: bytes) -> str:
    bits = ""
    for c in msg:
        bits += bin(c)[2:].zfill(8)
    return bits


def str_from_bits(bits: str) -> str:
    assert len(bits) % 8 == 0, len(bits)
    return ''.join([chr(int(bits[i: i + 8], 2)) for i in range(0, len(bits), 8)])


def bytes_from_bits(bits: str) -> bytes:
    assert len(bits) % 8 == 0, len(bits)
    return bytes([int(bits[i: i + 8], 2) for i in range(0, len(bits), 8)])


# looks like the server has issues receiving some byte values? will generate printables only
def generate_printable(flip_target: int) -> tuple[bytes, bytes]:
    for _ in range(100000):
        char = random.choice(string.printable).encode()
        original = char * (BLOCK // 8)
        flipped = list(to_bits(original))
        flipped[flip_target] = str(int(flipped[flip_target]) ^ 1)
        flipped = str_from_bits(''.join(flipped))

        try:
            if flipped.isprintable():
                return original, flipped.encode()
        except UnicodeDecodeError:
            pass
    raise ValueError("aiya")


def recover_perm() -> list[int]:
    perm = []
    for i in range(BLOCK):
        pt1, pt2 = generate_printable(i)
        ct1 = encrypt(pt1, NULL_KEY)
        ct2 = encrypt(pt2, NULL_KEY)
        diff = [i for i in range(BLOCK) if ct1[i] != ct2[i]]
        assert len(diff) == 1, diff

        perm.append(diff[0])
        print(i, perm)
    return perm


def unscramble(scrambled_text: str, rev_pattern: list[int]) -> str:
    unscrambled_text = ''
    assert len(unscrambled_text) % BLOCK == 0, len(unscrambled_text)
    for block in range(0, len(scrambled_text), 48):
        for i in rev_pattern:
            unscrambled_text += str(scrambled_text[block + i])
    return unscrambled_text


def read_secret() -> str:
    # r.recvuntil(b"[4] Exit")
    # r.recvline()
    #
    # r.sendline(b"Get the flag")
    # r.recvline()
    # secret1 = r.recvline().decode().strip()
    # secret2 = r.recvline().decode().strip()
    # secret3 = r.recvline().decode().strip()
    #
    # # this is a guess but it makes no sense otherwise since the individual secrets aren't full blocks
    # secret = secret1 + secret2 + secret3
    # assert secret == SECRET
    # return secret
    return SECRET


def recover_flag(secret: str, rev_pattern: list[int]) -> str:
    assert len(secret) == 288
    unscrambled_bits = unscramble(secret, rev_pattern)
    print(secret)
    xored_bytes = bytes_from_bits(unscrambled_bits)

    key = bytes([b ^ v for b, v in zip(xored_bytes, b'TUCTF{')])
    flag = bytes([b ^ v for b, v in zip(xored_bytes, key * (len(xored_bytes) // KEY_LEN))])

    assert len(xored_bytes) == 36

    # ended up not needing this
    # for k_i in range(KEY_LEN):
    #     values = [xored_bytes[i] for i in range(k_i, len(xored_bytes), KEY_LEN)]
    #     print(values)
    #     possible = []
    #     for val in range(256):
    #         if all([chr(v ^ val).isascii() and chr(v ^ val).isprintable() for v in values]):
    #             possible.append(val)
    #     print(k_i, possible)
    return flag.decode()


def main():
    # rev_pattern = recover_perm()
    rev_pattern = [9, 40, 27, 25, 42, 28, 34, 23, 17, 19, 24, 5, 11, 30, 6, 4, 41, 29, 1, 18, 16, 13, 20, 38, 36, 7, 3, 32, 12, 15, 2, 26, 14, 37, 44, 43, 22, 47, 35, 46, 8, 0, 21, 31, 33, 39, 45, 10]
    # print(rev_pattern)

    # test unscramble
    pt1, pt2 = generate_printable(8)
    ct1 = encrypt(pt1, NULL_KEY)
    ct2 = encrypt(pt2, NULL_KEY)
    dec1 = str_from_bits(unscramble(ct1, rev_pattern)).encode()
    dec2 = str_from_bits(unscramble(ct2, rev_pattern)).encode()
    assert dec1 == pt1, f"{pt1} {dec1}"
    assert dec2 == pt2, f"{pt2} {dec2}"

    secret = read_secret()
    flag = recover_flag(secret, rev_pattern)
    print(flag)


if __name__ == '__main__':
    main()

# TUCTF{D0nt_inv3nt_crypt0_algor1thm5}
