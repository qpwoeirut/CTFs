from pwn import remote, process


for attempt in range(100):
    # r = process(["python3", "fizzbuzz100.py"])
    r = remote("be.ax", 31100)

    n = int(r.recvline().decode().split(' = ')[1])
    e = int(r.recvline().decode().split(' = ')[1])
    ct = int(r.recvline().decode().split(' = ')[1])

    val = (ct * pow(2, e, n)) % n
    r.recvuntil(b"> ")
    r.sendline(str(val).encode())
    resp = r.recvline().decode().strip()
    if len(resp) <= 9:
        continue

    pt = (int(resp) * pow(2, -1, n)) % n
    flag = pt.to_bytes(((pt.bit_length() + 7) // 8), "big")
    print(flag)
    if b"corctf" in flag.lower():
        break

    r.close()


# corctf{h4ng_0n_th15_1s_3v3n_34s13r_th4n_4n_LSB_0r4cl3...4nyw4y_1snt_f1zzbuzz_s0_fun}
