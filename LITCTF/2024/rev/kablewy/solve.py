import base64
import re

with open("index.js") as f:
    script = f.read()

flag = ""
matches = re.findall(r'const . = "([a-zA-Z0-9]+)"', script)
for match in matches:
    decoded = base64.b64decode(match).decode()
    if 'atob' not in decoded:
        print("skipped:", decoded)
        continue

    inner = re.search(r'eval\(atob\("([a-zA-Z0-9]+)"\)\)', decoded).group(1)
    inner_decoded = base64.b64decode(inner).decode()
    # print(inner_decoded)

    flag_char = re.search(r"postMessage\('(.)'\)", inner_decoded).group(1)
    flag += flag_char

print(flag)
