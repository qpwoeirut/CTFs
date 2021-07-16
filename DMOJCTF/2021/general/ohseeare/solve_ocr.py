# https://onlineocr.org/
# https://www.newocr.com/
from hashlib import sha1
import re

with open("data.txt") as f:
    data = f.read()
with open("data2.txt") as f:
    data2 = f.read()

# tmp = data
data = re.sub(r"[^a-zA-Z]", '', data)
# print(data == tmp)
# print(data)
data2 = re.sub(r"[^a-zA-Z]", '', data2)

flag = sha1(data.encode()).hexdigest()
flag2 = sha1(data2.encode()).hexdigest()

print("ctf{" + flag + "}")
print("ctf{" + flag2 + "}")