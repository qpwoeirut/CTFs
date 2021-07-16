from scipy.io import wavfile
from string import hexdigits
from utils.basics import hexdump_to_file

samplerate, raw_data = wavfile.read("main.wav")
print("samples:", len(raw_data))
out_str = ""

data = [hexdigits[(d // 500) - 2] for d in raw_data]
with open("out.txt", "w") as f:
    f.write(''.join(data))

hexdump_to_file("out.txt", "flag.txt")