

# This file was *autogenerated* from the file server.sage
from sage.all_cmdline import *   # import sage library

_sage_const_1 = Integer(1); _sage_const_2 = Integer(2); _sage_const_3 = Integer(3); _sage_const_256 = Integer(256); _sage_const_1000 = Integer(1000); _sage_const_1719620105458406433483340568317543019584575635895742560438771105058321655238562613083979651479555788009994557822024565226932906295208262756822275663694111 = Integer(1719620105458406433483340568317543019584575635895742560438771105058321655238562613083979651479555788009994557822024565226932906295208262756822275663694111); _sage_const_5 = Integer(5); _sage_const_0 = Integer(0); _sage_const_100 = Integer(100)
import random
import signal
import os

HANDNAMES = {
    _sage_const_1 : "Rock",
    _sage_const_2 : "Scissors",
    _sage_const_3 : "Paper"
}

def commit(M, m):
    while True:
        r = random.randint(_sage_const_2 , _sage_const_2 **_sage_const_256 )
        if r % _sage_const_3  + _sage_const_1  == m:
            break
    return M**r, r


signal.alarm(_sage_const_1000 )

flag = os.environ.get("FLAG", "neko{old_yoshiking_never_die,simply_fade_away}")
p = _sage_const_1719620105458406433483340568317543019584575635895742560438771105058321655238562613083979651479555788009994557822024565226932906295208262756822275663694111 
M = random_matrix(GF(p), _sage_const_5 )
print("[yoshiking]: Hello! Let's play Janken(RPS)")
print("[yoshiking]: Here is p: {}, and M: {}".format(p, M.list()))

round = _sage_const_0 
wins = _sage_const_0 
while True:
    round += _sage_const_1 
    print("[system]: ROUND {}".format(round))

    yoshiking_hand = random.randint(_sage_const_1 , _sage_const_3 )
    C, r = commit(M, yoshiking_hand)
    print("[yoshiking]: my commitment is={}".format(C.list()))

    hand = input("[system]: your hand(1-3): ")
    print("")
    try:
        hand = int(hand)
        if not (_sage_const_1  <= hand <= _sage_const_3 ):
            raise ValueError()
    except ValueError:
        print("[yoshiking]: Ohhhhhhhhhhhhhhhh no! :(")
        exit()

    print("[yoshiking]: My hand is ... {}".format(HANDNAMES[yoshiking_hand]))
    print("[yoshiking]: Your hand is ... {}".format(HANDNAMES[hand]))
    result = (yoshiking_hand - hand + _sage_const_3 ) % _sage_const_3 
    if result == _sage_const_0 :
        print("[yoshiking]: Draw, draw, draw!!!")
        print("[yoshiking]: I'm only respect to win!")
        print("[system]: you can check that yoshiking doesn't cheat")
        print("[system]: here's the secret value: {}".format(r))
        exit()
    elif result == _sage_const_1 :
        print("[yoshiking]: Yo! You win!!! Ho!")
        wins += _sage_const_1 
        print("[system]: wins: {}".format(wins))

        if wins >= _sage_const_100 :
            break
    elif result == _sage_const_2 :
        print("[yoshiking]: Ahahahaha! I'm the winnnnnnner!!!!")
        print("[yoshiking]: You, good loser!")
        print("[system]: you can check that yoshiking doesn't cheat")
        print("[system]: here's the secret value: {}".format(r))
        exit()

print("[yoshiking]: Wow! You are the king of roshambo!")
print("[yoshiking]: suge- flag ageru")
print(flag)

