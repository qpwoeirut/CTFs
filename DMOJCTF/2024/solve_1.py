goal = [20, 50, 54, 68, 54, 67, 20, 58, 65, 57, 4, 67, 16]

for offset in range(min(goal) - 128, max(goal)):
    answer = ''.join([chr(x - offset) for x in goal])
    if answer.isprintable():
        print(answer, offset)
