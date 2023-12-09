import binascii
import random

c = 'e34a707c5c1970cc6375181577612a4ed07a2c3e3f441d6af808a8acd4310b89bd7e2bb9'
ct = binascii.unhexlify(c)
BITS = 32


def reverse_xor(value: int, shift: int, mask: int) -> int:
    bits = list(map(int, bin(value)[2:].zfill(BITS)))[::-1]
    assert shift != 0, "can't recover"

    if shift > 0:
        for i in range(BITS):
            if i + shift < BITS and mask & (1 << (i + shift)) > 0:
                bits[i + shift] ^= bits[i]
    else:
        for i in reversed(range(BITS)):
            if i + shift >= 0 and mask & (1 << (i + shift)) > 0:
                bits[i + shift] ^= bits[i]
    return sum(bits[i] << i for i in range(BITS))


def solve(x: int) -> bytes:
    # keeping this here for testing and to remember order of operations
    def convert(msg):
        msg ^= (msg >> x)
        msg ^= (msg << 13) & 275128763
        msg ^= (msg << 20) & 2186268085
        msg ^= (msg >> 14)
        return msg

    def reverse(msg):
        msg = reverse_xor(msg, -14, 0xffffffff)
        msg = reverse_xor(msg, 20, 2186268085)
        msg = reverse_xor(msg, 13, 275128763)
        msg = reverse_xor(msg, -x, 0xffffffff)
        return msg

    # test reverse
    for i in range(1000):
        n = random.randint(0, 0xff)
        assert reverse(convert(n)) == n, f"\n{bin(n)[2:].zfill(BITS)}\n{bin(convert(n))[2:].zfill(BITS)}\n{bin(reverse(convert(n)))[2:].zfill(BITS)}"

    decrypted = b''
    for i in range(len(ct) // 4):
        block = ct[i * 4: i * 4 + 4]
        block = int.from_bytes(block, 'big')
        block = reverse(block)
        block = int.to_bytes(block, 4, 'big')
        decrypted += block
    return decrypted


def main():
    for x in range(1, BITS):
        flag = solve(x)
        try:
            print("TUCTF{" + flag.decode() + "}")
        except UnicodeDecodeError:
            pass


if __name__ == '__main__':
    main()
