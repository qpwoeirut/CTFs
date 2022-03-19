# this script was run on the webshell

from pwn import *

log.level = logging.WARNING

pin = ""
for i in range(8):
    print("Starting digit", i, "with current PIN", pin)
    candidates = []
    for d in "0123456789":
        current_pin = (pin + d).ljust(8, '0').encode()
        proc = process(["./pin_checker"])
        start = time.time()
        proc.sendline(current_pin)
        proc.recvall()
        time_taken = time.time() - start
        proc.close()

        candidates.append((time_taken, d))

    candidates.sort(reverse=True)

    print("Candidates: ")
    for candidate in candidates:
        print(candidate)
    pin += candidates[0][1]

print(pin)  # 48390513
