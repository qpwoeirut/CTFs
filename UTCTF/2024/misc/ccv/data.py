import ast
from misc.ccv.solve_ccv import date_valid, luhn

with open("known.txt", 'r') as f:
    known = [ast.literal_eval(line) for line in f]

known = [t for t in known if luhn(t[0]) and date_valid(t[1])]

known.sort(key=lambda t:(len(t[0]), t[0][::-1]))
bad = [t for t in known if t[-1] is False]
print(len(bad), len(known))

ct = 0
for x in known[:20000]:
    if x[-1] is False:
        ct += 1
    else:
        ct = 0
    print(ct, x)
