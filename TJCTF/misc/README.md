# TJCTF 2022 Misc

## twist-cord
> Author: TJCTF Organizers<br>
> Welcome! Join our [Discord](https://discord.com/invite/w5xapGP) for announcements and challenge updates.
>
> Also check us out on [Twitter](https://twitter.com/tjctf)!


The first half of the flag is in Discord announcements: `tjctf{please_enjoy`.
The second half is on Twitter: `_b6fd3b11fc5393c8}`.

`tjctf{please_enjoy_b6fd3b11fc5393c8}`


## cheapest-cookies-2
> Author: andy<br>
> Now that Andrew knows which Costco has the cheapest cookies, he has to get there - as quickly as possible! He has given you 40 roads with the two endpoint locations and the distance of the road, and he starts at location 0 and the Costco is at location 20. All roads are 2-way roads, meaning you can go from x to y and from y to x. Please output the minimum distance needed to reach the Costco, and if there is no possible path, print -1. You will need to pass fifty tests to get the flag. And don't forget to be fast!
>
> Sample Input:
> ```
> 0 20 18
> 4 8 2
> 0 4 8
> 4 20 6
> ```
> Sample Output:
> ```
> 14
> ```
> `nc tjc.tf 31111`


This is a classical shortest-path problem.
Since the number of nodes is only 21, running Floyd-Warshall in O(N<sup>3</sup>) is fast enough.

See [solve_cheapest_cookies_2.py](/TJCTF/misc/cheapest-cookies-2/solve_cheapest_cookies_2.py) for implementation.

`tjctf{w00_w3_have_th3_c00k1es_n0w}`


`tjctf{gg_go_next}`