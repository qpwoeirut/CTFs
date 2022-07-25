# https://stegonline.georgeom.net/image

from collections import Counter

red = [
    [0, 0, 0, 1, 0, 1, 0, 0, 1, 1],
    [0, 0, 1, 1, 0, 1, 1, 1, 1, 1],
    [0, 0, 0, 1, 1, 0, 1, 0, 0, 0],
    [1, 1, 1, 1, 1, 0, 1, 1, 1, 0],
    [0, 0, 1, 0, 0, 0, 1, 1, 0, 1],
    [0, 1, 1, 1, 0, 0, 1, 1, 0, 1],
    [0, 0, 0, 0, 0, 0, 1, 0, 1, 0],
    [1, 0, 1, 0, 1, 1, 1, 0, 0, 0],
    [0, 1, 0, 0, 1, 1, 0, 0, 0, 1],
    [0, 0, 1, 0, 1, 0, 0, 1, 0, 1]
]

green = [
    [0, 0, 0, 1, 0, 1, 0, 0, 0, 0],
    [0, 1, 0, 0, 0, 0, 0, 1, 0, 0],
    [0, 1, 0, 0, 1, 1, 1, 0, 1, 1],
    [0, 0, 0, 0, 1, 1, 0, 0, 1, 1],
    [1, 0, 1, 1, 1, 0, 1, 1, 1, 1],
    [1, 0, 0, 1, 0, 1, 0, 0, 0, 1],
    [1, 1, 0, 1, 1, 0, 0, 1, 1, 0],
    [0, 0, 1, 1, 0, 0, 1, 1, 1, 0],
    [1, 1, 1, 0, 1, 1, 1, 0, 0, 1],
    [0, 1, 1, 1, 0, 1, 1, 1, 0, 0]
]

blue = [
    [0, 0, 1, 0, 0, 0, 1, 0, 0, 1],
    [1, 1, 0, 0, 1, 0, 0, 0, 1, 0],
    [1, 0, 0, 0, 0, 1, 0, 0, 1, 1],
    [0, 1, 0, 1, 0, 0, 1, 0, 1, 1],
    [0, 0, 0, 1, 1, 0, 1, 0, 1, 0],
    [0, 0, 1, 1, 1, 1, 1, 1, 0, 1],
    [0, 1, 0, 1, 0, 1, 0, 1, 0, 1],
    [1, 1, 1, 1, 1, 1, 0, 0, 1, 1],
    [0, 1, 0, 0, 1, 0, 1, 0, 0, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
]

# maybe flip all the bits?
# red = [[x ^ 1 for x in row] for row in red]
# green = [[x ^ 1 for x in row] for row in green]
# blue = [[x ^ 1 for x in row] for row in blue]

# maybe transpose?
red = [[red[j][i] for j in range(len(red[0]))] for i in range(len(red))]
green = [[green[j][i] for j in range(len(green[0]))] for i in range(len(green))]
blue = [[blue[j][i] for j in range(len(blue[0]))] for i in range(len(blue))]

redFlat = [x for row in red for x in row]
greenFlat = [x for row in green for x in row]
blueFlat = [x for row in blue for x in row]

print(Counter(redFlat))
print(Counter(greenFlat))
print(Counter(blueFlat))

combined = [[f"{r}{g}{b}" for r, g, b in zip(rRow, gRow, bRow)]
            for rRow, gRow, bRow in zip(red, green, blue)]
for row in combined:
    print(row)

combinedFlat = [x for row in combined for x in row]
print(Counter(combinedFlat))

cs = ''.join(combinedFlat)
rs = ''.join([str(x) for x in redFlat])
gs = ''.join([str(x) for x in greenFlat])
bs = ''.join([str(x) for x in blueFlat])

# print stuff and pray that something useful comes up
for s in [cs, rs, gs, bs]:
    n = int(s, 2)
    print(n)
    print(hex(n))
    print(n.to_bytes((len(s) + 7) // 8, 'big'))


# false lead; trying to split into n-bit chunks and decode
print(len(cs))
for step in range(2, 20):
    if len(rs) % step or step != 5:
        continue
    print(step)
    combinedCt = Counter()
    for s in [cs, rs, gs, bs]:
        # maybe decode each channel separately?
        print([int(s[i:i+step], 2) for i in range(0, len(s), step)])
        ct = Counter([int(s[i:i+step], 2) for i in range(0, len(s), step)])
        if s != cs:
            combinedCt += ct
        print(len(ct), len(s) // step, 1 << step)
    print(len(combinedCt), len(s) // step, 1 << step)
    # print(combinedCt)
    print()

