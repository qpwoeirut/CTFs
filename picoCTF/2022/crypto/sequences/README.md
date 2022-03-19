# Sequences

Author: Anish Singhani<br>
Tags: Cryptography

> **Description**<br>
I wrote this linear recurrence function, can you figure out how to make it run fast enough and get the flag?<br>
Download the code here [sequences.py](https://artifacts.picoctf.net/c/512/sequences.py) <br>
Note that even an efficient solution might take several seconds to run. If your solution is taking several minutes, then you may need to reconsider your approach.

This linear recurrence can be calculated with matrix powers.
In order to speed up the calculation, we split it in two and apply the mod from the `decrypt_flag` function so that the numbers become smaller.

`picoCTF{b1g_numb3rs_a1c77d6c}`
