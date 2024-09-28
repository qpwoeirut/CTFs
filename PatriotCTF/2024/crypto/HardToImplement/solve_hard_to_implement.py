from Crypto.Cipher import AES
from Crypto.Util.Padding import pad
from pwn import remote

r = remote("chal.competitivecyber.club", 6001)


def query(payload: bytes) -> bytes:
    assert len(payload) < 4096
    r.recvuntil(b'Send challenge > ')
    r.sendline(payload)
    resp = r.recvline(keepends=False)
    assert b"Response > " in resp
    return bytes.fromhex(resp.removeprefix(b"Response > ").decode())


def find_flag_len() -> tuple[int, int]:
    init_len = len(query(b""))
    for i in range(16):
        cur_len = len(query(b"a" * i))
        if cur_len > init_len:
            return cur_len - AES.block_size - i, i + 1
    raise ValueError()


def pad_block(block: bytes) -> bytes:
    return block if len(block) == AES.block_size else pad(block, AES.block_size)


# script fails occasionally and finds '[' instead, not sure why
def main():
    flag_len, extend = find_flag_len()

    known_suffix = b""
    for i in range(flag_len):
        possible = [(bytes([b]) + known_suffix)[:AES.block_size] for b in range(128)]
        blocks = [pad_block(pos) for pos in possible]
        if (extend + i) % AES.block_size > 0:
            blocks.append(b'a' * ((extend + i) % AES.block_size))

        resp = query(b''.join(blocks))
        resp_blocks = [resp[i : i + AES.block_size] for i in range(0, len(resp), AES.block_size)]
        block_to_check = resp_blocks[-1 - (i // AES.block_size)]
        byte = resp_blocks.index(block_to_check)
        assert byte < 128
        known_suffix = bytes([byte]) + known_suffix

        print(known_suffix)


if __name__ == '__main__':
    main()
