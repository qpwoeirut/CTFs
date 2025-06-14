# For some reason, running `sage solve_kewiri.sage` fails with import errors but running `sage` with venv activated and
# copy-pasting the script works. No clue why.


import math
import time

from pwn import remote
from sympy import isprime

r = remote("94.237.58.253", 48827)

print(r.recvuntil(b"You are given the sacred prime: p = ").decode(), end='')
p = int(r.recvline())
print(p)
assert p == 21214334341047589034959795830530169972304000967355896041112297190770972306665257150126981587914335537556050020788061

print(r.recvuntil(b"[1] How many bits is the prime p? > ").decode(), end='')
ans1 = str(p.bit_length())
print(ans1)
r.sendline(ans1.encode())

print(r.recvuntil(b"[2] Enter the full factorization of the order of the multiplicative group in the finite field F_p in ascending order of factors (format: p0,e0_p1,e1_ ..., where pi are the distinct factors and ei the multiplicities of each factor) > ").decode(), end='')
assert isprime(p)
order = p - 1
# From factordb, since p is always the same
factors = {
    2: 2, 5: 1, 635599: 1, 2533393: 1, 4122411947: 1, 175521834973: 1, 206740999513: 1, 1994957217983: 1,
    215264178543783483824207: 1, 10254137552818335844980930258636403: 1
}
assert math.prod(p_i ** e_i for p_i, e_i in factors.items()) == order
ans2 = '_'.join([f"{p_i},{e_i}" for p_i, e_i in factors.items()])
print(ans2)
r.sendline(ans2.encode())

print(r.recvuntil(b"[3] For this question, you will have to send 1 if the element is a generator of the finite field F_p, otherwise 0.\n").decode(), end='')
for _ in range(17):
    element = int(r.recvuntil(b'? > ', drop=True))
    print(f"{element}? > ", end='')
    ans3 = "1"
    for prime in factors:
        if pow(element, order // prime, p) == 1:
            ans3 = "0"
    print(ans3)
    r.sendline(ans3.encode())

print(r.recvuntil(b"The scholars present a sacred mathematical construct, a curve used to protect the most guarded secrets of the realm. Only those who understand its nature may proceed.\na = ").decode(), end='')
a = int(r.recvline())
b = int(r.recvline().decode().split('=')[1])
assert a == 408179155510362278173926919850986501979230710105776636663982077437889191180248733396157541580929479690947601351140
assert b == 8133402404274856939573884604662224089841681915139687661374894548183248327840533912259514444213329514848143976390134
print(f"{a}\n{b = }")

print(r.recvuntil(b"[4] What is the order of the curve defined over F_p? > ").decode(), end='')
ans4 = str(p)
print(ans4)
r.sendline(ans4.encode())

print(r.recvuntil(b"[5] Enter the full factorization of the order of the elliptic curve defined over the finite field F_{p^3}. Follow the same format as in question 2 > ").decode(), end='')

# Figuring out that name="z3" was required to avoid hanging took way too long...
order = EllipticCurve(GF(p^3, name="z3"), [a, b]).cardinality()
# More factordb
factors = {2: 2, 7: 2, p: 1, 2296163171090566549378609985715193912396821929882292947886890025295122370435191839352044293887595879123562797851002485690372901374381417938210071827839043175382685244226599901222328480132064138736290361668527861560801378793266019: 1}
assert math.prod(p_i ** e_i for p_i, e_i in factors.items()) == order
ans5 = '_'.join([f"{p_i},{e_i}" for p_i, e_i in factors.items()])
print(ans5)
r.sendline(ans5.encode())

print(r.recvuntil(b'The final trial awaits. You must uncover the hidden multiplier "d" such that A = d * G.\n').decode(), end='')
print(r.recvuntil(b"The chosen base point G has x-coordinate: ").decode(), end='')
G_x = int(r.recvline())
print(G_x)
print(r.recvuntil(b"The resulting point A has x-coordinate: ").decode(), end='')
A_x = int(r.recvline())
print(A_x)

print(r.recvuntil(b"[6] What is the value of d? > ").decode(), end='')
E = EllipticCurve(GF(p), [a, b])
G = E.lift_x(Integer(G_x))
A = E.lift_x(Integer(A_x))
ans6 = str(A.log(G))
print(ans6, time.time())
r.sendline(ans6.encode())

print(r.recvall(timeout=5).decode())

# HTB{Welcome_to_CA_2k25!Here_is_your_anomalous_flag_for_this_challenge_and_good_luck_with_the_rest:)_3d7058eda5d50b1113cbb07a550371aa}