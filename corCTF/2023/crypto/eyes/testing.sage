from Crypto.Util.number import bytes_to_long, getPrime

# my NEW and IMPROVED secret sharing scheme!! (now with multivariate quadratics)

with open('flag.txt', 'rb') as f:
    flag = f.read()

s = bytes_to_long(flag)
p = getPrime(len(bin(s)))
print(p)
F = GF(p)
N = 6

conv = lambda n: matrix(F, N, 1, [int(i) for i in list(bin(n)[2:][::-1].ljust(N, '0'))])

A = random_matrix(F, N, N)

for i in range(0, N):
    for j in range(0, i):
        A[i, j] = 0
B = random_matrix(F, N, 1)
C = matrix(F, [F(s)])

fn = lambda x: (x.T * A * x + B.T * x + C)[0][0]

L = []
for i in range(7):
    L.append(fn(conv(i + 1)))

print(L)

print("\nA:")
print(A)

print("\nB:")
print(B)

# print("\nC:")
# print(C)


for i in range(1, 8):
    print(f"\ni = {i}:")
    x = conv(i)
    print(x.T * A * x)
    print(B.T * x)


"""
A = 
[[a b c]
 [0 d e]
 [0 0 f]]

B =
[[g]
 [h]
 [i]]

x0: a + g + C
x1: d + h + C
x2: a + b + d + g + h + C
x3: f + i + C
x4: a + c + f + g + i + C
x5: d + e + f + h + i + C
x6: a + b + c + d + e + f + g + h + i + C

x6 = x2 + x4 - x0 + e
e = x0 + x6 - x2 - x4

x5 = x1 + x3 + e - C
C = x1 + x3 - x5 + e = x0 + x1 - x2 + x3 - x4 - x5 + x6
"""

print("\nC:")
print(C)
print(L[0] + L[1] - L[2] + L[3] - L[4] - L[5] + L[6])