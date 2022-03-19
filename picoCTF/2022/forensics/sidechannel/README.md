# SideChannel

Tags: Forensics<br>
Author: Anish Singhani

> **Description**<br>
There's something fishy about this PIN-code checker, can you figure out the PIN and get the flag?<br>
Download the PIN checker program here [pin_checker](https://artifacts.picoctf.net/c/149/pin_checker) <br>
Once you've figured out the PIN (and gotten the checker program to accept it), connect to the master server using `nc saturn.picoctf.net 53932` and provide it the PIN to get your flag.

This is a side channel timing attack.
We can write a script that goes through each digit place one by one, and checks which digit takes the longest to verify.
Doing this will build up the PIN digit-by-digit until we have the correct PIN, which can be sent to the master server for the flag.

`picoCTF{t1m1ng_4tt4ck_18704dda}`
