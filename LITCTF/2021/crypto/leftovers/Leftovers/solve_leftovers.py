import random
import sympy
from sympy.ntheory.modular import crt
from math import prod

random.seed(1337)

mods = [sympy.prevprime(random.randint(1, 4e10)) for _ in range(12)]
values = [16751036754, 7441743891, 13537409447, 12455208971, 16901669565, 15060041617, 15538665605, 192375025, 2176355740, 21877084789, 3184436468, 13214581420]

x0, mod = crt(mods, values)

while True:
    print(x0)
    flag = x0.to_bytes(100, "big").strip(b'\x00')
    if flag.startswith(b"flag"):
        print(flag)
        break
    x0 += mod
