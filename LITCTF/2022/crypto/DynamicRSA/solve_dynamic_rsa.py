import random
from pwn import remote, process
from sympy import nextprime
from sympy.ntheory.modular import crt

RSA_E = 65537
random.seed(RSA_E)

N = 500
REQUIRED = 1 << 400


def gcd(a, b, dat: str) -> str:
    if a == 0:
        return dat
    if b == 0:
        return dat
    return gcd(b % a, a, dat + ".,"[(b // a) & 1])


def calculate_values(e: int, data: str) -> int or None:
    results = []
    for i in range(e):
        dat = gcd(i, e, "")
        if dat == data[1:]:
            results.append(i)
            if len(results) > 1:
                return None
    # print(e, results)
    return results[0]


def handle_interaction(r) -> str:
    line = r.recv().decode().strip()
    # print(line, 2)
    r.sendline(b"2")

    loading_line = r.recvline().decode().strip()
    # print(loading_line)

    line = r.recv().decode().strip()
    # print(line, "a")
    r.sendline(b"a")

    line = r.recvline().decode().strip()
    # print(line)

    assert loading_line.startswith("Loading")
    return loading_line


def main():
    # r = process(["python3", "DynamicRSA.py"])
    r = remote("litctf.live", 31792)
    line = r.recvline().decode().strip()
    # print(line)
    line = r.recvline().decode().strip()
    # print(line)

    e_list = []
    v_list = []
    prod = 1
    used = set()
    while prod < REQUIRED:
        # print(prod.bit_length())
        e = nextprime(random.randint(1, 100000))
        loading_line = handle_interaction(r)

        val = calculate_values(e, loading_line.strip("Loading"))
        if val is not None and e not in used:
            used.add(e)
            e_list.append(e)
            v_list.append(val)
            prod *= e
            print(prod.bit_length(), e, val)

    phi, mod = crt(e_list, v_list)
    print(phi, mod)
    assert mod.bit_length() > 400

    d = pow(RSA_E, -1, phi)
    print(d)

    r.sendline(b"1")
    r.sendline(str(d).encode())
    print(r.recvall(3).decode().strip())


if __name__ == '__main__':
    main()
