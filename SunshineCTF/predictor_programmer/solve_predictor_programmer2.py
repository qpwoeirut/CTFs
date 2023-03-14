import calendar

from pwn import process, remote
import time

TIME_SCALE = 10000

# we use the time as a "random number" as it's used by the second challenge.
def get_time_number():
    return round(time.time() * TIME_SCALE)


# if knuth made it it must be secure!
def knuth_linear_congruential_generator(state):
    return ((state * 6364136223846793005) + 1442695040888963407) % (2 ** 64)


r = remote("predictor.sunshinectf.games", 22202)
# r = process(["python3", "server-challenge-2_edited.py"])


def query(value: int) -> int:
    print(r.recvuntil(b"0 to 18446744073709551615:").strip().decode())
    r.recvline()  # empty line
    print()
    r.sendline(str(value).encode())
    resp = r.recvline(keepends=False).strip().decode()
    if "too small" in resp:
        return -1
    elif "too big" in resp:
        return 1
    else:
        return 0


def find_state() -> tuple[int, int]:
    r.recvuntil(b"The current date is ")
    current_time_str = r.recvline(keepends=False).decode().split(", you have")[0]
    current_time = calendar.timegm(time.strptime(current_time_str, "%a, %d %b %Y %H:%M:%S +0000"))
    base_seed = round(current_time * TIME_SCALE)
    print("base seed:", base_seed)

    correct_answers = 0
    states = [(base_seed + x, x) for x in range(TIME_SCALE + 1)]
    for _ in range(15):
        states = [(knuth_linear_congruential_generator(state), seed) for state, seed in states]
        states.sort()

        median = states[len(states) // 2]
        median_value = median[0]
        resp = query(median_value)
        if resp == -1:
            states = [(value, seed) for value, seed in states if value > median_value]
        elif resp == 1:
            states = [(value, seed) for value, seed in states if value < median_value]
        else:
            correct_answers += 1
            print("found!")
            return median_value, 1

        print("len(states):", len(states))
        if len(states) < 10:
            print(states)
        assert len(states) > 0
        if len(states) == 1:
            break

    return min(states, key=lambda t: abs(t[1]))[0], 0  # select nearest to given time


def main():
    state, correct_answers = find_state()
    print(state, correct_answers)
    while correct_answers < 5:
        state = knuth_linear_congruential_generator(state)
        assert query(state) == 0
        correct_answers += 1
    print(r.recvall().strip().decode())


if __name__ == "__main__":
    main()
