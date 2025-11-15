with open("output.txt", "r") as f:
    a = [l.strip() for l in f]

a = a[:1000]
ct = [[0, 0] for _ in a[0]]
for x in a:
    for i, v in enumerate(x):
        ct[i][int(v)] += 1

flag = 0
for i, c in enumerate(ct[::-1]):
    if c[0] > c[1]:
        flag |= 1 << i

print(flag.to_bytes((flag.bit_length() + 7) // 8, 'big'))

# L\xc9TC\xd4F{n0t_4_c0!n[FliP...c66f2b}
# LITCTF{n0t_4_c01n_FliP...c66f2b}