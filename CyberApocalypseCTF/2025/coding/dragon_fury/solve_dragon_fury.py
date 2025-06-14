import ast

damage = ast.literal_eval(input())
T = int(input())

cur = []
def recurse(i):
    if i == len(damage):
        if sum(cur) == T:
            print(cur)
            exit(0)
        return
    for x in damage[i]:
        cur.append(x)
        recurse(i + 1)
        cur.pop()

recurse(0)

# HTB{DR4G0NS_FURY_SIM_C0MB0_fe927a1c7c9a02dbdf106a7a3ee70dd9}