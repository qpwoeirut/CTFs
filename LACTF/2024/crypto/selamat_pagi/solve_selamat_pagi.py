from utils.cryptography.analyze import quick_analyze


with open("message.txt") as f:
    message = f.read().upper()


quick_analyze(message)


# https://www.sttmedia.com/characterfrequency-indonesian
def replace_by_dict(s: str, table: dict) -> str:
    for k, v in table.items():
        s = s.replace(k, v)
    return s


key = {
    'B': 'l',
    'K': 'a',
    'V': 'c',
    'I': 't',
    'M': 'f',

    # https://en.wiktionary.org/wiki/Appendix:Indonesian_palindromes
    # assume EFE => ini = this
    'E': 'i',
    'F': 'n',

    # assume KQK => ada and KCK => apa
    'Q': 'd',
    'C': 'p',

    # assume second word is "adalah" (asked ChatGPT for what the last letter could be)
    "X": 'h',

    # assume last word before colon is "sini" (thanks ChatGPT)
    'W': 's',

    # assume third word is "pesan" (ChatGPT)
    'Z': 'e',

    # assume first word in flag is "selamat" (took ChatGPT two tries to figure that out)
    'D': 'm',

    # assume last word is frequency -> frekuensi because I assume analisis -> analysis
    'T': 'r',
    'U': 'k',
    'J': 'u',

    # dengan
    'S': 'g',


    # we have flag but let's finish it because yes
    # putting what we have into google translate and taking autocorrect options
    'A': 'y',

    # realized that message is talking about flag, flag -> bendera
    'L': 'b'
}

print(message)
print(replace_by_dict(message, key))