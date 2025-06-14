import string

from pwn import remote, tube
from utils.cryptography.aes.cbc_decryption_oracle import cbc_decryption_oracle


def decrypt(r: tube, payload: bytes) -> bytes:
    r.sendline(b"1")
    r.sendline(payload)
    r.recvuntil(b"[+] Thy credentials have been sealed in the encrypted scrolls: ", timeout=1)
    resp_hex = r.recvline().decode().strip()
    return bytes.fromhex(resp_hex)


def main():
    r = remote("94.237.58.215", 38569)
    chars = string.ascii_letters + string.digits
    secret = cbc_decryption_oracle(lambda payload: decrypt(r, payload), chars.encode(), verbose=2)

    print(secret)

    r.sendline(b"2")
    r.sendline(secret)

    print(r.recvall(timeout=1).decode())


if __name__ == '__main__':
    main()


# HTB{encrypting_with_CBC_decryption_is_as_insecure_as_ECB___they_also_both_fail_the_penguin_test_e0d76cc4206c7c3e39ecf268cb0e7139}
