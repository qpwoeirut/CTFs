import sys

sys.setrecursionlimit(50000)


class Type1:
    def __init__(self, b):
        self.b = b

    def s(self, b):
        return Type1(b(self.b).b)


class Type2:
    def __init__(self, b):
        self.b = b

    def d(self, b):
        return self if Type1(self.b).s(b).b != b(self.b).b else b(self.b)


def m(x):
    return Type2(f"{list(zip([l(i + 5*i) for i in x], [l(i + i) for i in x][::-1]))[::-1] = }")


def n(x):
    return Type2("{x[';]:=^450}".format(x={'''';''': x}))


def l(x):
    return (12648430 ^ (x + 3735928559)) * 3862272608


def inner(m, n):
    x = bin(m * n)
    return int(x[len(x) - 59:], 2) + int(x[:len(x) - 59], 2) if len(x) - 59 > 0 else int(x, 2)


def s_func(i, flag_input):
    z = sum(
        (ord(flag_input[i + j]) << j * 7) if i + j < len(flag_input)
        else (127 << j * 7) for j in range(8)
    )
    print(z)
    print(bin(z))
    print(len(bin(z)))

    n = 42068
    x = inner(1, z)
    for i in range(n):
        x = inner(x, z)

        # for testing conversion to iterative
        # assert flag_input == "a"
        # if n - i - 1 == 22000:
        #     assert x == 534474559382227254
        # if n - i - 1 == 21936:
        #     assert x == 61580383742727819
    print(x)
    return x


flag_input = input("Insert your flag: ")
s = [s_func(i, flag_input) for i in range(0, len(flag_input), 8)]

print(Type2(s).d(m).d(n).b)
if Type2(s).d(m).d(n).b == "=list(zip([l(i + 5*i) for i in x], [l(i + i) for i in x][::-1]))[::-1] = [(13969439442922757926633137632, 3251133470245911671632840864), (6919844817045365871489845728, 3067821989026578174692487328), (11408842561461143227463443808, 3766356150094573135206359136), (11299068421490417286376379488, 3802947530149782083826679648), (9203465938188223031329433888, 2306614948612889330244181216), (9753400381846729757945770272, 4656479823873291748257812704)]==":
    print("Good flag!")
else:
    print("Wrong flag!")
