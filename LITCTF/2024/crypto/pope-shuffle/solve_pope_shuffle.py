with open("enc.txt") as f:
    enc = f.read()

ords = [ord(c) for c in enc]

lo = max(ords) - 128
hi = min(ords) - 32

for x in range(lo, hi + 1):
    print(''.join([chr(o - x) for o in ords]))
