# Sum-O-Primes

Tags: Cryptography, RSA<br>
Author: Joshua Inscoe

> **Description**<br>
We have so much faith in RSA we give you not just the product of the primes, but their sum as well!
> * [gen.py](https://artifacts.picoctf.net/c/184/gen.py)
> * [output.txt](https://artifacts.picoctf.net/c/184/output.txt)

We're given `x = p + q` and `n = p * q`.
We need `p` and `q`, and since we have two equations with two unknowns, we can rearrange to find the secret.

```
p = x - q
n = (x - q) * q
n = xq - q^2
q^2 - xq + n = 0
q = (x + sqrt(x^2 - 4n)) / 2
p = n / q
```

Once we have `p` and `q`, we can decrypt the message.

`picoCTF{126a94ab}`
