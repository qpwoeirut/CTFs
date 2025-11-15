"""Attempt to generate the distribution with multithreading. This runs slower than the single-threaded version for some reason."""


import collections
import concurrent.futures
import copy
import random
import string
import time

from obs import cubify, linify, call_algorithm

start = time.time()


class FastPRNG(random.Random):
    stream = ""
    prev = -1
    i = 0

    def __init__(self):
        super().__init__()
        self.stream = bin(super().getrandbits(1 << 30))[2:].rjust(1 << 30, "0")
        print(time.time() - start, "Done generating stream")

    def n(self, check=False):
        if check:
            bound = 12
        else:
            bound = 18

        assert self.i + 32 <= len(self.stream)
        nbits = bound.bit_length()
        res = int(self.stream[self.i: self.i + nbits], 2) % bound
        if check:
            res = ((self.prev // 6 + 1) * 6 + res) % 18
        self.i += nbits
        self.prev = res
        return res

    def g(self, l=10):
        return [self.n(i != 0) for i in range(l)]


def shuffle(cube, moves) -> str:
    cube = copy.deepcopy(cube)
    call_algorithm(cube, moves)
    return linify(cube)


def main():
    n = 10 ** 5
    og = string.ascii_letters + "01"
    initial_cube = cubify(og)

    pos = {c: collections.Counter() for c in og}
    prng = FastPRNG()

    for _ in range(10):
        rand_moves = [prng.g(100) for _ in range(n)]
        print(time.time() - start, "Done generating rand_moves")

        with concurrent.futures.ProcessPoolExecutor() as executor:
            futures = [executor.submit(shuffle, initial_cube, moves) for moves in rand_moves]
            print(time.time() - start, "Done creating futures")
            for future in concurrent.futures.as_completed(futures):
                shuffled_cube = future.result()
                for i, c in enumerate(shuffled_cube):
                    pos[c][i] += 1
        print(time.time() - start, "Done completing futures")

        for c, ct in pos.items():
            print(c, ct)


if __name__ == '__main__':
    main()
