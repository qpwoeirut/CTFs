with open("keys.txt") as f:
    keys = [[list(map(int, part.strip().split())) for part in line.split('|')] for line in f]

with open("ciphertexts.txt") as f:
    ciphertexts = [line.strip() for line in f]


def affine(ct: str, a: int, b: int):
    pt = ""
    for c in ct:
        if c.isalpha():
            base = 'A' if c.isupper() else 'a'
            val = ord(c) - ord(base)
            x = ((val - b) * pow(a, -1, 26)) % 26
            pt += chr(x + ord(base))
        else:
            pt += c
    return pt


def tweaks(pt: str):
    target = "LITCTF{affine_my_beloved}"
    assert len(pt) == len(target)
    difference = [ord(c1.lower()) - ord(c2.lower()) for c1, c2 in zip(pt, target)]
    # print(difference)
    return difference
    # return [i for i in range(len(pt)) if difference[i] != 0]


def main():
    plaintexts = [affine(ct, key[0][0], key[0][1]) for key, ct in zip(keys, ciphertexts)]
    for key, pt in zip(keys, plaintexts):
        # print(pt, tweaks(pt), key[1][0])
        tw = tweaks(pt)
        for i in key[2]:
            tw[i] = 0
        print(tw, sum(tw), key[2], tw[0], tw[1], tw[10], tw[19])



if __name__ == '__main__':
    main()
