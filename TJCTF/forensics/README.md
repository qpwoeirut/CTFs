# TJCTF 2022 Forensics

## fake-geoguessr
> Author: alilredapple<br>
> We don't do guess challs here at TJCTF, so that means no Geoguessr 😞 Since I took this photo myself, though, you can find out precisely where it was taken, and some Bonus Content™️, from my Camera Model Name to the Circle Of Confusion. Maybe you'll find a flag there?
> 
> Downloads: [lake.jpg](https://tjctf-2022.storage.googleapis.com/uploads/7a56b27dfa8bea3f7a0351d61f1198096127bb8217702af2c5eed6e80f8aa56f/lake.jpg)

Run `exiftool` on the image provided.
The first part of the flag is in the `Copyright` data: `tjctf{thats_a_`.
The second part is in the `Comment`: `lot_of_metadata}`.

`tjctf{thats_a_lot_of_metadata}`


## cool-school
> Author: andrewxie<br>
> Wow TJ is such a cool school! This image really captures the vibes. But something seems off about the image...
> 
> Downloads: [image.png](https://tjctf-2022.storage.googleapis.com/uploads/1a1f6a901162115f8953f48ce188df3bf8c5b0108204f9286d4c2f0f4c0ff647/image.png)

The description hints at a hidden steganographic message.
Uploading the image into [StegOnline](https://stegonline.georgeom.net/upload) shows a somewhat blurry flag in the lowest bits of red, green, and blue.

![Image of red 0 bitplane](/TJCTF/forensics/cool-school/image_red0.png)
![Image of green 0 bitplane](/TJCTF/forensics/cool-school/image_green0.png)

`tjctf{l0l_st3g_s0_co0l}`


## spongebob
> Author: andrewxie<br>
> TJCTF is really cool and spongebob thinks so too. So cool in fact...wait a minute, isn't the meme usually 4 squares???
> 
> Downlaods: [image.png](https://tjctf-2022.storage.googleapis.com/uploads/702de9969131631b39619ca3d2cf73d6b4739d1f89a0023b9e05cf641673f964/image.png)

Based on the challenge description, it looks like we need to uncover the bottom part of the image, which is somehow hidden.
Using this [PNG chunk inspector](https://www.nayuki.io/page/png-file-chunk-inspector) reveals that an extra IEND chunk has been placed before the last IDAT chunk, which means that the last part isn't displayed.
Initially, I tried to remove the extra IEND chunk in a hex editor but couldn't get it to work.
Instead, I used a [PNG decoder script](https://pyokagan.name/blog/2019-10-14-png/) and modified it by increasing the height until it ran out of image data.

The script is at [solve_spongebob.py](/TJCTF/forensics/spongebob/solve_spongebob.py).

`tjctf{such_pogg3rs_ctf}`