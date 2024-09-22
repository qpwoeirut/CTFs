def reverse_ultrablend(message: bytes) -> tuple[list[bytes], int]:
    assert len(message) == 32

    msg = bytearray(message)
    for i in reversed(range(32)):
        msg[i] ^= msg[(i + 1) % 32] ^ i

    msg = msg[16:] + msg[:16]

    msg_len = msg[30] ^ 0xff
    t = msg[28] * 256 + msg[29]

    results = []
    for msg[28] in range(min(256, t + 1)):
        cur_msg = msg.copy()
        for i in reversed(range(28)):
            cur_msg[i] = (cur_msg[i] - cur_msg[i + 1]) % 256

        if t == cur_msg[0] * cur_msg[28]:
            results.append(cur_msg)

    return results, msg_len


def main():
    as_hex = "1a061d36422e5a08190009ddfd34d74d603f2f7c384a08b3521c08130d171dcf"
    results, msg_len = reverse_ultrablend(bytes.fromhex(as_hex))
    assert len(results) == 1
    result = results[0]

    flag_prefix = b"MetaCTF{f"  # f = '}' ^ '\x1b'
    flag_end = bytes([a ^ b for a, b in zip(result, flag_prefix)])
    print(result)
    print(flag_end)

    flag = flag_prefix + result[len(flag_prefix):-3] + flag_end
    print(flag)


if __name__ == '__main__':
    main()
