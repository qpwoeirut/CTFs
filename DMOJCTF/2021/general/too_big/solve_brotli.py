import brotli

with open("d71944b1edda54681c8ce4385a0c6a4c24cd3ea1.data.br", 'rb') as f:
    data = f.read()

for i in range(200):
    print(i)
    try:
        print(brotli.decompress(data[:i]))
        break
    except Exception:
        pass