with open("leak/usernames.txt") as uf, open("leak/passwords.txt") as pf:
    usernames = uf.read().strip().split()
    passwords = pf.read().strip().split()

N = len(usernames)

for i in range(N):
    if usernames[i] == "cultiris":
        flag = passwords[i]
        print(flag)
