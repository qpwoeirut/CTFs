from utils.cryptography.railfence import RailFence

with open("message.txt") as f:
    ct = f.read().strip()

print(RailFence(4, 0).decrypt(ct))
