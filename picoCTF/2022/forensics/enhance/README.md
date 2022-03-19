# Enhance!

Tags: Forensics, svg<br>
Author: LT 'Syreal' Jones

> **Description**<br>
Download this image file and find the flag.
> * [Download image file](https://artifacts.picoctf.net/c/142/drawing.flag.svg)

Open the SVG file in a browser.
Poking around in DevTools reveals a <text> element within an SVG.
We can efficiently get the flag by storing that element in global JS variable and then reading its `textContent` with the spaces removed.

`picoCTF{3nh4nc3d_6ae42bba}`
