import random
from hashlib import sha256
from pwn import xor


def test():
    from decimal import Decimal, getcontext

    getcontext().prec = int(1000)
    # test for 1000000x^9 + 1000001x^8 + 1000002x^7 + 1000003x^6 + 1000004x^5 + 1000005x^4 + 1000006x^3 + 1000007x^2 + 1000008x + 2^100 = 0
    r = Decimal("-476.6638228773329098992310176410083808974292275949695517089588920553329211856613691751575959876117848293393488292130781809320200334973687476258470202810657420913270860353742069317062384789867006756616657912951439657806387598803499279978640987148096143771615884775077628550356210129695345730137601684785242264866929418140856615446516808209718090951051700669133374534739290464783020781470129439540113412104766166974399174282506021475526922379645238988703075404894375650143588367666739210753340341223280614627254861747763283788794269998806048999510282389254711309390365563832533263668042684309569091909593842683408304780368502923443362127442582")
    mult = 10 ** 300
    degree = 9

    A = Matrix(ZZ, root_matrix([int(int(mult) * r ** (degree - d)) for d in range(degree + 1)]))
    print(A.LLL())


# https://jgeralnik.github.io/writeups/2021/08/12/Lattices/
def Babai_closest_vector(B, target):
    # Babai's Nearest Plane algorithm
    M = B.LLL()
    G = M.gram_schmidt()[0]
    small = target
    for i in reversed(range(M.nrows())):
        c = ((small * G[i]) / (G[i] * G[i])).round()
        small -= M[i] * c
    return target - small


def root_matrix(vals: list[int]) -> list[list[int]]:
    mat = [[1 if r == c else 0 for c in range(len(vals))] for r, v in enumerate(vals)]
    for r, v in enumerate(vals):
        mat[r][-1] = v
    return mat


def solve(seed: list[int], total: int, degree: int) -> vector:
    total -= x * k ** degree

    s = total % mod

    # https://crypto.stackexchange.com/questions/106770/lll-on-knapsack-eque-problem
    scale = 10**100
    g = gcd(k, mod)
    assert s % g == 0
    s = (s // g * pow(k // g, -1, mod)) % mod

    new_deg = degree - 1

    mat = root_matrix([scale * k ** (new_deg - d) for d in range(new_deg + 1)] + [scale * s, scale * mod])

    MULT = 1 << 80
    mat[-2][-2] *= MULT
    A = Matrix(ZZ, mat)
    vec = Babai_closest_vector(A, vector(seed + [-MULT, 0]))
    assert vec[-2] == -MULT
    assert vec[-1] == 0
    return vec[:degree]


def test_mod():
    # coeffs = [1036424608443065, -248663784003349, -721765477517853, 501204069885419, 187130788725433, -1103085400997944, -114615423476277, 787564864312811, -625941230265278]
    # coeffs = [-513509516223129475, -806701737104814268, 160779435005402667, -364760945292067595, 37179942362960570, 432267839884434445, -441376458029569238, -1079218214605121221, -688544063339044586]

    # coeffs = [-108501422666933660807, -110635217646864005269, -120817076593928840321, -58706576489641529609, -12176639756925845340, -59037813446315344456, 57030899916787080797, 74937342523298817176, -34808331275699318034]
    coeffs = [117330249771469003583, 85571228208234424811, 7421072654590548534, 106704185450873723589, -45631616074527674969, 106560575461590680511, 113107943462526718279, -99090033844253596399, 16621362930184902884]

    total = x
    for c in coeffs:
        total = k * (total + c)
    total %= mod
    ans = solve(total, len(coeffs))
    print(check(ans))
    print(total)


mod = 1 << 620  # we want this to be 600 without the algorithm breaking
x = 2093485720398457109348571098457098347250982735
k = 1023847102938470123847102938470198347092184702



def check(ans) -> int:
    val = x
    for c in ans:
        val = k * (val + c)
    return val % mod


def flag_for(ans: list[int]) -> bytes:
    key = sha256("".join(str(i) for i in ans).encode()).digest()
    enc = bytes.fromhex("4ba8d3d47b0d72c05004ffd937e85408149e13d13629cd00d5bf6f4cb62cf4ca399ea9e20e4227935c08f3d567bc00091f9b15d53e7bca549a")
    return xor(key, enc)


def main():
    degree = 9
    total = 2957389613700331996448340985096297715468636843830320883588385773066604991028024933733915453111620652760300119808279193798449958850518105887385562556980710950886428083819728334367280
    answers = {}

    while True:
        seed = [random.randint(-2 ** 66, 2 ** 66) for _ in range(degree)]
        ans = solve(seed, total, degree)
        if max([math.log2(abs(x)) for x in ans]) >= 67:
            continue
        assert check(ans) == total

        hashable = str(list(ans))
        if hashable in answers:
            continue

        if b'CTF' in flag_for(ans):
            print(flag_for(ans))
            break

        answers[hashable] = ans

        if len(answers) % 100 == 0:
            print(len(answers))
            with open("output.txt", 'w') as out:
                for answer in answers.values():
                    print(answer, file=out)

    for answer in answers.values():
        print(flag_for(answer))


if __name__ == '__main__':
    # test()
    # test_mod()
    main()
