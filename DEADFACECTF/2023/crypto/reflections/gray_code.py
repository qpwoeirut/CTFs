# https://stackoverflow.com/questions/38738835/generating-gray-codes


def hipow(n):
    ''' Return the highest power of 2 within n. '''
    exp = 0
    while 2 ** exp <= n:
        exp += 1
    return 2 ** (exp - 1)


def code(n):
    ''' Return nth gray code. '''
    if n > 0:
        return hipow(n) + code(2 * hipow(n) - n - 1)
    return 0


def main():
    mapping = {code(i): i for i in range(128)}
    with open("reflections2.txt") as f:
        encoded = f.read().strip()

    decoded_to_hex = ''.join([chr(mapping[ord(c)]) for c in encoded])
    decoded = ''.join([chr(int(n, 16)) for n in decoded_to_hex.split('0x') if n])
    print(decoded)


if __name__ == '__main__':
    main()
