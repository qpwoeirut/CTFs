# File types

Tags: Forensics<br>
Author: Geoffrey Njogu

> **Description**<br>
This file was found among some files marked confidential but my pdf reader cannot read it, maybe yours can.<br>
You can download the file from [here](https://artifacts.picoctf.net/c/329/Flag.pdf).

The table below lists the file types in order of extraction, along with the command to open them.
Each command file is listed with (what I think is) its proper extension, not the extension of the provided file.

| File type     | Command                   |
|---------------|---------------------------|
| shell archive | `sh flag.sh`              |
| ar            | `ar -p flag.ar > flag`    |
| cpio          | `cpio -i -vd < flag.cpio` |
| bzip2         | `bzip2 -kd flag.bz2`      |
| gzip          | `gzip -kd flag.gz`        |
| lzip          | `lzip -d flag.lz`         |
| LZ4           | `lz4 flag.lz4`            |
| LZMA          | `lzma -d flag.lzma`       |
| lzop          | `lzop -d flag.lzop`       |
| lzip          | `lzip -d flag.lz`         |
| XZ            | `xz -d flag.xz`           |

We finally get an ASCII text file with the flag encoded in hex, which can be decoded with a CyberChef [recipe](https://gchq.github.io/CyberChef/#recipe=From_Hex('Auto')&input=NzA2OTYzNmY0MzU0NDY3YjY2MzE2YzY1NmU0MDZkMzM1ZjZkNDA2ZTMxNzA3NTZjNDA3NDMxMzA2ZTVmCjY2MzA3MjVmMzA2MjMyNjM3NTcyMzEzNzc5NWYzMjM3Mzg2NjMxNjEzMTM4N2QwYQ).

The final program:
```
sh Flag.pdf
mv flag flag1.ar
ar -p flag1.ar > flag2.cpio
cpio -i -vd < flag2.cpio
mv flag flag3.bz2
bzip2 -kdc flag3.bz2 > flag
mv flag flag4.gz
gzip -kdc flag4.gz > flag
mv flag flag5.lz
lzip -d flag5.lz -o flag
mv flag flag6.lz4
lz4 -c flag6.lz4 > flag
mv flag flag7.lzma
lzma -dc flag7.lzma > flag
mv flag flag8.lzop
lzop -d flag8.lzop -o flag
mv flag flag9.lz
lzip -d flag9.lz -o flag
mv flag flag10.xz
xz -dc flag10.xz > flag
mv flag flag.txt
```

`picoCTF{f1len@m3_m@n1pul@t10n_f0r_0b2cur17y_278f1a18}`