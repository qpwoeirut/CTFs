import itertools

target1 = "\x04\x13\x1d\t\x13\x0e\x00"[::-1]
xor_key = "power"
str1 = ''.join([chr(ord(a) ^ ord(b)) for a, b in zip(target1, itertools.cycle(xor_key))])


a = 9 - 4
b = 7 - 4
c = 11 - 4
d = 13 - 4

str2 = "reverse"[::-1]

inp = 5

xor = f"{str1}{a}{b}{c}{d}{str2}{inp}"

buf1 = 0x65102d202f303222.to_bytes(8, 'little')
buf2 = 0x343a19100a555352.to_bytes(8, 'little')
buf3 = 0x120801120301021c.to_bytes(8, 'little')
buf = buf1 + buf2 + buf3
flag = ''.join([chr(a ^ ord(b)) for a, b in zip(buf, itertools.cycle(xor))])

print(str1)
print(a, b, c, d)
print(str2)
print(inp)
print(flag.encode())
print(xor.encode())
