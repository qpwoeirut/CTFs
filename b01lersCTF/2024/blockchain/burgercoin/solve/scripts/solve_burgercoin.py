from brownie import *


# Get Chain ID
# curl http://gold.b01le.rs:8545/7f5e0749-463c-4173-8477-e15a80812291 -X POST -H "Content-Type: application/json" -d '{"jsonrpc":"2.0","method":"eth_chainId","params":[],"id":1}'
# {"jsonrpc":"2.0","id":1,"result":"0xaa36a7"}
# Seems to stay the same across all instances


# brownie networks add ethereum b01lers host=http://gold.b01le.rs:8545/7f5e0749-463c-4173-8477-e15a80812291 chainid=0xaa36a7
# brownie networks delete b01lers
network.connect("b01lers")
assert network.is_connected()


myWallet = accounts.add(0x173ff8789133bba83e66c2fb309dc201459b73cffec72a9a48d69378c07188a3)
d = Contract.from_abi("0x63F1240cD53994e8912f41567983E64E544A8c10")
funcs = d.functions

print(d)
print(funcs)
print(dir(funcs))
print(list(funcs))
