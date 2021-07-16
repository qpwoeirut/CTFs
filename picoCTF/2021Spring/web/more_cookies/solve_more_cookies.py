from base64 import b64encode, b64decode
import requests

BLOCK_SIZE = 16


def is_valid(ct: bytes) -> bool:
    cookie = b64encode(b64encode(ct)).decode()
    # print(cookie)
    resp = requests.get("http://mercury.picoctf.net:34962/", cookies={"auth_name": cookie}).content.decode()
    if "picoCTF{" in resp:
        print(resp)
    return "Cannot decode cookie." not in resp


if __name__ == '__main__':
    cipher = "NzZPay9DOGpWZmlyK0JiNGphamZ0L3FQaE9aOTBjQkRnQmkzbVZlS0c5V1JNVUZnT25sWW4xWEZrTHZ4dTB2SmtKR3ZYclIrRFVXektmRmNJRlg3KzhIamJZbGFPcnZCdUxSQkhpR3V0QVk1TlBwNGtwRHhweWh2ZGExWk5TTzY="
    cipher = b64decode(b64decode(cipher))
    cipher = bytearray(cipher)
    for x in range(1, 256):
        i = 9
        cipher[i] ^= x
        if is_valid(cipher):
            print(i, x)
        cipher[i] ^= x
