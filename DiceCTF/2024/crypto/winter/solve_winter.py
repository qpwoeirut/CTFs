import itertools
from hashlib import sha256
from pwn import process, remote


def find_msg_pair() -> tuple[bytes, bytes]:
    best = 256 * 32
    best_hash = b''
    msg = b''
    for val in itertools.product(range(128), repeat=3):
        m = bytes(val)
        hashed = sha256(m).digest()
        s = sum(hashed)
        if best > s:
            best = s
            msg = m
            best_hash = hashed

    print("Lowest message found was", msg, "with average of", best / 32)

    for val in itertools.product(range(128), repeat=3):
        m = bytes(val)
        hashed = sha256(m).digest()
        if all([h >= b for h, b in zip(hashed, best_hash)]) and m != msg:
            return msg, m

    raise ValueError("increase search space")


def repeat_hash(x: bytes, n: int) -> bytes:
    for _ in range(n):
        x = sha256(x).digest()
    return x


def main():
    low_msg, high_msg = find_msg_pair()

    r = remote("mc.ax", 31001)
    # r = process(["python3", "server.py"])
    r.sendline(high_msg.hex())
    r.recvuntil(b'here is the signature (hex): ')
    sig = bytes.fromhex(r.recvline().strip().decode())

    diffs = [hi - lo for lo, hi in zip(sha256(low_msg).digest(), sha256(high_msg).digest())]
    sig_chunks = [sig[i:i + 32] for i in range(0, len(sig), 32)]
    new_sig = b''.join([repeat_hash(chunk, diff) for chunk, diff in zip(sig_chunks, diffs)])

    r.sendline(low_msg.hex())
    r.sendline(new_sig.hex())

    print(r.recvall().decode())


if __name__ == '__main__':
    main()
