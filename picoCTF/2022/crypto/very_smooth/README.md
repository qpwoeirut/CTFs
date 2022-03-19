# Very Smooth

Tags: Cryptography, backdoor, rsa<br>
Author: Joshua Inscoe

> **Description**<br>
Forget safe primes... Here, we like to live life dangerously... >:)
> * [gen.py](https://artifacts.picoctf.net/c/135/gen.py)
> * [output.txt](https://artifacts.picoctf.net/c/135/output.txt)

This is a case where we can apply [Pollard's p-1 algorithm](https://en.wikipedia.org/wiki/Pollard%27s_p_%E2%88%92_1_algorithm).
I used sympy's implementation since I was too lazy to write it myself. :)

Make sure that you don't make B too big. Since all factors of p-1 are less than 2^17, setting B to be larger won't work.

`picoCTF{94287e17}`
