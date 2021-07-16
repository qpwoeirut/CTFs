# based on https://raw.githubusercontent.com/ValarDragon/CTF-Crypto/master/RSA/FranklinReiter.sage
# modified to use fast half-gcd algorithm http://web.cs.iastate.edu/~cs577/handouts/polydivide.pdf

# Franklin-Reiter attack against RSA.
# If two messages differ only by a known fixed difference between the two messages
# and are RSA encrypted under the same RSA modulus N
# then it is possible to recover both of them.

DIFF = 150276333

# Inputs are modulus, known difference, ciphertext 1, ciphertext2.
# Ciphertext 1 corresponds to smaller of the two plaintexts. (The one without the fixed difference added to it)
def franklinReiter(n, e, r, c1, c2):
    R.<X> = Zmod(n)[]
    f1 = X ^ e - c1
    f2 = (X + r) ^ e - c2
    print("f1, f2 done")
    g = compositeModulusGCD(f1, f2, X)
    print("g =", g)
    m = g.coefficients()[0]
    return Integer(n - m)


# gotta use the fast Half-GCD algorithm otherwise we'll be here for days
# http://web.cs.iastate.edu/~cs577/handouts/polydivide.pdf
def hgcd(a0, a1, x):
    if a0.degree() >= 4095:
        print("h d0, d1 =", a0.degree(), a1.degree())
    if a1.degree() * 2 <= a0.degree():
        return Matrix([[1, 0], [0, 1]])

    m = a0.degree() // 2
    b0 = a0 // (x ** m)
    b1 = a1 // (x ** m)
    R = hgcd(b0, b1, x)
    d, e = R * Matrix([[a0], [a1]])
    d, e = d[0], e[0]
    f = d % e
    g0 = e // (x ** (m // 2))
    g1 = f // (x ** (m // 2))
    S = hgcd(g0, g1, x)
    q = d // e
    return S * Matrix([[0, 1],[1, -q]]) * R


def compositeModulusGCD(a0, a1, x):
    if a0.degree() == a1.degree():
        g = fastGcd(a0, a0 % a1, x)
    else:
        g = fastGcd(a0, a1, x)
    g = g.monic()
    assert a0 % g == 0
    assert a1 % g == 0
    return g


def fastGcd(a0, a1, x):
    if a0.degree() >= 4095:
        print("g d0, d1 =", a0.degree(), a1.degree())
    if a0 % a1 == 0:
        return a1

    R = hgcd(a0, a1, x)
    b0, b1 = R * Matrix([[a0],[a1]])
    b0, b1 = b0[0], b1[0]
    # print("gcd b0 =", b0)
    # print("gcd b1 =", b1)
    c = b0 % b1
    if c == 0:
        return b1
    else:
        return fastGcd(b1, c, x)


def slowGcd(a, b):
    ct = 0
    while b != 0:
        print("gcd call", ct)
        ct += 1
        a, b = b, a % b
    return a.monic()


def testFranklinReiter():
    p = random_prime(2 ^ 512)
    q = random_prime(2 ^ 512)
    n = p * q  # 1024-bit modulus
    e = 11

    m = randint(0, n)  # some message we want to recover
    r = randint(0, n)  # random padding

    c1 = pow(m + 0, e, n)
    c2 = pow(m + r, e, n)
    recoveredM = franklinReiter(n, e, r, c1, c2)
    print("m =", m)
    print("m?=", recoveredM)
    assert recoveredM == m
    print("They are equal!")
    return True


def testGcd():
    R.<x> = Zmod(1000000007 * 1000000009)[]
    a0 = x^5 + x^4 + x^3 + x^2 + x + 1
    a1 = x^4 - 2*x^3 + 3*x^2 - x - 7
    r = compositeModulusGCD(a0, a1, x)
    print(r)
    print(type(r))
    print(a0 % r)
    print(a1 % r)


def main():
    n = 25898966400928827905718377946331123070958718286581765316565582158865227877882475404853218079499084099440419144196215764927720893687968939899067275095801562867742359933997487928281899714724738097735994026225339488710478292473051567851786254924548138570069406420407124627567648479424564834446192417334669768477661434992797176428220265984651288944265998446714590797833756720922745187467388408600309665467669255896919554072379878017822219455974525233467767926938557154083882126002952139561283708342676308894318951822068027821029295524097544028901807902120777407151278396388621981625398417573347316888458337381776303199529
    e = 1048577

    print("n =", n)
    print("e =", e)

    c1 = 11140520553087800834883326476247582685177207584737264356946559762068509060522907835540767944557089926814767920501376431871780404000550271362410228709616559148950928004959648199391157781102695421411667843970881959939688515679415870087007797819271601359811630724878746762862603629420061133824605384527474682526549557804674160851967543475275374840169790764048711047622418045734436512050742433282306694490346907876574514077395835974083376649624559301087384766644865104383786285302561584731767419571603248493060257358632833957327996996960955767927114473513709882904104552609194519132931270741118197821776138632855021619178
    c2 = 2922817623733019475805146570530296261205732600738503605503192845278422660686627490817081424885152809772315629265930072636690234953045955503581182067349322827011065359648958225896393305011175960879100475146203207801533980643261035226402857047007061320653920746872424363923515091038846823007819033456503365649022294092944985887626605207259444051959239244136999684366533551627508385114998024232490369665950339127904350803268889205463047713233591604324960184727360413931125906144631968128488876241314939855024305076160092193380013725939761970042406866169417457376487954247442308318888399299295082898238584625937490546472
    print(franklinReiter(n, e, DIFF, c1, c2))


if __name__ == '__main__':
    # testFranklinReiter()
    main()
    # testGcd()