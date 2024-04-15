from pwn import remote
from utils.cryptography.padding_oracle import decrypt_with_padding_oracle
from utils.flag_strings import alpha_flag_chars_lower as flag_chars


def main():
    # r = process(["python3", "chal.py"])
    r = remote('gold.b01le.rs', 5003)
    r.recvuntil(b'Here is a valid certificate: ')
    enc = r.recvline().strip().decode()
    enc = bytes.fromhex(enc)
    print(enc, len(enc))

    def oracle(iv: bytes, ct: bytes):
        r.recvuntil(b'Give me a certificate >> ')
        r.sendline((iv + ct).hex().encode())
        return b'Something went wrong' not in r.recvline()

    print(decrypt_with_padding_oracle(enc[16:], oracle, iv=enc[:16], options=[ord(x) for x in flag_chars]))


if __name__ == '__main__':
    main()
