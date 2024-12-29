import hashlib
import json
import os
import random

import ecdsa
from Crypto.Cipher import AES
from Crypto.Util.Padding import pad
from Crypto.Util.number import bytes_to_long, long_to_bytes

IV = os.urandom(8)
KEY = os.urandom(16)
FLAG = os.environ.get("FLAG", "0xL4ugh{6d656f776d6f65776d6f65776d6f6577}").encode()


class Player:
    def __init__(self, credits=0):
        self.credits = credits
        self.dishes_washed = 0


class CoffeShop:
    def __init__(self, available_credits):
        self.available = available_credits
        self.curve = ecdsa.curves.NIST224p
        self.g = self.curve.generator
        self.d = random.randint(1, self.curve.order - 1)
        self.pubkey = ecdsa.ecdsa.Public_key(self.g, self.g * self.d)
        self.privkey = ecdsa.ecdsa.Private_key(self.pubkey, self.d)
        self.branch_location = (
            int(self.pubkey.point.x()), int(self.pubkey.point.y())
        )
        self.gen_ks()

    def gen_ks(self):
        c = [random.randint(1, self.curve.order - 1) for _ in range(6)]
        self.ks = [random.randint(1, self.curve.order - 1)]
        for i in range(7):
            k = int(sum((c[j] * (self.ks[i] ** j)) %
                    self.curve.order for j in range(6)) % self.curve.order)
            self.ks.append(k)

    def pay_player(self, player):
        credit = "100 EGP"
        sha256 = hashlib.sha256()
        sha256.update(credit.encode())
        hash = bytes_to_long(sha256.digest()) % self.curve.order
        player.credits += 100
        self.available -= 100
        coin = self.privkey.sign(hash, self.ks.pop())
        return json.dumps({"s": int(coin.s), "r": int(coin.r)})

    def clean(self, data):
        plate = data.encode()
        cipher = AES.new(KEY, AES.MODE_CTR, nonce=IV)
        return cipher.encrypt(plate).hex()

    def check_if_clean(self, data, plate):
        cipher = AES.new(KEY, AES.MODE_CTR, nonce=IV)
        try:
            p = json.loads(cipher.decrypt(bytes.fromhex(data)).decode())
            m = json.loads(plate)
            if p["plate_no"] == m["plate_no"] and p["plate_prop"] == m["plate_prop"]:
                return True
        except BaseException:
            pass
        return False

    def wash_dishes(self, player):
        if shop.available < 100:
            return "We're out of money, sorry :c"
        plate_no = f"{player.dishes_washed}"
        plate_prop = random.randint(1, 9)
        plate = json.dumps({"plate_no": plate_no, "plate_prop": plate_prop})
        print(f"Please clean the following plate: {plate}")
        cleaned = input("(hex) > ")
        if self.check_if_clean(cleaned, plate):
            player.dishes_washed += 1
            print("Here's your cheque: ", end="")
            print(self.pay_player(player))
            return "Good job! You got 100 EGP."
        return "You didn't clean the plate properly! >:c"

    def checkout(self, player):
        if player.credits >= 800:
            print("You have enough money to pay for the meal! c:")
            sha256 = hashlib.sha256()
            sha256.update(long_to_bytes(self.d))
            key = sha256.digest()
            iv = os.urandom(16)
            cipher = AES.new(key, AES.MODE_CBC, iv)
            print(f"Here's your receipt: ")
            return iv.hex() + cipher.encrypt(pad(FLAG, 16)).hex()
        return "You don't have enough money to pay for the meal! >:c"


print("""Welcome to
  ____                               _     _             
 / ___|__ _ _ __   __ ___      _____| |__ (_)_ __   ___  
| |   / _` | '_ \ / _` \ \ /\ / / __| '_ \| | '_ \ / _ \ 
| |__| (_| | |_) | (_| |\ V  V / (__| | | | | | | | (_) |
 \____\__,_| .__/ \__,_| \_/\_/ \___|_| |_|_|_| |_|\___/ 
  ____     |_|_                                          
 / ___|__ _ / _| ___                                     
| |   / _` | |_ / _ \                                    
| |__| (_| |  _|  __/                                    
 \____\__,_|_|  \___|      
""")
shop = CoffeShop(800)
you = Player(0)

print(f"Thank you for eating at at our branch in {shop.branch_location}.")
print("Your total is 800 EGP!")
print(f"==== BEEP === YOUR BALANCE IS {you.credits} EGP.")
print("Oh no, you're broke :c, that's okay, you can always wash the dishes for 100 EGP.")
print("Let me show you the ropes!")
print("You will be given a plate, and you're supposed to clean it! Let me do the first one for you.")

plate_no = f"{you.dishes_washed}"
plate_prop = random.randint(1, 9)
plate = json.dumps({"plate_no": plate_no, "plate_prop": plate_prop})
cleaned = shop.clean(plate)

print(f"Dirty plate: {plate}")
print(f"Cleaned plate: {cleaned}")


choices = {1: "Check Balance", 2: "Pay", 3: "Wash Dishes", 4: "Exit"}

while True:
    print()
    print("Now, what would you like to do?")
    for k, v in choices.items():
        print(f"{k}. {v}")
    try:
        choice = int(input("> "))
        if choice == 1:
            print(f"Your balance is {you.credits} EGP.")
        elif choice == 2:
            print(shop.checkout(you))
        elif choice == 3:
            print(shop.wash_dishes(you))
        elif choice == 4:
            break
        else:
            print("Invalid choice!")
    except:
        print("Something went wrong, please try again.")
