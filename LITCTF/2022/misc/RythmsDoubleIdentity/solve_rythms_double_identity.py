from collections import Counter
import json
import requests


API = "https://codeforces.com/api"
MISSING_CONTESTS = [
    1222, 1224, 1226, 1232, 1233, 1258, 1289,
    1306, 1317, 1318, 1390,
    1410, 1412, 1414, 1429, 1448, 1449,
    1502, 1507, 1518, 1564, 1565, 1568, 1577, 1587, 1590, 1595, 1596, 1597,
    1636, 1640, 1643, 1653, 1655, 1664, 1683, 1704
]


def make_query(name: str, params: dict[str, ]) -> dict:
    params_str = '&'.join([f"{k}={v}" for k, v in params.items()])
    url = f"{API}/{name}?{params_str}"
    resp = requests.get(url).json()
    assert resp["status"] == "OK", f"{url} -> {resp}"
    return resp["result"]


def get_participants_list(contest_id: int) -> set[str]:
    try:
        with open(f"cache/{contest_id}.json", 'r') as f:
            resp = json.load(f)
    except FileNotFoundError:
        resp = make_query("contest.standings", {"contestId": contest_id, "showUnofficial": "true"})
        with open(f"cache/{contest_id}.json", 'w') as f:
            json.dump(resp, f)

    participants = resp["rows"]
    handles = [member["handle"] for item in participants
               for member in item["party"]["members"]
               if item["party"]["participantType"] in ["CONTESTANT", "OUT_OF_COMPETITION"]]
    return set(handles)


def main():
    conflict = Counter()
    separate = Counter()

    print("Fetching contest data")
    for contest_id in range(1185, 1710):
        if contest_id % 10 == 0:
            print("Fetching contest", contest_id)
        if contest_id in MISSING_CONTESTS:
            continue
        participants = get_participants_list(contest_id)
        if "SuperJ6" in participants:
            conflict.update(participants)
        else:
            separate.update(participants)

    for candidate, _ in separate.most_common():
        if separate[candidate] >= 5 and conflict[candidate] <= 2:
            print(candidate, separate[candidate], conflict[candidate])


if __name__ == '__main__':
    main()
