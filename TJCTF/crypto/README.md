# TJCTF 2022 Crypto

## rsa-apprentice
> Author: nthistle<br>
> My friend sent me this secret message but I can't figure out how to decrypt it! Can you help?
>
> Downloads: [problem.txt](https://tjctf-2022.storage.googleapis.com/uploads/95aaede885ed4dfe4618dd26d1e319e872b2cec078261b89307324c6cfc8dff5/problem.txt) 

`n` can be factored with [factordb](http://factordb.com/index.php?query=1216177716507739302616478655910148392804849).
From there, the two ciphertexts can be decrypted with standard RSA procedure to find the flag.

`tjctf{n0t_s0_S3cur3_Cryp70}`


## flimsy-fingered-latin-teacher
> Author: alilredapple
> Oh no! My Latin teacher likes to touch type on her Dell laptop, but she has trouble keeping her fingers on the right keys in home row. The letters she's typing out don't really make sense. Can you help me understand what she's saying so she doesn't get upset when I come to her confused?
> 
> `ykvyg}pp[djp,rtpelru[pdoyopm|`

We know the first 6 characters of the flag should be `tjctf{`.
The description mentions the keyboard, and we can see that the provided text's characters are all one key offset from what we expect.
To find the flag, we offset in the other direction.

`tjctf{oopshomerowkeyposition}`


## factor-master
Python and SymPy too slow, used C++ GMP

`tjctf{S0_y0u_r34lly_c4n_F4c7t0r_4ny7th1nG_c36f63cfe73c}`


## morph-master

`tjctf{M0rph1Ng_1S_pr3t7y_c0ol_1snt_it?}`


## complex-secrets
> Author: kfb<br>
> Mr. Riemann came up with his own secret sharing scheme! Can you figure it out?
> 
> Downloads: [encrypt.sage](https://tjctf-2022.storage.googleapis.com/uploads/b668a4a94d177a086e304bb8158f314645ec1bb05628454e623e6473a15a45f3/encrypt.sage), [output.txt](https://tjctf-2022.storage.googleapis.com/uploads/47a5a6b77b60db5b0a249704877258b1591a7e9a6a0c38c2179d5ab3c25e3f5a/output.txt)

I won't lie; I have no clue how Möbius transformations work.
But I did enough reading on [Wikipedia](https://en.wikipedia.org/wiki/M%C3%B6bius_transformation#Formula_for_the_inverse_transformation) and [StackOverflow](https://math.stackexchange.com/questions/3807648/deriving-a-m%C3%B6bius-transformation-specified-by-three-points) to figure out how to recover the image of a fourth point, given preimage-image pairs of three points.
The implementation of the method described is fairly straightforward.
The only issue I had was with floating-point precision, and I got around that with `gmpy2`.

`tjctf{ar3_y0u_a_c1rcl3_0r_a_l1n3?}`
