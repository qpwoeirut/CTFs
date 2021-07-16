from utils.basics import hex_to_ascii
from utils.rsa.rsa_lsb_oracle import lsboracle
from pwn import remote

r = remote("mercury.picoctf.net", 60368)


def check(s: str):
    ln = r.recvline().strip().decode()
    assert ln == s, ln


def get_lsb(enc: int) -> int:
    r.recvuntil("Give me ciphertext to decrypt: ")
    r.sendline(str(enc))

    try:
        resp = r.recvline().strip().decode().split(': ')[1]
    except EOFError as e:
        print("unable to encrypt:", enc)
        raise e
    return int(resp) & 1


check("Welcome to the Padding Oracle Challenge")
check("This oracle will take anything you give it and decrypt using RSA. "
      "It will not accept the ciphertext with the secret message... Good Luck!")
check("")
check("")

n = int(r.recvline().strip().decode().split(': ')[1])
e = int(r.recvline().strip().decode().split(': ')[1])
c = int(r.recvline().strip().decode().split(': ')[1])

print(n)
print(e)
print(c)

flag = lsboracle(c, get_lsb, e, n)
print(flag)
print(hex_to_ascii(flag))

# picoCTF{m4yb3_Th0se_m3s54g3s_4r3_difurrent_3279013F
# picoCTF{m4yb3_Th0se_m3s54g3s_4r3_difurrent_3279013}
