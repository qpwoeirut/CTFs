from pwn import process, remote

p = 1719620105458406433483340568317543019584575635895742560438771105058321655238562613083979651479555788009994557822024565226932906295208262756822275663694111

G = GL(5, GF(p))
n = G.order()
print(n)
n2 = n
k = 1
while n2 % 3 == 0:
    n2 = n2 // 3
    k = k * 3


def solve(g_i, Mr):
    h_i = Mr ** (n // k)

    g_j = g_i
    for i in range(1, k):
        if g_j == h_i:
            return i % 3
        g_j *= g_i
    raise ValueError("sob")


# r = process(["sage", "server.sage"])
r = remote("crypto.2023.cakectf.com", int(10555))

r.recvline()
line = r.recvline().decode().split()
p = int(line[4].strip(','))
M = list(map(lambda x: int(x.strip("[,]")), line[7:]))
M = matrix(GF(p), [[M[5 * r + c] for c in range(5)] for r in range(5)])
g_i = M ** (n // k)

for rnd in range(1, 101):
    print(r.recvuntil(f"[system]: ROUND {rnd}\n".encode()).decode())
    Mr = list(map(lambda x: int(x.strip('is=[,]')), r.recvline().decode().split()[3:]))
    Mr = matrix(GF(p), [[Mr[5 * r + c] for c in range(5)] for r in range(5)])

    hand = "312"[solve(g_i, Mr)]  # rotate so that we win
    r.sendline(hand)

    print(r.recvline(keepends=False).decode())
    print(r.recvline(keepends=False).decode())
    print(r.recvline(keepends=False).decode())
    print(r.recvline(keepends=False).decode())

print(r.recvall(timeout=1).decode())
