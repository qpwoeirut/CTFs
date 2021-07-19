from collections import Counter
from scipy.io import wavfile

samplerate, data = wavfile.read("Rythm800Hz.wav")

print(samplerate, len(data))

data = [abs(x) for x in data][:samplerate * 2]

# print(data[:len(data) // 100])
print(data)

notes = []
cur = (0, -1)

for i, d in enumerate(data):
    if i < 5:
        continue

    if max(data[i-5:i+1]) < 12000 and cur[0] >= 20000:
        notes.append(cur)
        cur = (0, i)

    cur = max(cur, (d, cur[1]))

print(notes)