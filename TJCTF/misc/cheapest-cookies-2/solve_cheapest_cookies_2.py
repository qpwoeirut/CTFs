from pwn import remote

N = 21
M = 40
INF = 9999


def solve(edges: list[tuple[int, ]]) -> int:
    assert M == len(edges)

    # let's be lazy and run a floyd-warshall, which is slower than dijkstra but should be fast enough for N=21
    dist = [[INF for _ in range(N)] for _ in range(N)]
    for edge in edges:
        dist[edge[0]][edge[1]] = min(dist[edge[0]][edge[1]], edge[2])
        dist[edge[1]][edge[0]] = min(dist[edge[1]][edge[0]], edge[2])

    for i in range(N):
        dist[i][i] = 0

    for k in range(N):
        for i in range(N):
            for j in range(N):
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

    # for e in edges:
    #     print(e)
    # for i in range(N):
    #     print(' '.join([str(dist[i][j]) for j in range(N)]))
    if dist[0][N-1] == INF:
        return -1
    return dist[0][N-1]


def main():
    r = remote("tjc.tf", 31111)
    for t in range(50):
        print(f"Solving test {t+1}")
        r.recvuntil(b"Here are the routes:\n").decode()
        roads = []
        for _ in range(M):
            roads.append(tuple(map(int, r.recvline().decode().split())))

        r.sendline(str(solve(roads)))

    print(r.recvall().decode())


if __name__ == '__main__':
    main()