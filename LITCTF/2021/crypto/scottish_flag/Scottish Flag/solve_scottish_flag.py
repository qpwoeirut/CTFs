from sympy.solvers import solve
from sympy import Symbol

a0 = 1000000000000000000
ct0 = Symbol("ct0", positive=True)
ct1 = Symbol("ct1", positive=True)
a1 = Symbol("a1")

answers = solve([
    ct0**2 + (ct1 - a0)**2 - 11167218166350596634324970518743041384610511755703179147618262518561344714067317278672955466,
    (ct0 - a1)**2 + ct1**2 - 11167218166350596634324970522379402989880404344356627706412007661522873314144716029510966061,
    (ct0 - a1)**2 + (ct1 - a0)**2 - 11167218166350596634324970517500867796567936885101366588743135261789294824144716029510966061
])

for answer in answers:
    flag = int(answer[ct0]).to_bytes(40, "big") + int(answer[ct1]).to_bytes(40, "big")
    flag = flag.replace(b"\x00", b"")
    print(flag)