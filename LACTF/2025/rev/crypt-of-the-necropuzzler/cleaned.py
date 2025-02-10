#!/usr/local/bin/python3
import tty
import sys
import hashlib

if sys.stdin.isatty():
    tty.setcbreak(0)

locked = [0, 0, 0, 0, 0, 0, 0,
          0, 1, 0, 0, 0, 0, 0,
          0, 0, 0, 1, 0, 0, 0,
          0, 0, 1, 0, 1, 0, 0,
          0, 0, 0, 1, 0, 0, 0,
          0, 0, 0, 0, 0, 0, 0,
          0, 0, 1, 0, 1, 0, 1]
grid = locked.copy()
n = [1, 1, 0, 0, 0, 0, 0,
     0, 1, 0, 2, 0, 0, 0,
     0, 0, 3, 1, 0, 0, 0,
     0, 0, 1, 0, 1, 0, 0,
     0, 0, 0, 1, 3, 0, 0,
     0, 1, 0, 1, 0, 1, 0,
     0, 0, 2, 2, 2, 0, 0]


def decrypt_flag(k):
    h = hashlib.sha512(str(k).encode()).digest()
    print(bytes(a ^ b for a, b in zip(h, bytes.fromhex("8b1e35ac3da64cb9db365e529ad8c9496388a4f499faf887386b4f6c43b616aae990f17c1b1f34af514800275673e0f3c689c0998fc73c342f033aa7cc69d199"))).decode())


moves = {'w': (-1, 0), 's': (1, 0), 'a': (0, -1), 'd': (0, 1)}


def dfs(a, b, s=None):
    if s is None:
        s = set()
    s.add((a, b))
    for (i, j) in moves.values():
        x, y = a + i, b + j
        if (x, y) not in s and x in range(7) and y in range(7) and grid[x * 7 + y] == grid[a * 7 + b]:
            dfs(x, y, s)
    return s


a, b = 0, 0
d = 1
while True:
    if d:
        print("\x1b[2J")
        for row in range(7):
            print(" ["[(a, b) == (row, 0)], end="")
            for col in range(7):
                print("_#"[grid[row * 7 + col]], end="[" if (a, b) == (row, col + 1) else " ]"[(a, b) == (row, col)])
            print()
        d = 0
    try:
        c = sys.stdin.read(1)
        if c == "":
            break
    except EOFError:
        break
    if c == 'q':
        break
    elif c == 'x':
        if not locked[idx := a * 7 + b]:
            grid[idx] = 1 - grid[idx]
            d = 1
    elif mv := moves.get(c):
        row, col = a + mv[0], b + mv[1]
        if row in range(7) and col in range(7):
            a, b = row, col
            d = 1
    elif c == 'c':
        p = 1
        vis = set()
        for row in range(7):
            for col in range(7):
                if (row, col) not in vis:
                    v = [0] * 4
                    k = dfs(row, col)
                    vis |= k
                    for (x, y) in k:
                        v[n[x * 7 + y]] += 1
                    if any(h not in (0, 2) for h in v[1:]):
                        p = 0
        if p:
            print("Correct!")
            decrypt_flag(grid)
        else:
            print("Incorrect!")
