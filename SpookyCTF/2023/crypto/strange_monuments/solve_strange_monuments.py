"""
Indiana is searching for alien artifacts deep in the jungle. He's following a winding river, and in order to not get
lost he has charted its flow with the equation y^2 = x^3 + 7586x + 9001 (mod 46181).

Every point on the river's flow represents the site where an alien monument has been reported. Indiana starts at the
monument location denoted on his chart with the point (20305,32781).

He follows the flow of the river from that monument and passes many others. However, he loses count due to some snakes
that he had to run from! Indiana is now at the monument marked with the point (39234,12275) on his chart.

How many monuments did Indiana pass in total?
"""

a = 7586
b = 9001
mod = 46181


def inv(n: int) -> int:
    return pow(n, mod - 2, mod)


def add(p: tuple[int, int], q: tuple[int, int]) -> tuple[int, int]:
    if p == q:
        assert p[1] != 0
        L = (3 * p[0] ** 2 + a) * inv(2 * p[1]) % mod
    else:
        L = (q[1] - p[1]) * inv(q[0] - p[0]) % mod

    r_x = (L ** 2 - p[0] - q[0]) % mod
    r_y = (L * (p[0] - r_x) - p[1]) % mod
    return r_x, r_y


def main():
    start = 20305, 32781
    target = 39234, 12275

    pt1 = start
    pt2 = start
    for i in range(mod):
        pt1 = add(pt1, pt2)
        pt1, pt2 = pt2, pt1
        # print(pt1, pt2)
        if pt2 == target:
            print(i)

    pt = start
    for i in range(mod):
        pt = add(start, pt)
        # print(pt)
        if pt == target:
            print(i)


if __name__ == '__main__':
    main()

# NICC{2999}
