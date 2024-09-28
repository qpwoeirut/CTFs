import itertools
import string


CHARS = string.ascii_letters + string.digits + " \n" + string.punctuation


def main():
    with open("out.txt") as f:
        enc = bytes.fromhex(f.read().strip())

    blocks = [enc[i: i + 16] for i in range(0, len(enc), 16)]

    possible_key = [list(range(256)) for _ in range(16)]
    for b_i, block in enumerate(blocks):
        possible_key[15] = [
            p for p in possible_key[15]
            if chr(block[15] ^ (((p + b_i) % 255) % 256)) in CHARS  # Not sure why this modding associativity works
        ]

    for i in reversed(range(15)):
        for b_i, block in enumerate(blocks):
            possible_key[i] = [
                p for p in possible_key[i]
                if chr(block[i] ^ p) in CHARS or (
                        (255 in possible_key[i + 1] or i == 14) and chr(block[i] ^ (p + 1)) in CHARS
                )
            ]

    for i in range(16):
        print(i, possible_key[i])

    for key in itertools.product(*possible_key):
        flag = bytearray()
        for b_i, block in enumerate(blocks):
            for i in range(16):
                key_byte = key[i]
                if i == 15:
                    key_byte = (key_byte + b_i) % 255
                flag.append(block[i] ^ key_byte)
        print(flag)


if __name__ == '__main__':
    main()
