import itertools
from collections import defaultdict

import chall

mapping = defaultdict(list)

for bigram_tuple in itertools.product(chall.characters, repeat=2):
    bigram = ''.join(bigram_tuple)
    mapping[chall.bigram_multiplicative_shift(bigram)].append(bigram)

print(mapping)

enc = "jlT84CKOAhxvdrPQWlWT6cEVD78z5QREBINSsU50FMhv662W"
flag = ""
for i in range(0, len(enc), 2):
    options = mapping[enc[i:i + 2]]
    assert len(options) == 3

    not1 = chall.not_the_flag[i:i+2]
    not2 = chall.also_not_the_flag[i:i+2]

    correct = [opt for opt in options if opt not in (not1, not2)]
    assert len(correct) == 1

    flag += correct[0]

print(flag)