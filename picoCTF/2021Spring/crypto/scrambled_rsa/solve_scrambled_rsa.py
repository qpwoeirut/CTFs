from utils.flag_strings import leet_flag_chars_lower

from pwn import remote

r = remote("mercury.picoctf.net", 4484)


def encrypt(m: str) -> str:
    assert len(m) > 0
    r.sendline(m)
    ret = r.recvline().strip().decode().split(': ')[2]
    assert ret.isnumeric()
    return ret


flag = r.recvline().strip().decode().split(': ')[1]
n = int(r.recvline().strip().decode().split(': ')[1])
e = int(r.recvline().strip().decode().split(': ')[1])

# print(flag)
print(n)
print(e)

table = dict()
cur = ""
while len(cur) == 0 or cur[-1] != '}':
    print("cur:", cur)
    for c in leet_flag_chars_lower:
        print(c, end='', flush=False)
        enc = encrypt(cur + c)
        for i in range(len(cur)):
            enc = enc.replace(table[cur[:i+1]], '')
        if enc in flag:
            cur += c
            table[cur] = enc
            print()
            break

print("FLAG:", cur)
