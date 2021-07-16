s = "灩捯䍔䙻ㄶ形楴獟楮獴㌴摟潦弸彤㔲挶戹㍽"

def encrypt(flag):
    return ''.join([chr((ord(flag[i]) << 8) + ord(flag[i + 1])) for i in range(0, len(flag), 2)])

def decrypt(enc):
    chars = []
    for c in enc:
        chars.append(ord(c) >> 8)
        chars.append(ord(c) & ((1 << 8) - 1))
    return ''.join([chr(c) for c in chars])


print(decrypt(s))