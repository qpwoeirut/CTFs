# NSA Backdoor

Tags: Cryptography, backdoor, diffie_hellman, rsa<br>
Author: Joshua Inscoe

> **Description**<br>
I heard someone has been sneakily installing backdoors in open-source implementations of Diffie-Hellman... I wonder who it could be... ;)
> * [gen.py](https://artifacts.picoctf.net/c/263/gen.py)
> * [output.txt](https://artifacts.picoctf.net/c/263/output.txt)

This challenge is very similar to the [Very Smooth](/picoCTF/2022/crypto/very_smooth/README.md) challenge.
The prime generation is the exact same, and the modulus can be factored with the same method.

The only difference is that now we need to recover the `e` in RSA, instead of the message.
We're given both the message `m` (which is always 3) and the ciphertext `c`.
To find the flag, we need to solve `3^flag = c mod n`.

This is known as the discrete logarithm problem (or some subset of it, not completely sure) and there are several algorithms to solve it, though most of them will run too slowly here.
The trick is to take advantage of the same trait that we used for factoring the modulus: its smoothness.
The Pohlig-Hellman algorithm, which is mentioned in David Wong's [paper](https://eprint.iacr.org/2016/644.pdf) (as per the hint) fits this situation,