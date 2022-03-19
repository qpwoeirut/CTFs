# Web
Since most challenges don't have any files to download, I'll store all the writeups for them in this file.

## Includes
Tags: Web Exploitation, inspector<br>
Author: LT 'Syreal' Jones

> ### Description
>Can you get the flag?
Go to this [website](http://saturn.picoctf.net:52811/) and see what you can discover.

Use the Sources tab in DevTools to examine the `script.js` and `style.css` files.
The flag parts are in comments.

`picoCTF{1nclu51v17y_1of2_f7w_2of2_3d50f001}`


## Inspect HTML
Tags: Web Exploitation, inspector<br>
Author: LT 'Syreal' Jones

> ### Description
>Can you get the flag?
Go to this [website](http://saturn.picoctf.net:60935/) and see what you can discover.

Use the Elements or Sources tab in DevTools to examine the HTML page.
The flag is in a comment.

`picoCTF{1n5p3t0r_0f_h7ml_ab1df88d}`


## Local Authority
Tags: Web Exploitation, inspector<br>
Author: LT 'Syreal' Jones

> ### Description
>Can you get the flag?
Go to this [website](http://saturn.picoctf.net:51419/) and see what you can discover.

Inspecting the linked page doesn't yield anything immediately useful, so we can try submitting some bogus credentials and seeing what happens.
The login failure page has a script embedded in it, which seems to be checking if the user logged in correctly or not.

```js
if ( usernameFilterPassed && passwordFilterPassed ) {
    loggedIn = checkPassword(window.username, window.password);
    if(loggedIn)
    {
        document.getElementById('msg').innerHTML = "Log In Successful";
        document.getElementById('adminFormHash').value = "2196812e91c29df34f5e217cfd639881";
        document.getElementById('hiddenAdminForm').submit();
    }
    else
    {
        document.getElementById('msg').innerHTML = "Log In Failed";
    }
}
else {
    document.getElementById('msg').innerHTML = "Illegal character in username or password."
}
```

We can copy the script that would run if we did log in successfully and execute it ourselves in the Console tab, simulating a successful login.
That sends us to a page with the flag.

`picoCTF{j5_15_7r4n5p4r3n7_d6a44d91}`


## Search Source
Tags: Web Exploitation<br>
Author: Mubarak Mikail

> ### Description
> The developer of this website mistakenly left an important artifact in the website source, can you find it?<br>
The website is [here](http://saturn.picoctf.net:64200/)

I originally thought this challenge had to do with the Google Maps/Fonts API keys, but that was wrong.

Under the DevTools Sources tab, there's a Pages sidebar.
Right-clicking the top level of the hierarchy and selecting "Search in all files" opens up a search interface.
Searching "pico" finds the flag in `style.css`.

`picoCTF{1nsp3ti0n_0f_w3bpag3s_3003ba70}`


## Forbidden Paths
Tags: Web Exploitation<br>
Author: LT 'Syreal' Jones

> ### Description
>Can you get the flag?<br>
Here's the [website](http://saturn.picoctf.net:52278/). <br>
We know that the website files live in `/usr/share/nginx/html/` and the flag is at `/flag.txt` but the website is filtering absolute file paths. Can you get past the filter to read the flag?

Although absolute paths are filtered, we can still traverse up to parent directories.
Since the website files are nested within 4 folders, we can travel up 4 directories to reach the root and then read `flag.txt`.
To do this, we query `../../../../flag.txt`.

`picoCTF{7h3_p47h_70_5ucc355_57e411a1}`


## Power Cookie
Tags: Web Exploitation, cookie<br>
Author: LT 'Syreal' Jones

> ### Description
>Can you get the flag?
Go to this [website](http://saturn.picoctf.net:51532/) and see what you can discover.

As the title hints, this challenge is about web cookies.
After clicking the `Continue as guest` button, checking the cookies tab in DevTools reveals an `isAdmin` cookie, which as a value of `0`.
Changing this value to `1` and reloading the page gets the flag.

`picoCTF{gr4d3_A_c00k13_1d871e17}`


## Roboto Sans
Tags: Web Exploitation<br>
Author: Mubarak Mikail

> ### Description
>The flag is somewhere on this web application not necessarily on the website. Find it.<br>
Check [this](http://saturn.picoctf.net:57688/) out.

As the title hints, this challenge is about the `robots.txt` file.
Accessing that file shows the following content:
```
User-agent *
Disallow: /cgi-bin/
Think you have seen your flag or want to keep looking.

ZmxhZzEudHh0;anMvbXlmaW
anMvbXlmaWxlLnR4dA==
svssshjweuiwl;oiho.bsvdaslejg
Disallow: /wp-admin/
```

The base64-encoded strings are of particular interest.
The first line is two parts, which decodes into `flag1.txt;js/myfi`, but both give 404 errors.
The second line is `js/myfile.txt`, which holds the flag.

`picoCTF{Who_D03sN7_L1k5_90B0T5_f243a0a1}`


## Secrets
Tags: Web Exploitation<br>
Author: Geoffrey Njogu

> ### Description
> We have several pages hidden. Can you find the one with the flag?<br>
The website is running [here](http://saturn.picoctf.net:58314/).

I solved this challenge by randomly trying paths until I hit `/secret/`, which was a valid page.
The trailing slash is important; you'll get an error without it.
Then, we can see that the HTML imports a file from `/secret/hidden/`, which imports a file from a `/secret/hidden/superhidden/` directory.
Going to `/secret/hidden/superhidden/` gets us a page with the flag, which we can see through Inspect Element.

`picoCTF{succ3ss_@h3n1c@10n_9887a570}`


## SQL Direct
Tags: Web Exploitation, sql<br>
Author: Mubarak Mikail / LT 'Syreal' Jones

> ### Description
> Connect to this PostgreSQL server and find the flag!

This challenge launched instances on-demand.

After connecting to the server (which I did through the webshell), running `\?` and `\h` will provide a list of commands.
We're looking for `\d`, which lists the tables in the database.
This shows us that the only table is called `flags`.
Then we can run a query on the table, such as `SELECT * FROM flags;`, to get the flag.

`picoCTF{L3arN_S0m3_5qL_t0d4Y_a26695df}`


## SQLiLite
Tags: Web Exploitation, sql<br>
Author: Mubarak Mikail

> ### Description
> Can you login to this website?

This challenge launched instances on-demand.

We need to log in as `admin`.
Tossing some random credentials at the server lets us know that this is a SQL injection, since the executed SQL query is displayed.
To log in as admin, we can make our username `admin' OR 1=1--`, which will comment out the rest of the query and make it true.
Then we need to Inspect Element the page to actually read the flag.

`picoCTF{L00k5_l1k3_y0u_solv3d_it_8dac17f1}`
