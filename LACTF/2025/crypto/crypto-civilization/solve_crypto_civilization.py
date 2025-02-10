import hashlib

from pwn import remote


# s is 16 bits long, 32 bit output
def PRG(s: bytes) -> bytes:
    assert len(s) == 2, "You're trying to cheat! Go to Crypto Prison!"

    h = hashlib.new("sha3_256")
    h.update(s)

    out = h.digest()

    return out[:4]


def xor_bytes(bytes1: bytes, bytes2: bytes) -> bytes:
    if len(bytes1) != len(bytes2):
        raise ValueError("Byte objects must be of the same length")

    return bytes(b1 ^ b2 for b1, b2 in zip(bytes1, bytes2))


reverse_prng = dict()
for b0 in range(256):
    for b1 in range(256):
        as_bytes = bytes([b0, b1])
        reverse_prng[PRG(as_bytes)] = as_bytes

assert len(reverse_prng) == 256 * 256, "assumed there are no duplicate outputs"


r = remote("chall.lac.tf", 31173)


def answer_test():
    r.recvuntil(b"Here's y: ")
    y = bytes.fromhex(r.recvline().decode())

    commitment = list(reverse_prng.keys())[0]
    for key in reverse_prng:
        if xor_bytes(key, y) in reverse_prng:
            commitment = key

    r.sendline(commitment.hex().encode())
    print(commitment.hex())
    r.recvuntil(b"Did you commit the ")

    choice = r.recvline().decode().split()[0].strip('?')
    print(choice)
    if choice == "beef" and xor_bytes(commitment, y) in reverse_prng:
        decom = reverse_prng[xor_bytes(commitment, y)]
        print(xor_bytes(PRG(decom), y))
        r.sendline(decom.hex().encode())
    else:
        r.sendline(reverse_prng[commitment].hex().encode())
    print(r.recvline(keepends=False).decode())



def main():
    for _ in range(200):
        answer_test()
    print(r.recvall(timeout=1))


if __name__ == '__main__':
    main()
