from Crypto.Cipher import AES
from pwn import remote


def aes(block: bytes, key: bytes) -> bytes:
    assert len(block) == len(key) == 16
    return AES.new(key, AES.MODE_ECB).encrypt(block)


def pad(data):
    padding_length = 16 - len(data) % 16
    return data + b"_" * padding_length


def append_hash(state: bytes, data: bytes):
    data = pad(data)

    for i in range(0, len(data), 16):
        block = data[i: i + 16]
        state = aes(block, state)

    return state


def main():
    r = remote("challs.pwnoh.io", 13419)
    r.recvuntil(b"Hashbrowns recipe as hex:\n")
    base = bytes.fromhex(r.recvline(keepends=False).decode())
    r.recvuntil(b"Signature:\n")
    base_sig = bytes.fromhex(r.recvline(keepends=False).decode())

    target = b"french fry"
    new_sig = append_hash(base_sig, target)

    payload = pad(base) + target

    r.sendline(payload.hex().encode())
    r.sendline(new_sig.hex().encode())

    print(r.recvall(timeout=1).decode())


if __name__ == '__main__':
    main()
