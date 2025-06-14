import sympy
import tqdm
from pwn import process, remote, tube

BITS = 1024
BYTES = BITS // 8
e = 65537

RNG_ITERS = 100
FLAG_ITERS = 1500 - RNG_ITERS
assert FLAG_ITERS > BITS


def query(r: tube, ct: int) -> int:
    r.sendline(b'2')
    r.sendline(hex(ct)[2:].encode())
    r.recvuntil(b"their answer: ")

    bit = int(r.recvline().decode())
    assert bit in (0, 1)
    return bit


def read_constants(r: tube) -> tuple[int, int, int]:
    r.recvuntil(b"Behold its power: M = ")
    M = int(r.recvline().decode())

    r.sendline(b'1')
    r.recvuntil(b"insight: n = ")
    n = int(r.recvline().decode())
    r.recvuntil(b"sealed: ")
    enc = int(r.recvline().decode())

    return M, n, enc


def find_rng_state(r: tube, M: int) -> int:
    states = [state * state % M for state in sympy.sieve.primerange(2 ** 15)]
    for _ in tqdm.tqdm(range(1, RNG_ITERS), desc="Recovering RNG state"):
        bit = query(r, 1)
        # bit == 0 -> oracle returned that decode(1) = 1 < n/2
        # bit == 1 -> oracle returned that decode(1) = 1 % 1 == 1
        # Invert the result to get the rng output
        states = {state * state % M for state in states if state % 2 != bit}
    states = list(states)
    assert len(states) == 1, f"{states = }"
    return states[0]


def recover_flag(r: tube, state: int, M: int, n: int, enc: int, lo: int, hi: int) -> tuple[int, int]:
    lo_, hi_ = 0, n
    it = 0
    while True:
        mid = (lo_ + hi_) // 2
        if mid < lo:
            lo_ = mid
        elif mid > hi:
            hi_ = mid
        else:
            break
        it += 1

    for _ in tqdm.tqdm(range(FLAG_ITERS), desc="Recovering flag"):
        if state % 2 == 0:  # oracle will return lsb
            it += 1
            bit = query(r, enc * pow(2, it * e, n) % n)
            if bit == 0:
                hi_ = (lo_ + hi_) // 2
            else:
                lo_ = (lo_ + hi_) // 2
        else:  # oracle will return decode(c) > n/2. not sure how to use this, but it's not necessary
            query(r, 1)

        state = state * state % M

    print(lo_.to_bytes(BYTES))
    print(hi_.to_bytes(BYTES))
    print(lo_)
    print(hi_)

    return lo_, hi_


def run_iteration(lo: int = 0, hi: int = None) -> tuple[int, int]:
    r = remote("83.136.252.133", 57860)
    # r = process(["python3", "server.py"])
    M, n, enc = read_constants(r)
    print(f"{M = }")
    print(f"{n = }")
    print(f"{enc = }")

    if hi is None:
        hi = n

    state = find_rng_state(r, M)
    print(f"{state = }")
    lo, hi = recover_flag(r, state, M, n, enc, lo, hi)
    r.close()

    return lo, hi



def main():
    # lo, hi = run_iteration()

    lo = 9782038732516057040242996585062262329647055940806383330019831797424438273860401709940076021210547932070425234924548031632124677562787416531205540898429501152967697481587852072763054667787909447288276923245610509343496918285428404241135977242414863603361416234651895011250588
    hi = 9782038732516057040242996585062262329647055940806383330019831797424438273860401709940076021210547932070425234924548031632124677562787416531205540898429501152967697481587852072763054779529938302438648792341631114809746760048335174797409337203803581603409967606081748569896450
    while lo < hi:
        lo, hi = run_iteration(lo, hi)

    flag = lo.to_bytes(BYTES)
    print(flag)


if __name__ == '__main__':
    main()


# Output:
# b'\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00HTB{1_l0v3_us1ng_RS4_0r4cl3s___3v3n_4_s1ngl3_b1t_1s_3n0ugh_t0_g3t_m3_t0_3ld0r14!_0851ece37047b31309f7005b38a0172f\t'
# HTB{1_l0v3_us1ng_RS4_0r4cl3s___3v3n_4_s1ngl3_b1t_1s_3n0ugh_t0_g3t_m3_t0_3ld0r14!_0851ece37047b31309f7005b38a0172f}