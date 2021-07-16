# https://stackoverflow.com/a/57406014

from binascii import unhexlify
from decimal import Decimal, getcontext
from Crypto.Cipher import AES

getcontext().prec = 1000

enc = unhexlify("838371cd89ad72662eea41f79cb481c9bb5d6fa33a6808ce954441a2990261decadf3c62221d4df514841e18c0b47a76")

pts = [
    ("45702021340126875800050711292004769456.2582161398", "310206344424042763368205389299416142157.00357571144"),
    ("55221733168602409780894163074078708423.359152279", "347884965613808962474866448418347671739.70270575362"),
    ("14782966793385517905459300160069667177.5906950984", "340240003941651543345074540559426291101.69490484699")
]
pts = [(Decimal(x), Decimal(y)) for x, y in pts]

p1, p2, p3 = pts

ax = (p1[0] + p2[0]) / 2
ay = (p1[1] + p2[1]) / 2
ux = p1[1] - p2[1]
uy = p2[0] - p1[0]
bx = (p2[0] + p3[0]) / 2
by = (p2[1] + p3[1]) / 2
vx = p2[1] - p3[1]
vy = p3[0] - p2[0]
dx = ax - bx
dy = ay - by
vu = vx * uy - vy * ux
assert vu != 0, "points cannot be collinear"
g = (dx * uy - dy * ux) / vu
keynum = bx + g * vx
ivnum = by + g * vy

print(keynum)
print(ivnum)

keynum = round(keynum)
ivnum = round(ivnum)

key = int.to_bytes(keynum, 16, "big")
iv = int.to_bytes(ivnum, 16, "big")

cipher = AES.new(key, AES.MODE_CBC, iv=iv)
flag = cipher.decrypt(enc)

if flag.isascii():
    print(flag)
