from pwn import remote


def get_flag_root():
    return 3611094637755662941518431924033289770558504840
    # lo, hi = 256**10, 256**20
    # while lo < hi:
    #     print(lo, hi)
    #     mid = (lo + hi + 1) >> 1
    #     r = remote("filtercollector.litctf.live", 1337)
    #     r.recvline()
    #     r.sendline(str(mid).encode())
    #     assert r.recv() == b'Input your favorite mod: '
    #
    #     try:
    #         r.sendline(b"0")
    #         r.recvline()
    #         lo = mid
    #     except EOFError:
    #         hi = mid - 1
    #         print("err")
    #     finally:
    #         r.close()
    #
    # return lo


def main():
    rt = get_flag_root()
    print(rt)
    print(rt ** 2)
    print(rt + rt + 1)

    print((rt ** 2).to_bytes(38, 'big'))
    print(((rt + 1) ** 2).to_bytes(38, 'big'))


if __name__ == '__main__':
    main()
