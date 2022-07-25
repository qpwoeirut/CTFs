# LITCTF 2022

Some of these writeups are incomplete, and unfortunately they'll probably stay that way.
I don't have the time and energy to put up all of these, especially since you'll probably be able to find similar information on the challenges elsewhere on the internet.
All the challenges that are listed in this README are ones I solved, and any solve scripts I used should be in this repository.

Teammates solved a lot of the easier challenges, so I don't have writeups for those.
Challenges are listed in solve order.

## crypto/Running Up That Hill
Author: CodeTiger
> If I only could, I'd make a deal with god, and I'd get him to swap our places.<br>
> Downloads: [RunningUpThatHill.zip](https://drive.google.com/uc?export=download&id=17IAxOBN1G5POQ9wwZ4rpd0hY_ayfCJFi)

I had an old [project](https://github.com/qpwoeirut/ClassicalCipherCrackers) of classical cipher implementations and crackers, which I used here.
As the challenge name hints, this is a hill cipher.

```python
from ciphers.HillCipher import HillCipher


alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
key = [5, 19, 27, 10, 19, 24, 11, 6, 16]
key_str = ''.join([alphabet[k] for k in key])
ciphertext = "A8FC7A{H7_ZODCEND_8F_CCQV_RTTVHD}"

filtered = ''.join([c for c in ciphertext if c in alphabet])
plaintext = HillCipher(key_str, alphabet).decrypt(filtered)
for i, c in enumerate(ciphertext):
    if c not in alphabet:
        plaintext = plaintext[:i] + c + plaintext[i:]
        
print(plaintext)
```

`LITCTF{B3_RUNN1NG_UP_TH4T_H1LLLL}`


## rev/math test
Author: eyangch
> this math test is hard<br>
> Downloads: [math](https://drive.google.com/uc?export=download&id=1jGE3v40Xk3-Fq2GsnGvwzU8prZEoL3Iz)

Use Ghidra to decompile the binary.
We're interested in the `grade_test` function and the `answers` data.
We can copy the answers from Ghidra and submit them.
Each answer is an 8-byte (or 32-bit) integer. (We can check this because the first few questions have obvious answers.)
I had to copy the values manually since I couldn't figure out an easy way to copy the data.

```
0x02
0x04
0xF0
0x03
0x09DE8D6D
0x0A
0x591587
0x06A11E49
0x2060E95F
0x09
```
becomes
```
2
4
240
3
165580141
10
5838215
111222345
543222111
9
```

Copy-pasting that input gets the flag.

`LITCTF{y0u_must_b3_gr8_@_m4th_i_th0ught_th4t_t3st_was_imp0ss1bl3!}`


## web/Guess The Pokemon
Author: Stephanie
> Have you heard the new trending game? [GUESS THE POKEMON](http://litctf.live:31772/)!!! Please come try out our vast database of pokemons.<br>
> Downloads: [guess-the-pokemon.zip](https://drive.google.com/uc?export=download&id=1_NkoqdEGrYelVcKjVOVOJ0GmlBMxyXUs)

Note that the query for `names` has no quotes, so we can select entries where `names` equals itself.
`names` is blacklisted, but `names ` (with a trailing space) isn't.

`LITCTF{flagr3l4t3dt0pok3m0n0rsom3th1ng1dk}`


## web/Among Us
Author: CodeTiger
> Hello! I am Polopopy, and my friends like to call me Ryan. I have an unhealthy <strike>fetich</strike>obsession with Among Us, so I made [this website](http://litctf.live:31779/) to demonstrate my unyielding enthusiasm!

There's a response header to the request to `/sussy-yellow-amogus` named `sussyflag` with the flag.

`LITCTF{mr_r4y_h4n_m4y_b3_su55y_bu7_4t_l3ast_h3s_OTZOTZOTZ}`


## crypto/Flashy Colors
Author: CodeTiger
> Computer pixels are so interesting. Do you know that they are actually made of three lights? It's like each pixel has 3 tiny light switches, red, green, and blue, that can go on or off to create so many different colors.
> 
> Anyway, here are some flashy colors for you.<br>
> Downloads: [FlashyColors.png](https://drive.google.com/uc?export=download&id=1X7PMv0vi-Cp_xKzYeFd_wxxPGY373jgz)

Each color channel corresponds to a bit.
Take these bits and spend a few hours messing around until something readable comes up.
The key jump you need to make is to go in column order, not row order.

Overall I think this was my least favorite challenge of an otherwise very good CTF; too much guessing.

Solve script [here](/LITCTF/2022/crypto/FlashyColors/solve_flashy_colors.py).

`LITCTF{0MG_I_l0ve_th3s3_fla5hy_c0lor}`


## SUSSS
Author: CodeTiger
> The Sussy United States Secret Service (SUSSS) is very confident in our cybersecurity. You'll never get our secret! Connect with `nc litctf.live 31782`<br>
> Downloads: [susss.zip](https://drive.google.com/uc?export=download&id=1adVDeOOmOBXjmwZ0U1pvBtPuiiHyB_kn)

Epic challenge!
The basic idea is that you want to find the trace of the original matrix, given the matrix raised to a power.
I had a script that uses the matrix version of Fermat's Little Theorem

`'LITCTF{w3_4r3_l1k3_s00000_un1t3d_h0rr4y}`

## CTF

Signed up for an account, checked back the next day and got the flag for free :)
Seems like a cool idea but I guess there weren't enough people.

`LITCTF{CTF_1n_a_CTF?_W0AH_TH1S_I5_l1k3_s0_cr34t1v3}`


## EYANGCH FAN ART MAKER
Create another `<flag>` component in a blank space on the canvas, such as (100, 150).

`<flag x="100" y="150"></flag>`

`LITCTF{wh4t_d03s_CH_1n_EyangCH_m3an???}`


## A ROCk
Title hints towards the RSA ROCA attack, which is implemented in Sage [here](https://github.com/FlorianPicca/ROCA).

`LITCTF{rsalib_n0_m0r333}`


## Rythm's Double Identity

Use the Codeforces API to grab the standings of all contests since the first one that SuperJ6 participated in.
Take the set of participants from each of those contests and filter out the ones that participated in the same contest as SuperJ6.
Sort the remaining participants by # of contests.
(In hindsight, it may have been better to sort by rating, but that would have required an extra API call to figure out.)

There were a few candidates left after this process, but out of the ones that are near SuperJ6's rating, MaddyBeltran has the most contests.

Solve script [here](/LITCTF/2022/misc/RythmsDoubleIdentity/solve_rythms_double_identity.py).
It will take a long time to run the first time since it needs to fetch and download around 500 contests' worth of standings.
Subsequent runs with cached responses will take a few minutes.

`LITCTF{MaddyBeltran}`


## Dynamic RSA
Brute force second-level GCD candidates

## Murder Mystery
All information is already on the website; submitting passwords doesn't cause network requests.

A lot of the work for the challenge can be skipped by downloading the webpage and changing the password checks to always pass.
The flag is actually in the website source, encoded in binary as zero-width characters in the journal entry.


## Survey

`LITCTF{Th4nk5_for_c0m1ng_w3_l0v3_y0u}`
