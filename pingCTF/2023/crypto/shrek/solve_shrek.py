import collections
import itertools

from pwn import process, remote

r = remote("shrek.knping.pl", 50000)
# r = process(["python3", "main.py"], cwd="src/")

with open("src/shrek.txt") as file:
    text = file.read()
    parts = list(text.split())

alphabet = ""
for part in parts:
    for letter in part:
        if not letter in alphabet:
            alphabet += letter
alphabet += " "


def explore():
    print(alphabet)
    print(len(text))
    print(len(parts))
    print(len(set(parts)))

    for L in range(1, max(len(p) for p in parts) + 1):
        print(L, collections.Counter(p for p in parts if len(p) == L))

    print(collections.Counter(text))


def alignment_score(cts: list[str], key_len: int) -> float:
    text_length = sum([len(ct) for ct in cts])
    ratios = []
    for k_i in range(key_len):
        chars = [ct[i] for ct in cts for i in range(k_i, len(ct), key_len)]
        ctr = collections.Counter(chars)
        space = ctr.most_common(n=1)[0]
        ratio = space[1] / (text_length / len(alphabet))
        ratios.append(ratio)
    return sum(ratios) / key_len


def get_key_len(cts: list[str]) -> int:
    lengths = [(alignment_score(cts, key_len), key_len) for key_len in range(10, 30)]
    lengths.sort(reverse=True)

    # if this fails just rerun the program, let's make sure we have right key length
    assert lengths[0][0] / lengths[1][0] >= 1.5 or (
            lengths[0][1] * 2 == lengths[1][1] and lengths[0][0] / lengths[2][0] >= 1.6), lengths

    return lengths[0][1]


def assume_spaces(cts: list[str], key_len: int) -> tuple[list[int], list[str]]:
    key = []
    for k_i in range(key_len):
        chars = [ct[i] for ct in cts for i in range(k_i, len(ct), key_len)]
        ctr = collections.Counter(chars)
        space = ctr.most_common(n=1)[0]
        key.append((alphabet.index(space[0]) - alphabet.index(' ')) % len(alphabet))

    pts = [
        ''.join([
            alphabet[(alphabet.index(c) - k) % len(alphabet)]
            for c, k in zip(ct, itertools.cycle(key))
        ])
        for ct in cts
    ]
    return key, pts


def main():
    # explore()

    cts = []
    for i in range(10):
        ct = r.recvline().strip().decode()
        ct = ct.removeprefix("Ciphertext: ")
        cts.append(ct)
        if i < 9:
            r.sendline(b"no")
            r.recvline()

    key_len = get_key_len(cts)
    key, pts = assume_spaces(cts, key_len)
    print(pts)
    print(key)

    r.sendline(pts[-1].encode())
    print(r.recvall(timeout=1).decode().strip())


if __name__ == '__main__':
    main()
