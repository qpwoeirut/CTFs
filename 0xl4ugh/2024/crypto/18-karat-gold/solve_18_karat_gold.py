import decimal
import math

import sympy
from sympy import solve

decimal.getcontext().prec = 100000


def egcd(a, b) -> tuple[int, int, int]:
    if b == 0:
        return a, 1, 0
    g, x, y = egcd(b, a % b)
    return g, y, x - y * (a // b)


# a(x + k_x * t) + b(y - k_y * t) = c  ->  x, y, k_x, k_y
def linear_diophantine(a: int, b: int, c: int) -> tuple[int, int, int, int]:
    g = math.gcd(a, b)
    if c % g != 0:
        raise ValueError("This equation has no solutions")

    g, x, y = egcd(a, b)
    x *= c // g
    y *= c // g

    return x, y, b // g, a // g


# ax^2 + bx == c  ->  x ± k
def quadratic(a: int, b: int, c: int) -> tuple[decimal.Decimal, decimal.Decimal]:
    print(a, b, c)
    x = decimal.Decimal(-b) / (2 * a)
    k = decimal.Decimal(b * b - 4 * a * -c).sqrt() / (2 * a)
    return x, k


def recover_t(x, y, k_x, k_y, n) -> int:
    t = sympy.Symbol("t")
    equation = sympy.Eq((x + k_x * t) * ((y - k_y * t) ** 2), n * n)
    for sol in solve(equation, t):
        val = sympy.N(sol, n=n.bit_length())
        if abs(round(val) - val) < 1e-32:
            return round(val)
    raise ValueError("no solution found")


def main():
    n = 100026398723423136138211894154034770288614747503532436949113002491494106449460190757774983380494662883662759514156256645607757097055204165793773561291660533234897999940438140951945432930055253075454227497805364166473784745078630521842820709173135372171348602971072033705585222846168895750439091425162323558127
    e = 65537
    ct = 1568671097457723819787489941826403201689202993746529685616897686173734046943320352422354980066671032177408880447270685136791693193733651891710827651302071320562871220718432052045282175335409755157337581925962423926659242477707423166254936675589677604179513253908097118555374028363276063702786366529316802165
    a = 940992394
    b = 1056172127
    c = -83352184464206596895079853804376347820376656184780616999453572407271581212088694294587647030709499949161611518661501735954949703018176640246608971540128107874013647954886640098097662450307282075562519555303842993692138863072691265079602556601831246304763233826916529716537144801413233305868817759015916745384224723253

    # unused?
    m_p = 9411642810892552148049212473648795594483585878823360151567704044323967502593629499495235135136297160880420131524539526598684481737338314346462033527462417
    m_q = 10627942510488999658618727120778291021034972789433196059586844790118133859622599307622817010095023809491096944097847704202759115514685740339727904782535265

    x, y, k_x, k_y = linear_diophantine(a, b, -c)
    assert a * x + b * y == -c
    assert a * (x + k_x) + b * (y - k_y) == -c

    t = recover_t(x, y, k_x, k_y, n)
    q = int(y - k_y * t)
    p = n // q
    assert p * q == n

    d = pow(e, -1, (p - 1) * (q - 1))
    m = pow(ct, d, n)
    print(m.to_bytes((m.bit_length() + 7) // 8, "big"))


if __name__ == '__main__':
    main()
