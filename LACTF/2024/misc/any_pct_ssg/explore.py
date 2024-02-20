m = 1 << 52
c = 4164880461924199
a = 2760624790958533

print(bin(a))
print(bin(c))

lo = 9 * m // 10

print(bin(lo))
print(bin(a * c % m))
print((a * a * c) % m)
print(m)

print(m - lo)
