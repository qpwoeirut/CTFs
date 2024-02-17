import itertools

import pwnlib.tubes.tube
from pwn import process, remote


QUERIES = 56
LEN = 16
PAIRS = LEN - 1
OTHER = QUERIES - LEN - PAIRS


def get_info(r: pwnlib.tubes.tube.tube) -> int:
    r.recvuntil(b"Choose rock, paper, or scissors: ")
    r.sendline(b"rock")
    resp = r.recvline().strip().decode()
    return ["Tie!", "You lose!", "You win!"].index(resp)


def shift(x: int) -> int:
    result = 0
    for i in range(4):
        result |= (((x >> i) ^ (x >> (i + 1)) ^ (x >> (i + 3)) ^ (x >> (i + 4))) & 1) << i
    return result


def recurse(idx: int, pairs: list[list[tuple[int, int]]]) -> list[list[int]]:
    if idx == len(pairs):
        endings = {pair[1] for pair in pairs[-1]}
        return [[ending] for ending in endings]

    endings = recurse(idx + 1, pairs)
    combined = []
    for pair in pairs[idx]:
        for ending in endings:
            if pair[1] == ending[0]:
                combined.append([pair[0]] + ending)
    return combined


def gen_vals(state_hex: list[int], n: int) -> list[int]:
    state = 0
    for s in state_hex:
        state = state * 16 + s
    vals = []
    for _ in range(n):
        vals.append((state & 0xf) % 3)
        for i in range(4):
            bit = (state ^ (state >> 1) ^ (state >> 3) ^ (state >> 4)) & 1
            state = (state >> 1) | (bit << 63)
    return vals


def main():
    r = remote("mc.ax", 31234)
    # r = process(["python3", "server.py"])
    vals = [get_info(r) for _ in range(QUERIES)]
    i_val = 0

    # big endian
    possible = [list(range(16)) for _ in range(LEN)]

    for i in reversed(range(LEN)):
        possible[i] = [p for p in possible[i] if p % 3 == vals[i_val]]
        i_val += 1

    pairs = [itertools.product(possible[i], possible[i + 1]) for i in range(PAIRS)]
    for i in reversed(range(PAIRS)):
        pairs[i] = [p for p in pairs[i] if shift(p[0] * 16 + p[1]) % 3 == vals[i_val]]
        i_val += 1

    possible_states = recurse(0, pairs)
    print(len(possible_states))
    # usually around 1e4 to 1e5 possibilities, will take a few seconds to filter through

    starting_states = [possible_state for possible_state in possible_states if gen_vals(possible_state, QUERIES) == vals]
    print(starting_states)  # should almost always have len == 1

    all_vals = gen_vals(starting_states[0], QUERIES + 50)
    rps = [b"rock", b"paper", b"scissors", b"rock"]
    for i in range(50):
        r.sendline(rps[all_vals[QUERIES + i] + 1])

    print(r.recvall().strip().decode())


if __name__ == '__main__':
    main()