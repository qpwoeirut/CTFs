Tried cloning with
```
git clone https://gitlab.com/b01lersparlay/GenerationalWealth
```

This apparently doesn't require any credentials, but there's nothing of note in the repository.
The memo references a "secret files feature", which brings up [Secure Files](https://docs.gitlab.com/ee/ci/secure_files/) in GitLab's docs.

From there, we can use the linked [Secure Files API](https://docs.gitlab.com/ee/api/secure_files.html) to download the files.
Figuring out the format for the token took a bit of messing around

```
curl --header "PRIVATE-TOKEN: glpat-R-Td9nSxVAHW-72qxt5M" "https://gitlab.com/api/v4/projects/56114917/secure_files"
curl --header "PRIVATE-TOKEN: glpat-R-Td9nSxVAHW-72qxt5M" "https://gitlab.com/api/v4/projects/56114917/secure_files/1070175/download"
```