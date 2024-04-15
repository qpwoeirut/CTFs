import random
from pwn import remote

from Crypto.Util.number import long_to_bytes

"""
Key-scheduling algorithm (KSA)
"""
def KSA(key):
    S = [i for i in range(0, 256)]
    i = 0
    for j in range(0, 256):
        i = (i + S[j] + key[j % len(key)]) % 256

        S[i] ^= S[j]  ## swap values of S[i] and S[j]
        S[j] ^= S[i]
        S[i] ^= S[j]

    return S


"""
Pseudo-random generation algorithm (PRGA)
"""
def PGRA(S):
    i = 0
    j = 0
    while True:  ## while GeneratingOutput
        i = (1 + i) % 256
        j = (S[i] + j) % 256

        S[i] ^= S[j]  ## swap values of S[i] and S[j]
        S[j] ^= S[i]
        S[i] ^= S[j]

        yield S[(S[i] + S[j]) % 256]


def test():
    # No, I don't know why this works. It was a total guess.
    input_text = '\x00' * 200000

    plaintext = [ord(char) for char in input_text]
    key = long_to_bytes(random.getrandbits(2048))  # 2048 bits = 256 bytes
    key = [byte for byte in key]

    S = KSA(key)
    keystream = PGRA(S)

    ciphertext = [c ^ next(keystream) for c in plaintext]
    print(ciphertext)


def main():
    r = remote("gold.b01le.rs", 5004)
    r.recvuntil(b'(Just hit enter if you do not want to add any padding(s).)')
    r.sendline(b'a' * 1000000)
    r.recvline()
    enc = r.recvline().decode().strip()
    print(r.recvall())

    print(bytes.fromhex(enc)[-100:])


if __name__ == '__main__':
    main()
    # test()
