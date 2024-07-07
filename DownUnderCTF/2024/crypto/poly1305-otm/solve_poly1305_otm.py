import pwn
from pwn import remote

MOD = (1 << 130) - 5
BLOCK = 16

# https://github.com/Mbed-TLS/mbedtls/blob/development/library/poly1305.c#L245
R_MASK = 0x0FFF_FFFC_0FFF_FFFC_0FFF_FFFC_0FFF_FFFF
# R_MASK = 0xFFFF_FF0F_FCFF_FF0F_FCFF_FF0F_FCFF_FF0F
assert R_MASK.bit_count() == 106


# based on https://cr.yp.to/mac/poly1305aes.py, found in https://cr.yp.to/mac.html
def poly1305(msg: bytes, rval: int, s: int) -> int:
    # Python reference implementation doesn't have the masking for r  >:(
    rval &= R_MASK

    blocks = [msg[i: i + BLOCK] for i in range(0, len(msg), BLOCK)]
    assert len(blocks) == (len(msg) + 15) // 16

    tot = 0
    for i, block in enumerate(blocks):
        sub = block + b"\x01"
        num = int.from_bytes(sub, "little")
        print(num, block)
        tot = (tot + num) * rval % MOD
    assert tot < MOD
    return (tot + s) % (1 << 128)


# find all x such that (x % MOD) % (2^130 - 5) = x0 where x < 2^8 * 2^128
def options_for(x0: int) -> list[int]:
    single_mod = [x0 + i * (1 << 128) for i in range(4)]
    options = [n + i * MOD for i in range(256) for n in single_mod]
    return [opt for opt in options if opt < (1 << 136)]


# I spent a few hours working under the assumption that s=0, but it's actually random uninitialized values
# a * r + s = c (mod p)
def c_for(r: pwn.tube, a: int) -> list[int]:
    assert a >= 256
    # r.sendline(b'1')
    # r.recvuntil(b"> message: ")
    # r.sendline(a.to_bytes((a.bit_length() + 7) // 8, "little").hex().encode())
    # resp = r.recvline().strip().decode()
    if a == 256:
        resp = "0a24b374bf53734eec89e8d5b71b1177"
    elif a == 257:
        resp = "a9b71d84bbc6c15af052b4dd7b2c8881"
    else:
        raise ValueError("no")

    # I originally had int(resp[::-1], 16) and spent around 6 hours looking for a bug before realizing that little
    # endian doesn't work like that
    c0 = int.from_bytes(bytes.fromhex(resp), "little")
    return [c0 + i * (1 << 128) for i in range(4)]


# au + bv = g
def egcd(a: int, b: int) -> tuple[int, int, int]:
    assert a > 0 and b > 0
    old_r, r = a, b
    old_s, s = 1, 0
    old_t, t = 0, 1

    while r > 0:
        quotient = old_r // r
        old_r, r = r, old_r - quotient * r
        old_s, s = s, old_s - quotient * s
        old_t, t = t, old_t - quotient * t

    return old_r, old_s, old_t


# a_1 * r_1 + s = c_1 (mod p)
# a_2 * r_2 + s = c_2 (mod p)
# a_1 * r_1 - a_2 * r_2 = c_1 - c_2 (mod p)
# https://math.stackexchange.com/questions/404801/linear-diophantine-equation-modulo-n
def find_s(a: int, b: int, c_1: int, c_2: int) -> list[int]:
    g, u, v = egcd(a, b)
    assert a * u + b * v == g == 1

    # TODO: might need to list all solutions
    r_opts = [(v * (c_1 - c_2) + i * a) % MOD for i in range(-300, 301)]
    r_opts = [r for r in r_opts if r < (1 << 128)]
    s_opts = [(c_2 - b * r) % MOD for r in r_opts]
    return [s for s in s_opts if s < (1 << 128)]


def find_r(a: int, s: int, c: int) -> int:
    return (c - s) * pow(a, -1, MOD) % MOD


def main():
    r = remote("2024.ductf.dev", 30017)

    a_1 = 256
    c_1_opts = c_for(r, a_1)
    a_2 = 257
    c_2_opts = c_for(r, a_2)
    print(c_1_opts)
    print(c_2_opts)

    s_opts = [find_s(a_1, a_2, c_1, c_2) for c_1 in c_1_opts for c_2 in c_2_opts]
    s_opts = [s for s_list in s_opts for s in s_list]
    print(s_opts)

    rs_opts = [(find_r(a_1, s, c_1), s) for s in s_opts for c_1 in c_1_opts]
    rs_opts = [(r, s) for r, s in rs_opts if r < (1 << 128)]
    for rval, s in rs_opts:
        print("r =", hex(rval)[2:].zfill(32))
        print("s =", hex(s)[2:].zfill(32))
        # assert poly1305(b'\x00', rval, s) in c_2_opts, f"{poly1305(b'\x00', rval, s)} {c_2_opts}"

        # r.sendline(b'2')
        # r.recvuntil(b"> mac: ")
        #
        # possible_mac = poly1305(b"I have broken Poly1305 one time MAC!", rval, s)
        #
        # as_hex = possible_mac.to_bytes(16, "little").hex().zfill(32)
        # print(as_hex)
        # r.sendline(as_hex.encode())
        # result = r.recvline().decode().strip()
        # print(result)

    print(r.recvall(timeout=0.5).decode().strip())


if __name__ == '__main__':
    main()
