from Crypto.Cipher import AES
from Crypto.Util.Padding import pad
from Crypto.Util.number import long_to_bytes

x0 = 2029673067800379268
y0 = 1814239535542268363
x1 = 602316613633809952
y1 = 1566131331572181793
p = 2525114415681006599
iv = '6959dbf6bf22344d452c3831a3b68897'


# 26525 38700 (101309 : 75098 : 1)
x0 = 65803
y0 = 81921
x1 = 6549
y1 = 112888
p = 116903
# 15499 15499
iv = 'ceb0b4f3d7b761c2338e585bdf88e466'


iv = bytes.fromhex(iv)


m_num = x0**2 * (y1 - y0) + x0 * (x0 * y0 - x1 * y0)
m_den = x0 * (y1 - y0) - x1 * y0 + x0 * y0
m = m_num * pow(m_den, -1, p) % p
m = 101309

a_num = y1 - y0
a_den = y1 - m * y0 * pow(x0, -1, p) % p
a = a_num * pow(a_den, -1, p) % p
a = 26525

v = (m * a**3 + x1) % p

print("m =", m)
print("a =", a)
print("v =", v)

k = pad(long_to_bytes(v**2), 16)

enc = bytes.fromhex('a490e177c3838c8f24d36be5ee10e0c9e244ac2e54cd306eddfb0d585d5f27535835fab1cd83d26a669e6c08096b58cc4cc4cb082f4534ce80fab16e21f119adc45a5f59d179ca3683b77a942e4cf4081e01d921a51ec3a3a48c13f850c04b80c997367739bbde0a5415ff921d77a6ef')

cipher = AES.new(k, AES.MODE_CBC, iv=iv)
dec = cipher.decrypt(enc)
print(f"dec = '{dec.hex()}'")
print(dec)