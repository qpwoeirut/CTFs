import hashlib
import struct
import time

import requests
from pwn import remote


# modified from https://gist.github.com/Ironpark/9c9a10040eecb2e6ac071ae6f30b1560
def load_block_data(block_hash):
    r = requests.get('https://blockchain.info/rawblock/' + block_hash)
    return r.json()


def block_hash_preimage(version: int, prevblock, merkle_root, time: int, bits: int, nonce: int):
    prevblock_little = bytearray.fromhex(prevblock)[::-1]
    merkle_root_little = bytearray.fromhex(merkle_root)[::-1]

    # pack binary little endian
    header_bin = struct.pack(
        # < : little endian
        # i : signed int (32 bit) - +
        # I : unsigned int (32 bit) +
        # 32s : 32 byte (char)

        # <i32s32sIII = little endian | int | byte[32] | byte[32] | unsigned int | unsigned int | unsigned int
        '<i32s32sIII',
        version,
        prevblock_little,  # little -> big
        merkle_root_little,  # little -> big
        time,
        bits,
        nonce,
    )

    return hashlib.sha256(header_bin).digest()


# https://www.blockchain.com/explorer/api/blockchain_api has a /blocks/<time> endpoint which returns the last 24h of
# blocks from a given timestamp
# the filtering seems a bit weird but hopefully it works
def find_longest_prefix() -> str:
    # during the CTF my teammate just found a long hash from
    # https://bitcoin.stackexchange.com/questions/65478/which-is-the-smallest-hash-that-has-ever-been-hashed and we
    # solved using that
    now = int(time.time() * 1000)
    day = 24 * 60 * 60 * 1000

    best_block = {"hash": "ffff"}
    for i in range(1000):
        blocks = requests.get(f"https://blockchain.info/blocks/{now - i * day}?format=json").json()
        best_block = min([best_block] + blocks, key=lambda block: block["hash"])
        print(i, best_block["hash"])

    return best_block["hash"]


def main():
    block_hash = find_longest_prefix()
    block = load_block_data(block_hash)

    # calculate block hash
    block_header = block_hash_preimage(
        version=block['ver'],
        prevblock=block['prev_block'],
        merkle_root=block['mrkl_root'],
        time=block['time'],
        bits=block['bits'],
        nonce=block['nonce'],
    )

    assert block_hash == hashlib.sha256(block_header).digest()[::-1].hex()

    prefix_len = max(i for i in range(1, len(block_hash) + 1) if set(block_hash[:i]) == {'0'})
    print("prefix length:", prefix_len)
    print("block hash:", block_hash)

    # chall reverses it for some reason
    payload = block_header[::-1].hex()

    r = remote("slot-machine.chal.uiuc.tf", 1337, ssl=True)
    r.sendline(payload.encode())
    r.sendline(str(prefix_len).encode())
    print(r.recvall(1).strip().decode())


if __name__ == '__main__':
    main()
