# diffie-hellman

Tags: Cryptography<br>
Author: Will Hong

> **Description**<br>
Alice and Bob wanted to exchange information secretly. The two of them agreed to use the Diffie-Hellman key exchange algorithm, using p = 13 and g = 5. They both chose numbers secretly where Alice chose 7 and Bob chose 3. Then, Alice sent Bob some encoded text (with both letters and digits) using the generated key as the shift amount for a Caesar cipher over the alphabet and the decimal digits. Can you figure out the contents of the message?
Download the message [here](https://artifacts.picoctf.net/c/455/message.txt). <br>
Wrap your decrypted message in the picoCTF flag format like: `picoCTF{decrypted_message}`

We're given all the parameters we need to calculate the secret `s`.
`s = g^a mod p`, so `s = 5^7 mod 13 = 8`. Using `s = g^b mod p = 5^3 mod 13 = 8` confirms this.

Then, we can throw away all of this work since none of those numbers are useful for getting the flag.
Instead, we can brute force each shift of the Caesar Cipher.
The important phrase is that the Caesar Cipher is "over the alphabet **and** the decimal digits" which is different from the standard Caesar Cipher.
Using a combined alphabet and shifting by 5 gets the flag.

Here's a CyberChef [recipe](https://gchq.github.io/CyberChef/#recipe=Substitute('0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ','VWXYZ0123456789ABCDEFGHIJKLMNOPQRSTU')&input=SDk4QTlXX0g2VU04V182QV85X0Q2Q181WkNJOUM4SV9DQjVFSkhCNg) which does this using substitution.

`picoCTF{C4354R_C1PH3R_15_4_817_0U7D473D_7609EC61}`