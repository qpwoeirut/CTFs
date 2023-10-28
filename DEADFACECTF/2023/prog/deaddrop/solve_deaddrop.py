# Password recovery:
# buA9kvZ=T_A}b[J8l:@ob_tviPZtb_<olOpxkvZ=T_=xju]olOpxkvZ=T_bxlu]olOpxkvZ=QIEE
import base64
import binascii

arr = ['empty', 'interest', 'current', 'valuable', 'influence', 'from', 'scolded', 'would', 'got', 'key', 'facility',
       'run', 'great', 'tack', 'scent', 'close', 'are', 'a', 'plan', 'counter', 'earth', 'self', 'we', 'sick', 'return',
       'admit', 'bear', 'cache', 'to', 'grab', 'domination', 'feedback', 'especially', 'motivate', 'tool', 'world',
       'phase', 'semblance', 'tone', 'is', 'will', 'the', 'can', 'global', 'tell', 'box', 'alarm', 'life', 'necessary']


def print_password(nums):
    if len(nums) < 1:
        print("Must provide a list of at least one number i.e. [1]")
    print("flag{{{}}}".format(" ".join([arr[num] for num in nums])))


def left_shift_with_cutoff(s, n, cutoff):  # turns out cutoff stuff is unnecessary
    return ''.join(chr(ord(char) - n) for char in s if ord(char) >= cutoff)


ords = [ord(char) for char in "buA9kvZ=T_A}b[J8l:@obtviPZtb<olOpxkvZ=T_=xju]olOpxkvZ=T_bxlu]olOpxkvZ=QIEE"]

for c in range(128):
    if c - 1 not in ords and c not in ords and c + 1 not in ords:
        continue
    decoded = left_shift_with_cutoff("buA9kvZ=T_A}b[J8l:@obtviPZtb<olOpxkvZ=T_=xju]olOpxkvZ=T_bxlu]olOpxkvZ=QIEE", 8, c)
    for i in range(len(decoded)):
        try:
            print(c, i, base64.b64decode(decoded[i:]))
        except binascii.Error:
            pass

# fourty-one two thirty-nine thirty-five thirty
# 41 2 39 35 30

print_password([41, 2, 39, 35, 30])

# guess that it should be 41, 2, 18, 39, 35, 30
print_password([41, 2, 18, 39, 35, 30])
