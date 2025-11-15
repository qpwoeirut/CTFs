import collections
import concurrent.futures
import copy
import itertools
import logging
import string
import time
from typing import Generator

from pwnlib import log
from pwnlib.tubes.remote import remote

from obs import cubify, linify, PRNG, call_algorithm

log.getLogger(__name__).setLevel(logging.INFO)

start = time.time()
og = string.ascii_letters + "01"
n = 10 ** 6
m = 5000


def calculate_distribution() -> dict[str, collections.Counter]:
    initial_cube = cubify(og)

    distribution = {c: collections.Counter() for c in og}
    prng = PRNG()
    for _ in range(n):  # This will take a few minutes to run
        cube = copy.deepcopy(initial_cube)
        moves = prng.g(100)
        call_algorithm(cube, moves)
        cube_str = linify(cube)
        for i, c in enumerate(cube_str):
            distribution[c][i] += 1
    return distribution


def distance(a: collections.Counter[int], b_list: list[collections.Counter[int]]) -> float:
    b = sum(b_list, collections.Counter())
    return sum((a[i] / a.total() - b[i] / b.total()) ** 2 for i in range(len(og)))


def query_server() -> str:
    for _ in range(1200):
        try:
            r = remote("litctf.org", 31786)
            r.sendline(b'2')
            cube = r.recvline(keepends=False).decode()
            print("Done")
            return cube
        except EOFError as e:
            time.sleep(0.5)
    raise ValueError("Couldn't get response")


def query_actual_distribution() -> dict[str, collections.Counter]:
    distribution = collections.defaultdict(collections.Counter)

    with concurrent.futures.ProcessPoolExecutor() as executor:
        futures = [executor.submit(query_server) for _ in range(m)]
        for future in concurrent.futures.as_completed(futures):
            cube = future.result()
            for i, c in enumerate(cube):
                distribution[c][i] += 1
    return dict(distribution)


def all_solutions(s: str, best: dict[str, tuple[str]]) -> Generator[str, None, None]:
    if len(s) == len(og):
        yield s
    else:
        c = og[len(s)]
        actual_options = [val for val, expected_options in best.items() if c in expected_options] or ['?']
        for option in actual_options:
            yield from all_solutions(s + option, best)


def main():
    expected_distribution = calculate_distribution()
    with open("expected_distribution.txt", 'a') as f:
        f.write(str(expected_distribution) + '\n')
    print(time.time() - start, "Done calculating expected distribution")
    for c, ct in expected_distribution.items():
        print(c, ct)

    actual_distribution = query_actual_distribution()
    with open("actual_distribution.txt", 'a') as f:
        f.write(str(actual_distribution) + '\n')
    print(time.time() - start, "Done querying actual distribution")
    for c, ct in actual_distribution.items():
        print(c, ct)

    best = {}
    for c, ct in actual_distribution.items():
        occurrences = min(4, sum(ct.values()) // m)
        print(c, occurrences)
        distances = [(distance(ct, [expected_distribution[opt] for opt in combo]), combo)
                     for combo in itertools.combinations(og, occurrences)]
        distances.sort()
        print(c, distances[:10])
        best[c] = distances[0][1]

    for solution in all_solutions("", best):
        print(solution)


if __name__ == '__main__':
    main()
