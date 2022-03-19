from string import ascii_uppercase, digits

with open("message.txt") as f:
    ct = map(int, f.read().strip().split())

chars = ascii_uppercase + digits + "_"

pt = ''.join([chars[x % 37] for x in ct])
print("picoCTF{" + pt + "}")
