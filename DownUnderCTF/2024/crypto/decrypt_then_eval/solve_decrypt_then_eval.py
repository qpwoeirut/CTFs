import pwn
from Crypto.Cipher import AES
from pwn import remote, process


def test():
    # recreate the AES instance each time
    def decrypt(ct: bytes) -> bytes:
        key = b"testtesttesttest"
        key = b'\xcb\xde\x1b^\xb9%M~\xfeD\x08\xb7\\\xd2\x93\xea'
        iv = b"test iv test iv!"
        iv = b'qjNY\x8c\xeb\x81\x91\xda\xb1\xd9\xc4Y\x98\x82\xf3'
        aes = AES.new(key, AES.MODE_CFB, iv, segment_size=128)
        return aes.decrypt(ct)

    print(decrypt(b"\x00" * 16))
    print(decrypt(b'\x01' + b'\x00' * 15))
    print(decrypt(b'\x02' + b'\x00' * 15))

    payload = bytearray(b'6x')[::-1].zfill(16)[::-1]
    print(payload)
    print(decrypt(payload))

    payload = bytearray(b'6j\xf0\xdc\x00')[::-1].zfill(16)[::-1]
    print(payload)
    print(decrypt(payload))

    payload = bytearray(b'A\x17\x80\xaa\x0000000000000')[::-1].zfill(16)[::-1]
    print(payload)
    print(decrypt(payload))


def query(r: pwn.tube, val: bytes) -> bytes:
    r.recvuntil(b"ct: ")
    r.sendline(val.hex().encode())
    return r.recvline().decode().strip()


def find_valid(r: pwn.tube) -> bytearray:
    b = bytearray(16)
    # TIL that this loop variable syntax is allowed, very cool
    for b[0] in range(0, 256, 10):  # find a number
        print("b[0] =", b[0])
        for b[1] in range(256):  # find comment (#)
            resp = query(r, b)
            if resp != "invalid ct!":
                print("resp:", resp)
                value = int(resp[0])
                return bytearray([ord(str(value)) ^ ord('1') ^ b[0], b[1]])

    # there's a chance this happens, just rerun
    raise ValueError("couldn't find valid starting point, decryption probably has a null byte or newline")


# This is rather brittle but we can just run it a few times until we get the flag
def main():
    r = remote("2024.ductf.dev", 30020)
    # r = process(["python3", "decrypt-then-eval.py"])

    payload = find_valid(r)
    print("initial payload:", payload)
    for i in range(1, len("FLAG")):
        print("finding i =", i)
        new_payload, comment = payload[:-1], payload[-1]
        print(new_payload)
        new_payload.append(comment ^ ord('#') ^ ord('1'))
        print(new_payload)
        new_payload = new_payload[::-1].zfill(16)[::-1]
        for new_payload[i + 1] in range(256):
            resp = query(r, new_payload)
            if resp != "invalid ct!":
                print("resp:", resp)
                assert resp.isdigit(), resp
                payload = new_payload[:i + 2]
                break
        else:
            print("failed on iteration", i)
            exit(0)

    flag_payload = bytes([p ^ c ^ ord('1') for p, c in zip(payload, b"FLAG1")])
    flag_payload = flag_payload[::-1].zfill(16)[::-1]

    print(bytes(payload))
    print(flag_payload)

    print(query(r, payload))
    print(query(r, flag_payload))

    print(r.recvall(timeout=0.1).decode().strip())


if __name__ == '__main__':
    main()
    # test()
