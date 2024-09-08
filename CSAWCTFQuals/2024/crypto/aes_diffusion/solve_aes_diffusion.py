from aes_simulator import MixColumns, state


# Create version of ShiftRows that doesn't mutate input directly
def CyclicShift(row, shift):
    return row[shift:] + row[:shift]


def ShiftRows(mat):
    return [CyclicShift(mat[row_index], row_index) for row_index in range(4)]


def get_components(expr: str) -> list[str]:
    print(expr)
    parts = expr.removeprefix('(').removesuffix(')').split(' + ')
    x_parts = [part.split('*')[1] for part in parts]
    x_parts.sort(key=lambda s: int(s[1:]))
    return x_parts


def r_of(s):
    for r, row in enumerate(state):
        if s in row:
            return r
    raise ValueError()


def c_of(s):
    for row in state:
        if s in row:
            return row.index(s)
    raise ValueError()


def main():
    shifted = ShiftRows(state)
    mixed = MixColumns(shifted)

    ans0 = shifted
    ans1 = mixed
    for r in ans1:
        for i in range(len(r)):
            r[i] = r[i].removeprefix('(').removesuffix(')')

    ans2 = ','.join(get_components(mixed[r_of('x0')][c_of('x0')]))
    ans3 = ','.join(get_components(mixed[r_of('x11')][c_of('x11')]))
    ans4 = "no"
    ans5 = 16 - 4
    ans6 = ','.join([c + ':1' for c in get_components(mixed[r_of('x0')][c_of('x0')])])

    ans7 = ','.join([
        x + ':0' for x in sorted(
            [
                x_0
                for x_1 in get_components(mixed[r_of('x0')][c_of('x0')])
                for x_0 in get_components(mixed[r_of(x_1)][c_of(x_1)])
            ], key=lambda s: int(s[1:])
        )
    ])
    tmp = state
    for _ in range(10):
        tmp = ShiftRows(tmp)
    ans8 = tmp[r_of('x0')][c_of('x0')]

    ans9 = MixColumns(MixColumns(state))[r_of('x0')][c_of('x0')]
    ans10 = "no"

    print("----ANSWERS----")
    print(ans0)
    print(ans1)
    print(ans2)
    print(ans3)
    print(ans4)
    print(ans5)
    print(ans6)
    print(ans7)
    print(ans8)
    print(ans9)
    print(ans10)


if __name__ == '__main__':
    main()
