k1 = random1 + b"A"*29
k2 = random2 + b"A"*29
plain = b'This is a non-secret message....'
cipher = AES(k1,AES(k2,plain)) # ECB mode
print(plain,'\n',cipher)
# b'7\xcf7\xce\xa6 \xbe\t\xba\x03\xe4\xac\x9e\x86\x85\xf5YZYa_7\xae\xa1\xe6\xc1\xd1\xad\xfb\x9c\x99s'