import time

from pwn import process, remote
from pwnlib.tubes.tube import tube

dirs = "wasd"
ch_row = [-1, 0, 1, 0]
ch_col = [0, -1, 0, 1]


def move(r: tube, d: str) -> bool:
    r.clean_and_log()
    r.sendline(d.encode())
    s = time.time()
    # Regular recv() with timeout doesn't seem to work, the \r is probably breaking things
    # I was getting responses that made it seem as if walls were magically appearing and disappearing
    resp = r.recvuntil(b'\r\n', timeout=0.7)  # usually takes around 0.3 on my computer if there's a response
    print(time.time() - s)
    print(d, resp)
    if resp.strip() == b'':
        return True
    elif resp.strip() == b'BONK':
        return False
    else:
        print("flag:", resp)
        return True


def dfs(r: tube, row: int, col: int, maze: list[list[str]]):
    print(row, col)
    for d, cr, cc in zip(dirs, ch_row, ch_col):
        nr = row + cr
        nc = col + cc
        if 0 <= nr < len(maze) and 0 <= nc < len(maze[nr]) and maze[nr][nc] == '?':
            if move(r, d):
                maze[nr][nc] = '.'
                dfs(r, nr, nc, maze)
                assert move(r, dirs[dirs.index(d) ^ 2])  # Make sure we end up back where we started
            else:
                maze[nr][nc] = '#'


def main():
    n = 40
    maze = [['?' for _ in range(n)] for _ in range(n)]
    maze[0][0] = '.'

    r = remote("left-in-the-dark.chal.imaginaryctf.org", 1337)
    # r = process(["python3", "maze.py"])
    assert r.recvline().strip() == b'Find the flag in this maze. Good luck!'
    assert r.recvline().strip() == b'WASD to move.'

    dfs(r, 0, 0, maze)


    # for _ in range(15):
    #     move(r, 's')
    #     move(r, 'a')


if __name__ == '__main__':
    main()
