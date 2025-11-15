MODULUS = 100000000000000000000000000000000000000000000000151
a_str = "XthEeBeQm3MTsw1ytAHvFNNnL9rhYHBJzrRqmCl2iEhSTzkrhgUAWEYTkqcK1XirEIkiiwVkRc6alzBXJ7ynM69iEoaF5juikG21mBYlbOFOkRAeTS76LfbDcr9cRyWfTdoZk9VKqXmOCtDF2ZpmZmKDFYTnJXTHByHqMLuKEn1V4mdnJ8DvzcLCSFv3LJzBf9Omxs8Egd5lpIDGT65vyyHcgMGobZmnfjaodqyBSd4Fw63IIb1r5KtD2OskgN3NZdymlPU5LtvFTY34r9lYTvePuKFpJEGDTga"
b_str = "2hnX15jtajgnJYKGAhC5OV1GjQlelUHHgTDJNAogJ2ZwmBO7rj8iMUCYcpqE2ygp7ZzJtVo8wmBQAqISrgZCuLIQSzjvdPytLjLie0esepxDalXD1lekVLvg5IVHsQiC7TcsaiY1SbYu8RvzNVGcA2ljr7lOopMaX855vgeHNoyOVhdJy6FguYYl9qzI4R2XIYO4m3JjcDNRULQOLD4nhtQLRJ7tcGRxMDQDYpUbttr1JuHcrJtvHgVFtK13iFYaS3XFR55QEw52dZDEZ4pRsat74rc8lndWkeoCt8qeBjEprnDjLYW3CVpa21ux1YfdO1sDTNPuQ3mOCE6B6a4kQc7UyM5lmxudjmCDhfD07I4Ls22LbVK7AdZpTWI8ZO90jG1TilcQIMX7Kr3og4Z8pidMDjCl1XOFEjCAySnp36zCa9ohTlRdrUQPcXs0yo7mM25R8rqKAb1DQrx75W7d8Cw8nwcJOr3kvLNG9RX91Gt9eUBEPbqaK9SjJpy8lvhpgMoH3nKy5MnY5KYVO2mH9tHcpnr9YweUsWLz7L1iOPp5v7RYrUzMPn4e2NtMyPEDiMCgzjubgcLQUrl6HytlcWDILZ55zPZUMEoRt4f0QKGy4Z0fuJTcYLxn5HWVg9ZyACMVTCdagMUPpvdKmCteJiL7YcGjkkk1fRWm9pJOFHerwz4DjZ7fUO"
x = 57747851786171493771708682658289659416964365220270
y = 94463895675179066127876381387266781722096911347573

a = int.from_bytes(a_str.encode(), "big")
b = int.from_bytes(b_str.encode(), "big")

n = (a.bit_length() + 7) // 8 * 8
m = (b.bit_length() + 7) // 8 * 8

secret = (a * y - b * x) * pow(pow(2, m, MODULUS) * x - pow(2, n, MODULUS) * y, -1, MODULUS) % MODULUS
print(secret)
print(secret.to_bytes((secret.bit_length() + 7) // 8, "big"))

# s*2^n * k + a * k = x => s*2^n + a = x * k^-1
# s*2^m * k + b * k = y
# k(s(2^n - 2^m) + a - b) = x - y
# k = (x - y)(s(2^n - 2^m) + a - b)^-1
# s*2^n + a = x * (x - y)^-1 * (s(2^n - 2^m) + a - b)

# Wolfram
# s = (a*y - b*x)(2^m*x - 2^n*y)^-1