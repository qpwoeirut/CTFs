import datetime
import json
from pwn import remote

# brew install evtx
# evtx_dump security-logs.evtx --dont-show-record-number -o json -f parsed.json
with open("parsed.json") as f:
    s = f.read().replace('}\n{', '},\n{')
data = json.loads('[' + s + ']')

has_defender = [d for d in data if 'defender' in str(d).lower()]

event = has_defender[0]  # it seems any of these will work
time_str = event["Event"]["System"]["TimeCreated"]["#attributes"]["SystemTime"]
as_unix = int(datetime.datetime.fromisoformat(time_str).timestamp())
print(as_unix)

ids = {d["Event"]["System"]["EventID"] for d in data}
print(sorted(ids))

# https://learn.microsoft.com/en-us/previous-versions/windows/it-pro/windows-10/security/threat-protection/auditing/event-4738
account_events = [e for e in data if e["Event"]["System"]["EventID"] in (4737, 4738, 4739) and "TargetUserName" in e["Event"]["EventData"]]
usernames = [e["Event"]["EventData"]["TargetUserName"] for e in account_events]

# https://learn.microsoft.com/en-us/previous-versions/windows/it-pro/windows-10/security/threat-protection/auditing/audit-security-group-management
group_events = [e for e in data if e["Event"]["System"]["EventID"] in (4731, 4732)]
groups = {e["Event"]["EventData"]["TargetUserName"] for e in group_events}
for e in group_events:
    r = remote("be.ax", 32222)
    r.sendline(b"slice1")
    r.sendline(b"lemon-squeezer")
    r.sendline(b"83")
    r.sendline(str(as_unix).encode())
    r.sendline(b"notabackdoor")
    r.sendline(e["Event"]["EventData"]["TargetUserName"].encode())  # Administrators
    print(r.recvall(timeout=1).decode())
