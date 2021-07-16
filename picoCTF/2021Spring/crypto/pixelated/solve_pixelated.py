from PIL import Image
import numpy as np

img1 = np.array(Image.open("scrambled1.png"))
img2 = np.array(Image.open("scrambled2.png"))

assert img1.shape == img2.shape

R = img1.shape[0]
C = img1.shape[1]

xor_img = np.zeros(img1.shape, dtype=np.uint8)
for r in range(R):
    for c in range(C):
        for i in range(3):
            xor_img[r][c][i] = img1[r][c][i] ^ img2[r][c][i]

Image.fromarray(xor_img).save("xor_out.png")


for r in range(R):
    for c in range(C):
        if (xor_img[r][c] != [255, 255, 255]).all():
            xor_img[r][c] = [0, 0, 0]

Image.fromarray(xor_img).save("flag.png")