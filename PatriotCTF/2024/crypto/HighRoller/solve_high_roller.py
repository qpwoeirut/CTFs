from Crypto.Util.number import *
import random

# from public_key.pem
n = 0x3081dd300d06092a864886f70d01010105000381cb003081c7028181009c0a0bfbbdd87b313e6f3c63aad1176c8ad745caf729768b9f97981357df1c21e04215d97f2c8c42081e6ccecd2d497f3a1c4c1edc8035d415c9dd8c778ac08f73ead88e6d41ec8cfb4e8476932e37aa910ada561a66bbfe341b77a08cd7ab3600e9aabdcd225f28d2b20d6632ddbcfb35fc1080d396d82f01cab8d9a221923d024100bd202092e27db343467c522563436c1ef2e51cee6cc0b02d728751011d954ad9c2fc485aa424e0162aa072360c8c40e8f6b4854b46bb9b07999697afc7da148b


def try_seed(seed: int) -> str | None:
    random.seed(seed)
    p, q = getPrime(512, random.randbytes), getPrime(512, random.randbytes)

    if p * q != n:
        return None

    with open("flag.enc", 'rb') as f:
        enc = f.read()
    c = int.from_bytes(enc)
    print(pow())


def main():
    for t in range(10):
        try_seed(0)


if __name__ == '__main__':
    main()
