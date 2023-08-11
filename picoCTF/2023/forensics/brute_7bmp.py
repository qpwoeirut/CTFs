import itertools
from subprocess import Popen, PIPE


def try_password(password: str) -> bool:
    process = Popen(['/opt/local/bin/steghide', 'extract', '-sf', 'home/yone/gallery/7.bmp', '-p', password], stderr=PIPE)
    stdiout, stderr = process.communicate()
    return b"could not extract any data with that passphrase" not in stderr

def main():
    with open("league_of_legends_characters.txt") as f:
        characters = [line.strip().lower() for line in f]

    prefix = "yasuoaatrox"
    for r in range(1, 4):
        print(r)
        for combo in itertools.permutations(characters, r=r):
            password = prefix + ''.join(combo)
            if try_password(password):
                print("PASSWORD:", password)  # yasuoaatroxashecassiopeia


if __name__ == '__main__':
    main()
