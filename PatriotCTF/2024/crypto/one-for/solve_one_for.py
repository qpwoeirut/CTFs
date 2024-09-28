# Assume all characters are < 128
def count_known(enc: bytes) -> tuple[int, int]:
    same, flipped = 0, 0
    for byte in enc:
        if byte >= 128:
            flipped += 1
        else:
            same += 1

    return same, flipped


def main():
    with open("output.txt") as f:
        encs = [bytes.fromhex(line.strip()) for line in f]

    n = len(encs[0]) * 8

    count = [0 for _ in range(n)]
    for enc in encs:
        known = count_known(enc)
        if known[0] >= known[1]:
            continue
        for i in range(n):
            count[i] += (enc[i // 8] >> (i % 8)) & 1

    bits = [0 for _ in range(n)]
    for i, ct in enumerate(count):
        print(i, ct, len(encs))
        bits[i] = ct * 4 > len(encs)

    flag = bytearray()
    for i in range(0, n, 8):
        byte = 0
        for j in range(7):  # skip MSB
            byte |= bits[i + j] << j
        flag.append(byte)
    print(flag)



if __name__ == '__main__':
    main()
