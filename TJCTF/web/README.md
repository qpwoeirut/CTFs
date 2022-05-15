# TJCTF 2022 Web

## lamb-sauce
> Author: kfb<br>
> where's the lamb sauce
> 
> [lamb-sauce.tjc.tf](https://lamb-sauce.tjc.tf/)

Inspect element finds a commented-out link to `/flag-9291f0c1-0feb-40aa-af3c-d61031fd9896.txt`.
Going to that page yields the flag.

`tjctf{idk_man_but_here's_a_flag_462c964f0a177541}`

## game-leaderboard
> Author: andy<br>
> I (superandypancake) signed up for this tournament to win a flag! Unfortunately, I'm not so good. But I'm sure there still is a way to get the flag... right?!?
>
> [game-leaderboard.tjc.tf](https://game-leaderboard.tjc.tf/)
>
> Downloads: [index.js](https://tjctf-2022.storage.googleapis.com/uploads/1ab35738265d2438d863d75fc7fdf3727eaf4d2da66493163beef4f18245c8ca/index.js)

Looking through index.js, we can see that we need to find the first rank's `profile_id` to get the flag.
We can also see that the score filter is SQL-injectable if we make requests ourselves instead of through the webpage.
To find the first place's `profile_id`, we can filter out the other players by score (with score=75, for example) and then query prefixes until we find the full `profile_id`.

See [solve_game_leaderboard.py](/TJCTF/web/game-leaderboard/solve_game_leaderboard.py) for implementation.

`tjctf{h3llo_w1nn3r_0r_4re_y0u?}`


## ascordle
> Author: andy<br>
> I found this new worldle clone...
> 
> [ascordle.tjc.tf](https://ascordle.tjc.tf/)
> 
> Downloads: [server.zip](https://tjctf-2022.storage.googleapis.com/uploads/63a44267ee10f9bde36e787296b422aa6e1376e8042f5ddb8378575566286098/server.zip)

The `checkWord` function is SQL-injectable, but some strings are blacklisted.
Both `OR` and `or` are banned, but `Or` isn't.
`=`, `>`, and `<` are banned as well, but we just need any statement that evaluates to true and is within the character limit.

`' Or 'a' LIKE 'a` works and happens to have the exact right amount of characters (although adding extra spaces to a shorter query would've worked too).
The query becomes `SELECT * FROM answer WHERE word = '' Or 'a' LIKE 'a'`, which is always true.

`tjctf{i_h3ck1n_l0v3_a5c11_w0rdl3}`


## photoable
> Author: laoweiaustin<br>
> My games always play at like 3 fps, so I thought it'd be more efficient to send individual frames rather than videos. Anyways, I'm sure my website is unhackable, and that you are never gonna find the flag on my server!
> 
> photoable.tjc.tf
> 
> Downloads: [server.zip](https://tjctf-2022.storage.googleapis.com/uploads/042cdecae9354649bf57d899c61a28d41ccae32fded96995dc2f5c2ee92f097e/server.zip)

Poking around through the image display page shows that the image path includes a `../` to traverse to the parent directory.
We can try requesting `flag.txt` directly to get the flag.

`https://photoable.tjc.tf/image/..%2fflag.txt/download`

`tjctf{1fram3_1fl4g}`

## fruit-store
> Author: kpdfgo<br>
> :lemonthink:
> 
> [Instancer](https://instancer.tjctf.org/fruit-store)
> 
> Downloads: [server.zip](https://tjctf-2022.storage.googleapis.com/uploads/3c9c44c226a0736e4bba9d8a3b6d805e301fac85d6abc93fc2762175215a006e/server.zip)

There's nothing stopping us from ordering a non-whole amount of fruit.
We can order `0.00000000000000000000000000001` grass fruits to get the flag.

`tjctf{h4v3_y0u_ev3r_tri3d_gr4s5_j3l1y_d4ebd9}`