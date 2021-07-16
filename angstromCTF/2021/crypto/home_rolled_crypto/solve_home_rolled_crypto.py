from pwn import remote, process

BLOCK_SIZE = 16
BITS = BLOCK_SIZE * 8

r = remote("crypto.2021.chall.actf.co", 21602)
# r = process(["python3", "chall.py"])

mapping = []

r.sendline("1")
r.recvuntil("encrypt: ")
r.sendline("00")

zero_enc = int(r.recvline().strip().rjust(BLOCK_SIZE * 2, b'0'), 16)

for bit in range(BITS):
    print("bit", bit)
    r.sendline("1")
    r.recvuntil("encrypt: ")
    r.sendline(hex(1 << bit)[2:].rjust(BLOCK_SIZE * 2, '0'))

    resp = int(r.recvline().strip().rjust(BLOCK_SIZE * 2, b'0'), 16)

    mapping.append([zero_enc & (1 << bit), resp & (1 << bit)])

r.sendline('2')
for i in range(10):
    print("query", i)
    r.recvuntil("Encrypt this: ")
    plain_hex = r.recvline().strip().rjust(BLOCK_SIZE * 2, b'0').decode()
    print("Encrypting", plain_hex)

    enc = 0
    for block in range(0, len(plain_hex), BLOCK_SIZE * 2):
        enc <<= BITS
        plain = int(plain_hex[block:block + BLOCK_SIZE * 2], 16)
        print(plain)

        for bit in range(BITS):
            enc += mapping[bit][(plain >> bit) & 1]

        print(enc)

    print("Encrypted:", hex(enc)[2:].rjust(BLOCK_SIZE * 2, '0'))
    r.sendline(hex(enc)[2:].rjust(BLOCK_SIZE * 2, '0'))

r.interactive()
