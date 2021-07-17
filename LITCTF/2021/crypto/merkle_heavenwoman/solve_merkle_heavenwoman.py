# the purpose of a and b is to remove the q and r that didn't cancel out
# don't worry about it - we can try all 4 different options
# can probably use same idea as shamir's attack but hopefully finding fake privkey with less math

import ast
import math
from functools import reduce
from operator import and_

with open("output.txt") as f:
    pub, out = [x for x in f.read().split('\n') if x]
pub = ast.literal_eval(pub)
out = int(out)


def pub_to_priv(public, q, r) -> list:
    n = len(public)
    return [x ^ (q if i < n // 2 else r) for i, x in enumerate(public)]


def check_key(priv_key) -> bool:
    s = 0
    for x in priv_key:
        if s >= x:
            return False
        s += x
    return True


def dec(msg, priv_key):
    n = len(priv_key)

    pt = ""
    for i in range(n - 1, -1, -1):
        highestBit = 1 << int(math.log2(priv_key[i]))
        pt = ('0', '1')[(msg & highestBit) != 0] + pt
        msg ^= ((msg & highestBit) != 0) * (priv_key[i])

    try:
        print(hex(int(pt, 2)))
        return int(pt, 2).to_bytes(math.ceil(n / 8), "big").decode("utf-8")
    except ValueError:
        return None


def main():
    n = len(pub)
    q, r = 699936249058533560409157149 >> n * 2 << n * 2, 645194423245058853953075086 >> n * 2 << n * 2
    print(f"q={hex(q)}\nr={hex(r)}")

    q, r = pub[0] >> n*2 << n*2, pub[n // 2] >> n*3 << n*3

    print(f"q={hex(q)}\nr={hex(r)}")
    priv_key = pub_to_priv(pub, q, r)
    print(check_key(priv_key))

    print(dec(out, priv_key))
    print(dec(out ^ q, priv_key))
    print(dec(out ^ r, priv_key))
    print(dec(out ^ q ^ r, priv_key))


if __name__ == '__main__':
    main()
