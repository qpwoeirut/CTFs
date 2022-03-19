# Sleuthkit Intro

Tags: Forensics, sleuthkit<br>
Author: LT 'Syreal' Jones

> **Description**<br>
Download the disk image and use `mmls` on it to find the size of the Linux partition. Connect to the remote checker service to check your answer and get the flag.<br>
Note: if you are using the webshell, download and extract the disk image into `/tmp` not your home directory.
> * [Download disk image](https://artifacts.picoctf.net/c/114/disk.img.gz)
> * Access checker program: `nc saturn.picoctf.net 52279`

I used the webshell for this challenge.
To access the disk image, I ran:
```
wget https://artifacts.picoctf.net/c/114/disk.img.gz
gzip -kd disk.img.gz
```

Then, as instructed, I ran the `mmls` command and got the following:
```
qpwoeirut-picoctf@webshell:/tmp$ mmls disk.img
DOS Partition Table
Offset Sector: 0
Units are in 512-byte sectors

      Slot      Start        End          Length       Description
000:  Meta      0000000000   0000000000   0000000001   Primary Table (#0)
001:  -------   0000000000   0000002047   0000002048   Unallocated
002:  000:000   0000002048   0000204799   0000202752   Linux (0x83)
```

`0000202752` is the correct answer and submitting it to the checker gets us the flag.

`picoCTF{mm15_f7w!}`
