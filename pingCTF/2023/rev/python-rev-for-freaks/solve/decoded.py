import sys

sys.setrecursionlimit(50000)

v = type("m", (), {"__init__": lambda a, b: setattr(a, "b", b), "s": lambda a, b: v(b(a.b).b)})
c = type("l", (), {"__init__": lambda a, b: setattr(a, "b", b), "d": lambda a, b: a if v(a.b).s(b).b != b(a.b).b else b(a.b)})
m = lambda x: c(f"{list(zip([l(i + 5*i) for i in x], [l(i + i) for i in x][::-1]))[::-1] = }")
n = lambda x: c("{x[';]:=^450}".format(x={'''';''': x}))
l = lambda x: (12648430 ^ (x + 3735928559)) * 3862272608
flag_input = input("Insert your flag: ")
s = [
    (
        lambda z, f, x, n: x if n == 0
        else f(
            z, f, (
                lambda m, n: (
                    lambda x: int(x[len(x) - 59:], 2) + int(x[:len(x) - 59], 2) if len(x) - 59 > 0 else int(x, 2)
                )(bin(m * n))
            )(x, z),
            n - 1
        )
    )(
        sum(
            (ord(flag_input[i + j]) << j * 7) if i + j < len(flag_input)
            else (127 << j * 7) for j in range(8)
        ),
        (
            lambda z, f, x, n: x if n == 0
            else f(
                z, f, (
                    lambda n, m: (
                        lambda x: int(x[len(x) - 59:], 2) + int(x[:len(x) - 59], 2) if len(x) - 59 > 0 else int(x, 2)
                    )(bin(n * m))
                )(x, z), n - 1
            )
        ),
        1,
        420_69
    ) for i in range(0, len(flag_input), 8)
]
if c(s).d(m).d(n).b == "=list(zip([l(i + 5*i) for i in x], [l(i + i) for i in x][::-1]))[::-1] = [(13969439442922757926633137632, 3251133470245911671632840864), (6919844817045365871489845728, 3067821989026578174692487328), (11408842561461143227463443808, 3766356150094573135206359136), (11299068421490417286376379488, 3802947530149782083826679648), (9203465938188223031329433888, 2306614948612889330244181216), (9753400381846729757945770272, 4656479823873291748257812704)]==":
    print("Good flag!")
else:
    print("Wrong flag!")
