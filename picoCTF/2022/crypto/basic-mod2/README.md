# basic-mod2

Tags: Cryptography<br>
Author: Will Hong

> **Description**<br>
A new modular challenge!<br>
Download the message [here](https://artifacts.picoctf.net/c/505/message.txt). <br>
Take each number mod 41 and find the modular inverse for the result. Then map to the following character set: 1-26 are the alphabet, 27-36 are the decimal digits, and 37 is an underscore.<br>
Wrap your decrypted message in the picoCTF flag format (i.e. `picoCTF{decrypted_message}`)

Follow the instructions in the challenge description.
The modular inverse of `a` mod `m` can be calculated in Python 3.8 and above with `pow(a, -1, m)`.

`picoCTF{1NV3R53LY_H4RD_374BE7BB}`
