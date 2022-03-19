with open("message.txt") as f:
    ct = f.read().strip()

chunks = [ct[i:i+3] for i in range(0, len(ct), 3)]
flag = ''.join(a[2] + a[0] + a[1] for a in chunks)

print(flag)
