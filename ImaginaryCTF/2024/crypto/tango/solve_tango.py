from zlib import crc32


def xor(a: bytes, b: bytes) -> bytes:
    assert len(a) == len(b), f"{len(a)} {len(b)}"
    return bytes([b0 ^ b1 for b0, b1 in zip(a, b)])


def find_data_nonce(change: bytes, idx: int) -> bytes:
    prefix_crc = crc32(change[:idx])
    target_crc = crc32(b'\x00' * len(change))
    ch = bytearray(change[idx:])

    valid_change = [x for x in range(256) if all(chr(ord(c) ^ x).isascii() and chr(ord(c) ^ x).isprintable() and chr(ord(c) ^ x) not in "'\"\n\r" for c in "0123456789abcdef")]

    for ch[0] in valid_change:
        for ch[1] in valid_change:
            for ch[2] in valid_change:
                for ch[3] in valid_change:
                    for ch[4] in valid_change:
                        for ch[5] in valid_change:
                            for ch[6] in valid_change:
                                if crc32(ch, prefix_crc) == target_crc:
                                    assert crc32(ch, prefix_crc) == crc32(change[:idx] + ch)
                                    return change[:idx] + ch
            print(ch[0], ch[1])


# Ran this with PyPy3 to get the hardcoded value
# This works since crc(a ^ x) ^ crc(a) == crc(b ^ x) ^ crc(b)
def calculate_change():
    return b'\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x07\x1c\n\x06\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x08\x03\x11E\x0e\x0c\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x0e\n\x08]\\\x18\x00\x00\x00\x00'
    # return b'\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x07\x1c\n\x06\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x08\x03\x11E\x0e\x0c\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\tPR\x08X\x08[\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00'

    ct_len = 63
    data   = b'{"user": "user", "command": "nop", '
    target = b'{"user": "root", "command": "flag",'
    change = xor(target, data)
    change += (ct_len - len(change)) * b'\x00'
    print(change)

    # leave 8 bytes to flip, reduce the length of the checksum that changes with each check
    change = find_data_nonce(change, len(data) + len(b'"nonce": "fedcba9'))
    print(change)
    return change


def main():
    change = calculate_change()

    from pwn import remote
    r = remote("tango.chal.imaginaryctf.org", 1337)
    r.sendline(b'E')
    r.sendline(b'nop')
    r.recvuntil(b'Your encrypted packet is: ')

    enc_hex = r.recvline().decode().strip()
    enc = bytes.fromhex(enc_hex)
    nonce, checksum, ciphertext = enc[:8], enc[8:12], enc[12:]
    payload = xor(ciphertext, change)

    r.sendline(b'R')
    r.sendline((nonce + checksum + payload).hex())
    print(r.recvall(timeout=1).decode())


if __name__ == '__main__':
    main()
