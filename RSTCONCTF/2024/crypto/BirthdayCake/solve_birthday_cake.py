# Could be done with John the Ripper, but I don't have it installed and the keyspace is small enough that Python works
import zipfile

with zipfile.ZipFile("birthdaycake.zip") as enc_zip:
    for m in range(12):
        for d in range(31):
            for y in range(99):
                pwd = str(m).zfill(2) + str(d).zfill(2) + str(y).zfill(2)
                try:
                    enc_zip.extractall(pwd=pwd.encode())
                    print(pwd)
                    exit()
                except (RuntimeError, zipfile.BadZipFile):
                    pass
