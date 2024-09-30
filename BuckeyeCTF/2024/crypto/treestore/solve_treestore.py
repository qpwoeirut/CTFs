import ast
import base64
import concurrent.futures
import itertools
import logging
import sys
from typing import Iterable

import pwnlib.tubes.tube
import tqdm
from pwn import process, remote
from pwnlib.log import getLogger

log = getLogger("pwnlib")
log.setLevel(logging.ERROR)


MAX_CHUNK = 1 << 16
char_size = (16, 32)  # (width, height)


def connect() -> pwnlib.tubes.tube.tube:
    # return process(["python3", "main.py"])
    return remote("challs.pwnoh.io", 13420)


def _try_message(payloads: Iterable[bytes]) -> list[int]:
    r = connect()

    counts = []
    for payload in payloads:
        r.recvuntil(b">>> ")
        r.sendline(base64.b64encode(payload))

        count = int(r.recvline(keepends=False).decode().split()[0])
        counts.append(count)

    r.close()
    return counts


def try_message(payloads: Iterable[bytes]) -> list[int]:
    chunks = []
    chunk_size = 0
    chunk = []
    for payload in payloads:
        if chunk_size + len(payload) >= MAX_CHUNK:
            chunks.append(chunk)

            chunk = []
            chunk_size = 0

        chunk.append(payload)
        chunk_size += len(payload)
    if chunk:
        chunks.append(chunk)

    with concurrent.futures.ProcessPoolExecutor(max_workers=10) as executor:
        futures = [executor.submit(_try_message, chunk) for chunk in chunks]
        results = [x for fut in tqdm.tqdm(futures, file=sys.stdout, colour="green") for x in fut.result()]

    return results


def find_flag_length() -> tuple[int, int]:
    aux = 54
    char = 4 * char_size[0] * char_size[1]
    payloads = [
        b'BM' + (aux + char * flag_len).to_bytes(4, byteorder='little') + b'\x00\x00\x00\x006\x00\x00\x00(\x00'
        for flag_len in range(32)
    ]
    counts = try_message(payloads)
    assert counts.count(0) == 1
    flag_len = counts.index(0)
    return flag_len, aux + char * flag_len


def construct_bmp_header(bitmap_width, bitmap_height):
    size_bytes = (54 + bitmap_width * bitmap_height * 4).to_bytes(4, 'little')
    width_bytes = bitmap_width.to_bytes(4, 'little')
    height_bytes = bitmap_height.to_bytes(4, 'little')
    bitmap_size_bytes = (bitmap_width * bitmap_height * 4).to_bytes(4, 'little')
    return b'BM' + size_bytes + b'\x00\x00\x00\x006\x00\x00\x00(\x00\x00\x00' + width_bytes + height_bytes + b'\x01\x00 \x00\x00\x00\x00\x00' + bitmap_size_bytes + b'\xc4\x0e\x00\x00\xc4\x0e\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00'


def combine_options(header: bytes) -> list[list[bytes]]:
    # header doesn't align with blocks, assume first few pixels are empty
    blocks = [header[:16], header[16:32], header[32:48], header[48:] + b'\x00' * 10]

    # could be done with 2^5 options but that requires more effort
    for r in range(1, 9):
        blocks.extend(b''.join(v) for v in itertools.product((b'\x00\x00', b'\xff\xff'), repeat=r))

    counts = try_message(blocks)
    opts = [[b for i, b in enumerate(blocks) if counts[i] == 0]]

    # For some reason the program crashes/hangs every now and then when running on remote, so save progress for restarts
    # opts = []
    # with open("out.txt", 'r') as out:
    #     for line in out:
    #         opts.append(ast.literal_eval(line))
    while len(opts[-1]) > 1:
        combined_set = {a + b for a, b in itertools.product(opts[-1], repeat=2)}  # causes duplicates sometimes
        combined = [
            block for block in combined_set
            if b'\x00\xff\xff\x00' not in block and b'\xff\x00\x00\xff' not in block
               and (b'BM' not in block or block.startswith(b'BM'))
        ]
        print(len(opts), len(opts[-1]), sum(len(block) for block in combined))
        counts = try_message(combined)
        opts.append([b for i, b in enumerate(combined) if counts[i] == 0])
        if all(len(opt) % 16 == 0 for opt in opts[-1]):
            opts[-1].extend([opt for opt in opts[-2] if len(opt) % 16 > 0])

        with open("out.txt", 'w') as out:
            for opt_list in opts:
                out.write(f"{opt_list}\n")
    return opts


def main():
    flag_len, size = find_flag_length()
    print(f'{flag_len = }')

    bitmap_width = flag_len * 16
    bitmap_height = char_size[1]
    header = construct_bmp_header(bitmap_width, bitmap_height)
    assert len(header) == 54

    opts = combine_options(header)

    recovered = b""
    for opt_list in reversed(opts):
        not_used = [opt for opt in opt_list if opt not in recovered]
        assert len(not_used) <= 1
        if not_used:
            recovered += not_used[0]  # if it wasn't combined into a larger chunk, it must have been at the end

    print(f"Recovered {len(recovered)} bytes out of {size}")
    with open("out.bmp", 'wb') as out:
        out.write(recovered)


if __name__ == '__main__':
    main()
