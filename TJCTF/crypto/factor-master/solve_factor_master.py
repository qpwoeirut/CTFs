from pwn import remote, process
from time import time


def solve():
    solver = process(["./a.out"])
    solver.recvuntil(b"Ready!\n")

    r = remote("tjc.tf", 31782)
    # r = process(["python3", "server.py"])
    start = time()

    for challenge in [1, 2, 3]:
        print(r.recvuntil(f"CHALLENGE {challenge}\nn = ".encode()).decode())

        n_line = r.recvline(keepends=False).decode()
        print(n_line)

        n = int(n_line.strip())
        solver.sendline(str(n).encode())
        p = int(solver.recvline().strip())
        q = n // p

        print(p, q)
        print(time() - start)
        r.sendline(f"{p} {q}".encode())

    res = r.recvall(timeout=3).decode()
    print(res)
    return "tjctf" in res


def main():
    while solve() is False:
        continue


if __name__ == '__main__':
    main()
