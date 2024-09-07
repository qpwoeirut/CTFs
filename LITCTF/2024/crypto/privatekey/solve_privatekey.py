from typing import Iterator

import sympy


def continued_fractions(k: int, d: int) -> Iterator[int]:
    q = k // d
    yield q

    r = k % d

    while r > 0:
        k, d = d, r
        q = k // d
        yield q

        r = k % d


def convergents(k: int, d: int) -> Iterator[tuple[int, int]]:
    it = continued_fractions(k, d)
    v_1 = next(it)
    n_1 = v_1
    d_1 = 1
    yield n_1, d_1

    v_2 = next(it)
    n_2 = v_2 * v_1 + 1
    d_2 = v_2

    yield n_2, d_2

    for v_3 in it:
        n_3 = v_3 * n_2 + n_1
        d_3 = v_3 * d_2 + d_1

        yield n_3, d_3

        n_1, n_2 = n_2, n_3
        d_1, d_2 = d_2, d_3


def wiener_attack(e: int, n: int) -> tuple[int, int]:
    for pk, pd in convergents(e, n):
        if pk == 0:
            continue

        possible_phi = (e * pd - 1) // pk

        p = sympy.Symbol('p', integer=True)
        roots = sympy.solve(p ** 2 + (possible_phi - n - 1) * p + n, p)

        if len(roots) == 2:
            pp, pq = roots
            if pp * pq == n:
                return int(pp), int(pq)

    raise ValueError("attack failed")


def main():
    N = 91222155440553152389498614260050699731763350575147080767270489977917091931170943138928885120658877746247611632809405330094823541534217244038578699660880006339704989092479659053257803665271330929925869501196563443668981397902668090043639708667461870466802555861441754587186218972034248949207279990970777750209
    e = 89367874380527493290104721678355794851752244712307964470391711606074727267038562743027846335233189217972523295913276633530423913558009009304519822798850828058341163149186400703842247356763254163467344158854476953789177826969005741218604103441014310747381924897883873667049874536894418991242502458035490144319
    c = 71713040895862900826227958162735654909383845445237320223905265447935484166586100020297922365470898490364132661022898730819952219842679884422062319998678974747389086806470313146322055888525887658138813737156642494577963249790227961555514310838370972597205191372072037773173143170516757649991406773514836843206
    p, q = wiener_attack(e, N)

    tot = (p - 1) * (q - 1)
    d = pow(e, -1, tot)
    m = pow(c, d, N)

    print(m.to_bytes((m.bit_length() + 7) // 8))

    # Forgot I already had this implemented
    from utils.rsa.rsa_wiener_attack import recover_d
    d = recover_d(e, N)
    m = pow(c, d, N)

    print(m.to_bytes((m.bit_length() + 7) // 8))


if __name__ == '__main__':
    main()
