# St3g0

Tags: Forensics, steganography<br>
Author: LT 'Syreal' Jones (ft. djrobin17)

> **Description**<br>
Download this image and find the flag.
> * [Download image](https://artifacts.picoctf.net/c/427/pico.flag.png)

Putting the provided image through [StegOnline](https://stegonline.georgeom.net/upload) shows some noise in the top left for the lowest bit of red, green, and blue.
This LSB stego can be extracted with the [zsteg](https://github.com/zed-0xff/zsteg) tool.

In hindsight, running `zsteg` first would have worked too.

`picoCTF{7h3r3_15_n0_5p00n_4706df81}`
