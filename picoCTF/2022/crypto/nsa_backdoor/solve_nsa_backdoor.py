from math import gcd

from sympy import factorint
from sympy.ntheory import pollard_pm1
from sympy.ntheory.modular import crt
from utils.basics import hex_to_ascii


# david wong's paper https://eprint.iacr.org/2016/644.pdf


# from page 162, Algorithm 4.79 of Handbook of Applied Cryptography
def find_order(a, p, q):  # find the multiplicative order of a mod n
    n = p * q
    totient = (p - 1) * (q - 1)

    order = totient
    for factor, power in factorint(totient).items():
        order //= factor ** power
        a1 = pow(a, order, n)
        while a1 != 1:
            a1 = pow(a1, factor, n)
            order *= factor

    assert pow(a, order, n) == 1

    return order


# https://en.wikipedia.org/wiki/Pollard%27s_rho_algorithm_for_logarithms
# https://github.com/mimoo/GoNTL/blob/master/discrete_logarithm.go
# Handbook of Applied Cryptography Algorithm 3.60
def pollard_rho_logarithm(alpha, beta, n, order):
    # print(alpha, beta, n, order)

    def new_xab(x, a, b):
        if x % 3 == 0:
            x = (x * x) % n
            a = (a * 2) % order
            b = (b * 2) % order
        elif x % 3 == 1:
            x = (x * beta) % n
            b = (b + 1) % order
        else:
            x = (x * alpha) % n
            a = (a + 1) % order
        return x, a, b

    slow_x, slow_y, slow_z = 1, 0, 0
    fast_x, fast_y, fast_z = 1, 0, 0
    while True:
        slow_x, slow_y, slow_z = new_xab(slow_x, slow_y, slow_z)
        fast_x, fast_y, fast_z = new_xab(fast_x, fast_y, fast_z)
        fast_x, fast_y, fast_z = new_xab(fast_x, fast_y, fast_z)
        # print(slow_x, slow_y, slow_z, " | ", fast_x, fast_y, fast_z)

        if slow_x == fast_x:
            dy = (fast_y - slow_y) % order
            dz = (slow_z - fast_z) % order
            if dz == 0:
                raise ValueError()
            if gcd(dz, order) > 1:
                print(dy, dz, order, sep='\n')
                return None
            result = (dy * pow(dz, -1, order)) % order
            if result < 0:
                result += order
            return result


def main():
    # print(pollard_rho_logarithm(alpha=2, beta=228, n=383, order=191))
    # print(pollard_rho_logarithm(alpha=2, beta=5, n=1019, order=1018))
    n = 0x5bf9961e4bcfc88017e1a9a40958af5eae3b3ee3dcf25bce02e5d04858ba1754e13e86b78a098ea0025222336df6b692e14533dad7f478005b421d3287676843f9f49ffd7ebec1e8e43b96cde7cd28bd6fdf5747a4a075b5afa7da7a4e9a2ccb26342799965f3fb6e65e0bb9557c6f3a67568ccbfaaa7e3d6c5cb79dd2f9928111c3183bf58bd91412a0742bbfb3c5cebfb0b82825da0875c5ee3df208ce563f896d67287c8b9aad9943dd76e5eae1fc8abd473ec9f9e4f2b49b7897954ca77b8f00ed51949c7e4f1f09bd54b830058bd7f4da04e5228250ba062ec0e1d19fb48a05333aada60ecdfc8c62c15773ed7e077edba71621f6a6c10302cc9ed26ec9
    c = 0x2475123653f5a4b842e7ac76829e896450126f7175520929a35b6a4302788ceff1a605ed30f4d01c19226e09fc95d005c61320d3bbd55cfebbc775332067ac6056c1969282091856eaa44ccaf5738ac6409e865bbd1186d69f718abd2b3a1dd3dc933a07ca687f0af9385406fd9ee4fa5f701ad46f0852bf4370264c21f775f1e15283444b3bf45af29b84bb429ed5a17adc9af78aee8c5351434491d5daf9dd3ce3cf0cd44b307eb403f0e9f482dd001b25ed284c4e6c1ba2864e5a2c4b1afe4161426cc67203f30553c88d7132aef1337eca00622b47cb7a28195f0e3a2ab934e6163b2941a4631412e13b1a72fe34e6480fada9af4dae14f2608805d61ee

    # locally generated test
    # n = 0x5bbdd2bb70758af61b3f5230f459be9689065e0fd13eb15c5ce12bd72b70661f43bc782af0a981d942c700b58c0973ed689a88b081d0b83afbf1215d03e1b7c78693e01e82633c05958d0fbdb096beb1784c43621c1379d9656dcb1dae47e851ec3a4bc3a4e28bba74b75f49f29c8943ae15035e784541b36ffc0f5ac5d1e798c02b33f77ad56c2f0f91954c63ceca784e10fb659fa273b505f79762c81064f82b21ffffe65e67de49d0467ca082a82cb4242ef94766d9f600d2c73441249f5d93d1b7e7b7f089da86e11e1772da0a4ef269c9a219bb03609d2519d8024caec617eed07b3b4aef18a46a1decf3b378e30b72825ce31753e708cd252e69d93c39
    # c = 0x18a540be01b9186dc632fcc4b9f318f6111c5e775a9395a67fb837af5c188bb49b05d3872c228f1734a6c3defa9851a389b2e9bdfd28e65860be87e336fe7c58ca3df1f0f8deaa9dcdd83ef7b84cfda92b7de799113c7ced4d6b8d879ce267da564ee37dfa9b045098a6a6f477f6ea02a85cdbea9955c573baac32210d993aa23a442f184e0a9eb49cdcfe5990da8b2843524fd8b498e5a21dae0aacee3b8d194442a9f5855f42b3a947cf8d471e8b817fa6fe6d2139312d58ea5ce046d53a28f1c73e356a46ba6cfeb3e0b094135038b1ea7c0b7029a19a4be247a57c6c2fdfb0cc154d12bd8f106c92bebe480f4efa079b067f9f92db5275865d763b87bcc8
    # test_flag = 2756326214310569388162120447013565128312970436797794968445

    g = 3

    p = pollard_pm1(n, B=2 ** 16)
    q = n // p
    assert p * q == n
    print("p =", p)
    print("q =", q)

    totient = (p - 1) * (q - 1)
    order = find_order(a=g, p=p, q=q)  # make sure to use the right order! 3 isn't a generator of the group
    print("order =", order)

    # https://en.wikipedia.org/wiki/Pohlig%E2%80%93Hellman_algorithm
    mods = []
    residues = []
    for factor, power in factorint(totient).items():
        fp = factor ** power
        alpha = pow(g, totient // fp, n)
        beta = pow(c, totient // fp, n)

        result = pollard_rho_logarithm(alpha=alpha, beta=beta, n=n, order=order)
        if result is not None:
            residues.append(result)
            mods.append(factor**power)
    print("calculated residues for prime powers")

    exp, mod = crt(mods, residues)
    exp = int(exp)
    mod = int(mod)

    print(order / mod)

    print("exp =", exp)
    print("mod =", mod)

    cur = pow(g, exp, n)
    a = pow(g, mod, n)
    for k in range(int(1e9)):
        if k % 100000 == 0:
            print(f"Finished {k} iterations")
            print(exp + k * mod)
            print(order)
            print(exp + k * mod - order)
        if cur == c:
            flag = (exp + k * mod) % order
            assert pow(g, flag, n) == c
            print(k)
            print(flag)
            # print(test_flag)
            print(hex_to_ascii(flag))

            break

        cur = (cur * a) % n


if __name__ == '__main__':
    main()
