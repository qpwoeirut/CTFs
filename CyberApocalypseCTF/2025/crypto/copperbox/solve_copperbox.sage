load("coppersmith.sage")  # https://github.com/defund/coppersmith/blob/master/coppersmith.sage

p = 0x31337313373133731337313373133731337313373133731337313373133732ad
R = Integers(p)

trunc = 48
x0 = R(77759147870011250959067600299812670660963056658309113392093130 << trunc)
y0 = R(50608194198883881938583003429122755064581079722494357415324546 << trunc)

a = R(0xdeadbeefdeadbeefdeadbeefdeadbeefdeadbeefdeadbeefdeadbeefdeadbeef)
b = R(0xdeadc0dedeadc0dedeadc0dedeadc0dedeadc0dedeadc0dedeadc0dedeadc0de)

# x0 = x / (ax + b)
# x = a*x*x0 + b*x0
# (1 - a*x0)*x = b*x0
# x = b*x0 / (1 - a*x0)

# y0 = (aax + ab + b) / (aaax + aab + ab + b)
# c = ab + b
# d = aab + c
# y0 = (aax + c) / (aaax + d)
# aax + c = aaa*x*y0 + d*y0
# (aa - aaa*y0)*x = d*y0 - c
# x = (d*y0 - c) / (aa - aaa*y0)
# x = (aab*y0 + ab*y0 + b*y0 - ab - b) / (aa - aaa*y0)
# x = (aab*y0 + ab*(y0 - 1) + b*(y0 - 1)) / (aa - aaa*y0)

# b*x0 / (1 - a*x0) = (aab*y0 + ab*(y0 - 1) + b*(y0 - 1)) / (aa - aaa*y0)
# (b*x0)(aa - aaa*y0) = (1 - a*x0)(aab*y0 + ab*(y0 - 1) + b*(y0 - 1))

bounds = 1 << trunc, 1 << trunc
P.<x, y> = PolynomialRing(R)
f = (b*(x0 + x))*(a*a - a*a*a*(y0 + y)) - (1 - a*(x0 + x))*(a*a*b*(y0 + y) + a*b*(y0 + y - 1) + b*(y0 + y - 1))
solutions = small_roots(f, bounds)

assert len(solutions) == 1

x, y = solutions[0]

x0 += x
y0 += y

seed = (x0^-1 - a)^-1 * b
flag = (seed - b) * a^-1
print(int(flag).to_bytes(30))

# HTB{sm1th1ng_mY_c0pp3r_fl4G}
