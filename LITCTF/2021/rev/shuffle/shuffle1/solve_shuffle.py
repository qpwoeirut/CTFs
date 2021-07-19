import random

with open("shuffle.txt") as f:
    enc = f.read().strip()

n = len(enc)


def try_seed(seed):
    random.seed(seed)
    perm = list(range(n))

    random.shuffle(perm)

    out = ['?' for _ in range(n)]
    for i, c in enumerate(enc):
        out[perm[i]] = c

    flag = ''.join(out)
    if flag.startswith("flag"):
        print(flag)


def main():
    for seed in range(1001):
        try_seed(seed)


if __name__ == '__main__':
    main()