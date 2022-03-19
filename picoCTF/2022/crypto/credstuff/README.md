# credstuff

Tags: Cryptography<br>
Author: Will Hong / LT 'Syreal' Jones

> **Description**<br>
We found a leak of a blackmarket website's login credentials. Can you find the password of the user `cultiris` and successfully decrypt it?
Download the leak [here](https://artifacts.picoctf.net/c/534/leak.tar).
The first user in `usernames.txt` corresponds to the first password in `passwords.txt`. The second user corresponds to the second password, and so on.

Find the index of the correct username and use that to find the password, which is ROT13 encoded.
To decode ROT13, I used [CyberChef](https://gchq.github.io/CyberChef/#recipe=ROT13(true,true,false,13)&input=Y3ZwYlBHU3tQN2UxU181NEkzNV83MVozfQ).

`picoCTF{C7r1F_54V35_71M3}`
