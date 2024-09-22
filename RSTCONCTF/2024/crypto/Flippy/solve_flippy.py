import itertools

from pwn import remote

r = remote("host5.metaproblems.com", 7040)

init = "xxxxxxxx"
target = "puts ''+FLAG+''"
current = f"puts '{init}'"

assert len(target) == len(current)

r.sendline(b'1')
r.recvuntil(b"> Enter a message: ")
r.sendline(init.encode())

enc_hex = r.recvline(keepends=False).decode().removeprefix("Encrypted message: ")
enc = bytes.fromhex(enc_hex)

iv, enc = enc[:16], enc[16:]


to_xor = [ord(a) ^ ord(b) for a, b in zip(target, current)]
to_xor = itertools.chain(to_xor, itertools.cycle([0]))
iv_payload = bytes([a ^ b for a, b in zip(iv, to_xor)]).hex()

payload = iv_payload + enc.hex()

print(payload)
print(enc_hex)

r.sendline(b'2')
r.sendline(payload.encode())
print(r.recvall(timeout=1).decode().strip())
