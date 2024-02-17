import string

from pwn import process, remote

# r = process(["python3", "chal.py"])
r = remote("dhash.chal.irisc.tf", 10101)


def calculate_preimage(N: int, e: int, target: int) -> bytes:
    exp = pow(e, -1, N - 1)
    return pow(target, exp, N).to_bytes(256, 'big').hex().encode()


def main():
    line = r.recvline()
    if line == b'== proof-of-work: disabled ==\n':  # only remote has this
        line = r.recvline()
    params = line.decode().strip(string.ascii_letters + '()\n')
    N, e = map(int, params.split(', '))

    print("N =", N)
    print("e =", e)

    r.recvuntil(b'> ')

    # 2 ^ 4 ^ 6 = 0
    preimage = calculate_preimage(N, e, 2) + calculate_preimage(N, e, 4) + calculate_preimage(N, e, 6)
    r.sendline(preimage)
    print("sent", preimage)
    print(r.recvall(timeout=1).decode().strip())


if __name__ == '__main__':
    main()