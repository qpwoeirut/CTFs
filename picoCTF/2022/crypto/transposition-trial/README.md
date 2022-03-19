# transposition-trial

Tags: Cryptography<br>
Author: Will Hong

> **Description**<br>
Our data got corrupted on the way here. Luckily, nothing got replaced, but every block of 3 got scrambled around! The first word seems to be three letters long, maybe you can use that to recover the rest of the message.
Download the corrupted message [here](https://artifacts.picoctf.net/c/462/message.txt).

Unscramble each triplet manually, following whatever makes the most sense.
A pattern soon emerges: each block rotates by one spot.
This can be done with Python code.

`picoCTF{7R4N5P051N6_15_3XP3N51V3_58410214}`