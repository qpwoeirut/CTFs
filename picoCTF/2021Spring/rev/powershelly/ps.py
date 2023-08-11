import re


def scramble(block, seed):
    raw = ''.join(block)
    bm = '10 ' * len(raw)
    bm = bm.split(' ')
    print('ins', len(bm))
    for i in range(len(raw)):
        y = (i * seed) % len(raw)
        n = bm[y]
        while n != '10':
            y = (y + 1) % len(raw)
            n = bm[y]
        if raw[i] == '1':
            n = '11'
        else:
            n = '00'
        bm[y] = n
    print('ins', len(bm))
    raw2 = ''.join(bm)
    print('insraw2\n ', raw2)
    return int(raw2, 2)


with open('output.txt', 'r') as f:
    blocks = f.readlines()

randoms = []
for i in range(1, len(blocks) + 1):
    y = (((i * 327) % 681) + 344) % 313
    randoms.append(y)

seeds = []
for i in range(1, len(blocks) + 1):
    seeds.append((i * 127) % 500)

b2 = []

for i in range(len(blocks)):
    print('output', blocks[i])
    num = int(blocks[i])
    fun = num ^ randoms[i] ^ 0
    bm = bin(fun)[2:].rjust(60, '0')
    print('bm\n ', bm)
    bm = re.findall('..', bm)
    print(bm)
    cop = ['10'] * len(bm)
    raw = ['' for _ in range(30)]
    for j in range(len(cop)):
        # print(j, ''.join(cop))
        y = (j * seeds[i]) % len(cop)
        n = cop[y]
        while n != '10':
            y = (y + 1) % len(cop)
            n = cop[y]
        cop[y] = bm[y]
        if bm[y] == '00':
            raw[j] = '0'
        else:
            raw[j] = '1'
    orig = ''.join(raw)
    block = re.findall('.' * 6, orig)
    # print('reversed', block)
    # print('rescramble', scramble(block, seeds[i]) ^ randoms[i])
    b2.append(block)
# input()

"""
function Scramble {
    param (
        $block,
        $seed
    )
    $raw = [system.String]::Join("", $block)
    $bm = "10 " * $raw.Length
    $bm = $bm.Split(" ")
    for ($i=0; $i -lt $raw.Length ; $i++)
    {

      $y = ($i * $seed) % $raw.Length
      $n = $bm[$y]
      while ($n -ne "10")
      {
        $y = ($y + 1) % $raw.Length
        $n = $bm[$y]
      }
      if ($raw[$i] -eq "1" )
      {
        $n = "11"
      }
      else
      {
      $n = "00"
      }
      $bm[$y] = $n
    }
    $raw2 = [system.String]::Join("", $bm)
    $b = [convert]::ToInt64($raw2,2)
    return $b
}
"""

b2 = [*zip(*b2)]

with open('input2.txt', 'w') as f:
    for l in b2:
        f.write(' '.join(l) + '\r\n')
