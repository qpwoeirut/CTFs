"""
https://web.archive.org/web/20130827121726/http://en.wikipedia.org/wiki/List_of_Bank_Identification_Numbers

The current page (https://en.wikipedia.org/wiki/Payment_card_number) does not have this info

let s = "";
for (let row of tbody.children) {
    s += `'${row.children[0].textContent.trim()}': [${row.children[1].textContent.trim().split(', ').map(s => '\'' + s.toString() + '\'')}]\n`;
}
console.log(s);

Some manual merging and editing required.
"""
import ast
import logging

from pwn import remote
from typing import Optional

bins = {
    'American Express': ['34', '37'],
    'Bankcard': ['5610', '560221–560225'],
    'China T-Union': ['31'],
    'China UnionPay': ['62'],
    'Diners Club enRoute': [],
    'Diners Club International': ['36'],
    'Diners Club United States & Canada': ['55'],
    'Discover Card': ['6011', '644–649', '65', '622126–622925'],
    'UkrCard': ['60400100–60420099'],
    'RuPay': ['60', '65', '81', '82', '508', '353', '356'],
    'InterPayment': ['636'],
    'InstaPayment': ['637–639'],
    'JCB': ['3528–3589'],
    'Laser': ['6304', '6706', '6771', '6709'],
    'Maestro UK': ['6759', '676770', '676774'],
    'Maestro': ['5018', '5020', '5038', '5893', '6304', '6759', '6761', '6762', '6763'],
    'Dankort': ['5019', '4571'],
    'Mir': ['2200–2204'],
    'BORICA (Bulgarian national payment system)': ['2205'],
    'NPS Pridnestrovie': ['6054740–6054744'],
    'Mastercard': ['2221–2720', '51–55'],
    'Solo': ['6334', '6767'],
    'Switch': ['4903', '4905', '4911', '4936', '564182', '633110', '6333', '6759'],
    'Troy': ['65', '9792'],
    'Visa': ['4'],
    'Visa Electron': ['4026', '417500', '4508', '4844', '4913', '4917'],
    # 'UATP': ['1'],

    # updated manually using https://bintable.com/scheme/UATP
    'UATP': ['1001', '1005–1007', '1016', '1037', '1053', '1065', '1075', '1081', '1086', '1105', '1112', '1114',
             '1125', '1127', '1131', '1139', '1157', '1169', '1220', '1232', '1234', '1235', '1257', '1333', '1526',
             '1556', '1607', '1611', '1617', '1781', '1800', '1920', '1953', '1957'],
    'Verve': ['506099–506198', '650002–650027', '507865–507964'],
    'LankaPay': ['357111'],
    'UzCard': ['8600', '5614'],
    'Humo': ['9860'],
    'GPN': ['1946', '50', '56', '58', '60–63'],
    'Napas': ['9704'],
}
# use endash, not hyphen


def luhn(pan: str) -> bool:
    checksum = 0
    for i, x in enumerate(pan[::-1][1:]):
        if i % 2 == 1:
            checksum += int(x)
        elif int(x) >= 5:
            checksum += 2 * int(x) - 9
        else:
            checksum += 2 * int(x)
    return int(pan[-1]) == (10 - (checksum % 10)) % 10


def check_prefix(prefix: str, pan: str) -> bool:
    assert set(prefix) <= set("0123456789–"), prefix
    assert prefix.count('–') <= 1, prefix

    if '–' in prefix:
        lo, hi = prefix.split('–')
        assert len(lo) == len(hi)
        to_check = pan[:len(lo)]
        return lo <= to_check <= hi

    return pan.startswith(prefix)


def check_bin(pan: str) -> Optional[str]:
    for bank, prefixes in bins.items():
        if any(check_prefix(prefix, pan) for prefix in prefixes):
            return bank
    return None


def date_valid(date: str) -> bool:
    assert len(date) == 4, date
    month, year = date[:2], date[2:]
    return 1 <= int(month) <= 12 and 24 <= int(year)


def prefix_match(a: str, b: str) -> int:
    for i in range(len(a)):
        if i >= len(b) or a[i] != b[i]:
            return i
    return len(a)


def extrapolate(known: list[tuple[str, str, str, str, bool]], pan: str) -> bool:
    best = ''
    answer = None
    for known_pan, date, _, _, result in known:
        if len(known_pan) == len(pan) and date_valid(date) and luhn(known_pan) and prefix_match(best, pan) < prefix_match(known_pan, pan):
            best = known_pan
            answer = result

    if answer is None:
        return check_bin(pan) is not None
    else:
        return answer


def main():
    from pwnlib.log import getLogger
    log = getLogger("pwnlib.tubes.remote")
    log.setLevel(logging.ERROR)

    r = remote("puffer.utctf.live", 8625)
    r.recvuntil(b'check these again.\n')
    assert r.recvline().decode().isspace()

    try:
        with open("known.txt", 'r') as f:
            known = [ast.literal_eval(line) for line in f]
    except FileNotFoundError:
        known = []
    answers = ""
    lengths = []
    while True:
        with open("known.txt", 'w') as f:
            f.writelines([str(x) + '\n' for x in known])
        try:
            print(answers)
            query = r.recvline(keepends=False).decode().split(', ')
            r.recvline()

            pan = query[0].split(': ')[1]
            date = query[1].split(': ')[1]
            code = query[2].split(': ')[1]
            cvv = query[3].split(': ')[1]

            answer = date_valid(date) and luhn(pan) # and extrapolate(known, pan)

            answers += '1' if answer else '0'
            r.sendline(answers[-1].encode())
            r.recvline()

            known.append((pan, date, code, cvv, answer))
        except EOFError:
            known[-1] = known[-1][0], known[-1][1], known[-1][2], known[-1][3], not known[-1][4]
            lengths.append(len(answers))
            answers = ""
            print(sum(lengths) / len(lengths))

            print(known[-1])

            r = remote("puffer.utctf.live", 8625)
            r.recvuntil(b'check these again.\n')
            assert r.recvline().decode().isspace()


if __name__ == '__main__':
    main()
