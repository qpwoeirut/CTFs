# https://github.com/Ariana1729/CTF-Writeups/blob/master/2019/CryptoCTF/Complex%20RSA/README.md
from collections import namedtuple
from gmpy2 import invert, lcm
from sympy import isprime

private_key = (
    128329415694646850105527417663220454989310213490980740842294900866469518550360977403743209328130516433033852724185671092403884337579882897537139175073013,
    119773890850600188123646882522766760423725010264224559311769920026142724028924588464361802945459257237815241227422748585976629359167921441645714382651911)
public_key = 236252683050532196983825794701514768601125614979892312308283919527619033977486749228418695923608569040825653688303374445536392159719426640289893369552258923597180869981053519695297428186215135878525530974780390951763007339139013157234202093279764459949020588291928614938201565110828675907781512603972957429701280916745719458738970910789383870206038035515777549907045905872280803964436193687072794878040018900969772972761081589529671158140590712503582004892067155769362463889653489918914397872964087471457070748108694165412065471040954221707557816986272311750297566993468288899523479556822418109112211944932649
ciphertext = b'\x00h\xbe\x94\x8c\xcd\xdd\x04\x80\xf4\x9d\t\xd8\x8dO\x08\xf1\xd1\xc4\xb9\xa06\xe7\xe3\xb6\xc3\x01+\xa9\xf2\xb9\xe8\x8d\xe6\xc9\x0c_#\x93\x11\xad\x0f\x90\xd3\x0b6\xb0n\x13\xea~"V6\xdbA&\x87\xfe\xa3C\xcb\x16\xae\xd9\x83\xdbU\xc6\x06\xcd\x9a\x94\xa9\xce\x15{d\x95s\xc2\xfb\x90q\xe7\x02\xa2\x081:_C\xc68\x00y\x7fj4@\xd2\xcdE\x06\x943\xbe\xbcC3\xca\x91\xb4\x0e}C\xab\xff?X\xc30u\x069:Dc\xb5\xdc\x9b0%\x98\xbd\xd9\x13\xc0\x02w\xc5\xe5:\xca\xcf\x0c\xab\xc2\x9b}\xab\xd0\xcc\xbc\x0f\x9e9\t\xf7M\xb3\xed\x86\xb5E\x8b\xbc4\xfaH\x9b4\x1c\xc4\xab\xc0\xaf\x8a5XcX\x19K\xed\x19\xe1\x1c\xd0\x1e\x97c\x9fF:L\x9d\x90p\x99\xb8\xde\xcblM\xb3\x80sSL\xe1\xa4\xd6,\x81\xd6\x9c\xf1\xbb\x9c)\xf03\x155\xc0\xd5v\x13\xd6#\xb7\x19\xdea%\xce<\x1c\xf7\xf2!;\xc1\xd7w\xd1\xc8\x8d/\xaew\xa1\x1d\xc5(\xc8\x9d\x82v\xf6j\x90A,e\xbd\xa7]\x10\x8f\xe5\xe7\x93}:\xdf1~\xec\xd0-o`\r\x96\xe4\x03\xb9E\x9fdF\xc3\xf8L\xa0\xda\xf0E[\xf7\x02\xef|*\x08\x8a5pI&\xa9i\x0fZ\xa8\xb3H\xed\xe8v\xc4H\xff\xdb\xcb\x00\xf1\xae\x9bO\x18\xd5\xd8&p\xf5\xf6\xe9\xf1\x1brs\xc2\r\xceZ\xd0\xb24\x97+\x98b\x0e\xbb\xb8.\x8dT\xe4"\xad\xe4\xa3f\xd0M\xbf\xafX\xbb"[\x99\xdap\xa5\xcfT2Wx\x87M\x7f\x99!>B[Q\x04\xf6\x03\xbc\x84\xf4\xdfj\xdd1^I\x1a\x05\x81\x91\xde9Mf*\x8e\x8d\xe64\xf8\x93\x99&yP\xcd\x00!\x82\xab\xbcy\xed\xf1\x13\xd3\x81\xeaz\xbbP>\x9a2\x8c\x08\x0es\xbc\xa9\xf6\xa3\x8c\xb0\xb9t\xd9?\x06@\xc9\x90\xb7\xa7<\x85\xeb\x1a\x88#\x1c\xc3 \xec\xc7\x94d\x99\xd6\x8e>\x06\xf8Y\xf4\x19\xcaI/hy\x18\x8e\x0e8\xf8\r\xbb\xf6\x11\xb9\x8dCWB6 '

# private_key = (38185078637420941, 46843512524907899)
# public_key = 3199530719905696625765237080802329868014359327455128338626218135681
# ciphertext = b'\x1b\xb3\xacb\x847D\xf0\xa8C\xd5\x17\xd5\xeep,?\x9f)\xd8\xcc=\xd3\xce32 \xa3\x04\xfd\xd8\xaf\xc7\xba3\xecn/Gs\xef\xb29\xfe\xcd\x93h\xbeF\x8e@}N\x0bf\xa7'

# private_key = (16369214309198075196906394030715894631510647034562845131540814895355105977075907743601569649130305276259729725075782970838980068004957104999628488210705565486080315026271837861216580654624234665631836385945271199203743661602975345475592967957920112385427442411991977361520113113297989064692424527746038569, 11403025304134380174640028248978188056993211869878417563076682811505356577942473984754423428531833233987968311901928887452255001812023243697272413435220662293195296756179433773633880885336493439017936194385039521223919981104889611609972337928404163658996524429680377998805831322823777144654990568372818333)
# public_key = 186658564976584229296008480368538009502542027585440356762672291883419841084754860123172553685136322342557101185374121672287788228868214574195439504432960510636651655569147488959558774390168955824259753576682592838087486854250611542276134734656011622430096035131296682535324069181731290087065762695451981838111618782376515299468303416074132880720559779530328911710810070908303527956873801932672410735926835451974037867970084681632398687923138076356102108114471117307152862937401672393199992413771401319258920521212683567038298634150632282264594881844941852868401549040342645834314929761035368221807538648285477
# ciphertext = b'\x0c\xbeF\xe4y\xae>,/xepL\xeb\xf8\xe0\xe5\xce\xa46]\xc9\x1ar\xc9\xc6\xed\xbc\x89\x0e\x9d)\x9b\xd9\xb3\xcb;\x98\xe2\x92\x18h\x00\xf6\xd8\xe1\xe8\xb5\x81\xb4\xf7RVG__\x08\x97\x19\x89\xf8\xb3\x13\x88\xbb\xd7\xb4\xfeh\x98d\x8d\x06\x7f\xe3/z\xf4\x80Y\xe5\xd9\xfeVE\x90\x9e\xa3\x13\xae\xa9\x98\xeb\xdb2\xccj\xe8\xcbE\xc0~Z8\x1f\xc40\x08l\x90\xba\xff#\xb0\xe8\x98\xdb\x8a6\xf7\x05\xed\x1d\xb9T\xf1\xd2\x1c\xda\xb0Y\'~\xc6\x1b\x9d\x17\xd9\x18\xe9\x9d`\xbb^\x95\x0b=\xec\x1f\xaa\xe9\x12=\xd7\x8bt&y%\xf0\xce\x049\xbd;)A\xed\xfbG\xf8\xea\x8aj\x8b\x9c\xb2\x9b\xe2\xf0c\xed\xa1\xb81\x9bU\xd1 \xc9ZK\x06-\xb9m;\xb5uh\x96\x90\xf6\x94\xb6@\x98i{\xd2t\xb7\xe8_\xd9\xcb\x7f\n\xb1\xe6\xf8U\x83\x1ba\x8d\xc2\xc2\x84\xa8\xed\xb97\xc3E\xad\xca\x99\x8e\xdd\x16\x90\x80\xe0Wx\xa6\x80\xf3\x06\xbf\x1a\x14\x01\xd3\x12:\x18O\xb3s\xf7\x05\x1e?\xda~L\x17\'\x08\xdb\xd9\x97\xba\x9ae4K\na\xb0\xde.#o\'Q\x10\xb4\xda\xc89\x88BB\xb5\xa7\x92\xa45X>\xd3\x01I\xbd\xbb\x00)\x84\x86o\xe1\x03c_m$v$\x16\xb7]!\xecy[T\xb7\xf6\x86-*lk\x83\xefpw\x18m80\xea\xabJd\xfc\x1e/\x83\xd9\xe5gB\xf2q\x04\xec\x87V\x07\xdd\xd1\xf3\xf4R\'k\xfe\x87\xcd6\xc2\xd2\x9a3\xcd\xff~e\x148q)\xcb\xd7\x14\xb0\xbb\xbc\xf4\xc7\xae8BY\x1b\xde_N$\xb7\x880>\xec\x8cu0\xfbO\xed)\x80\xdc\x1a\xf8\xed\xf1C\xf5g\xf4\x04\xe62f"\xf9`p\\,\xf7\xc4\x11\x93\xa2\xde\x0f?\xb4@\xd1\xc3\x08\xa8G\x9b+\xe1\xbc\xfb.\xad\x84P\x91\x04D\x12\x97\xedUy4\x00\xc0<\xd8\xdfg\xe9\xa9=/\xab\xf8\x9a3\x1a)\x00\x16<\x10m\xb5\xb5\x8b\x9b\x93\xab\xe58\xaa\xd4\xc9\x14\xc8A\x15\xfc\xcf'


Complex = namedtuple("Complex", ["re", "im"])


def complex_mult(c1, c2, modulus):
    return Complex(
        (c1.re * c2.re - c1.im * c2.im) % modulus,  # real part
        (c1.re * c2.im + c1.im * c2.re) % modulus,  # image part
    )


def complex_pow(c, exp, modulus):
    result = Complex(1, 0)
    while exp > 0:
        if exp & 1:
            result = complex_mult(result, c, modulus)
        c = complex_mult(c, c, modulus)
        exp >>= 1
    return result


# https://codegolf.stackexchange.com/questions/196122/gaussian-integer-division-reminder
def complex_mod(a, b):
    # Compute p = a * conj(b)
    p1 = a.re * b.re + a.im * b.im
    p2 = - a.re * b.im + a.im * b.re

    # Compute the norm-squared of b
    b_nsq = b.re ** 2 + b.im ** 2
    b_nsq_half = b_nsq // 2

    # Do a symmetric mod of d1 and d2 by b_nsq
    # That is, into the interval [-b_nsq_half, +b_nsq_half)
    d1 = (p1 + b_nsq_half) % b_nsq - b_nsq_half
    d2 = (p2 + b_nsq_half) % b_nsq - b_nsq_half

    # Compute r = d / b
    r1 = (d1 * b.re - d2 * b.im) // b_nsq
    r2 = (d1 * b.im + d2 * b.re) // b_nsq

    return r1, r2


p, q = private_key
n = public_key
e = 65537
ct = ciphertext
ct = Complex(int.from_bytes(ct[:len(ct) >> 1], 'big'), int.from_bytes(ct[len(ct) >> 1:], 'big'))

o = (p ** 4 - p ** 2) * (q ** 4 - q ** 2)
assert isprime(p) and isprime(q)

# x = complex_mult(Complex(p, 0), Complex(q, 0), 1 << 5000)
# print(complex_mult(x, x, 1 << 5000))
print(p % 4, p)
print(q % 4, q)
# print(n)

pt = complex_pow(ct, invert(e, o), n)

msg = pt.re.to_bytes((n.bit_length() + 7) // 8, 'big') + pt.im.to_bytes((n.bit_length() + 7) // 8, 'big')
print(msg)
