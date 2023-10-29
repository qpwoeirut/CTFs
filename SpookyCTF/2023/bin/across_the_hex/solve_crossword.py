import itertools

with open("crossword.bin", 'rb') as f:
    contents = f.read()
    crossword = [contents[i: i + 16] for i in range(0, len(contents), 16)]

R = 512
C = 16
DIRS = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]
WORDS = ["spooky", "hacker", "alien", "ghost", "pumpkin", "nicc", "ctf", "cybersecurity"]

LEET = {
    '4': 'a',
    '3': 'e',
    '6': 'g',
    '1': 'i',
    '0': 'o',
    '5': 's',
    '7': 't'
}

assert len(crossword) == R
assert all([len(row) == C for row in crossword])


def extract_chars(r: int, c: int, dr: int, dc: int) -> bytes:
    s = bytearray()
    while 0 <= r < R and 0 <= c < C:
        s.append(crossword[r][c])
        r += dr
        c += dc
    return s


# I think it's possible to have a sequence that maps to two valid strings ('g' could be 'g' or 'gt' since 'g' = 0x67)
# but let's pretend that isn't possible and match greedily
def extract_match(s: bytes, word: str) -> tuple[bytes, str] or None:
    i_s = 0
    i_w = 0
    raw_match = bytearray()
    processed_match = []
    while i_s < len(s):
        as_hex = hex(s[i_s])[2:].zfill(2)
        if char_match(word[i_w], chr(s[i_s])):
            raw_match.append(s[i_s])
            processed_match.append(chr(s[i_s]))
            i_s += 1
            i_w += 1
        elif i_s == 0 and as_hex[0] == '0' and char_match(word[i_w], as_hex[1]):
            raw_match.append(s[i_s])
            processed_match.append(as_hex[1].upper())
            i_s += 1
            i_w += 1
        elif i_s == 0 and as_hex[1] == '0' and char_match(word[i_w], as_hex[0]):
            raw_match.append(s[i_s])
            processed_match.append(as_hex[0].upper())
            i_s += 1
            i_w += 1
        elif i_w + 1 < len(word) and char_match(word[i_w], as_hex[0]) and char_match(word[i_w + 1], as_hex[1]):
            raw_match.append(s[i_s])
            processed_match.append(as_hex.upper())
            i_s += 1
            i_w += 2
        elif i_w + 1 < len(word) and char_match(word[i_w], as_hex[1]) and char_match(word[i_w + 1], as_hex[0]):
            raw_match.append(s[i_s])
            processed_match.append(as_hex.upper())
            i_s += 1
            i_w += 2
        elif i_s + 1 == len(s) and as_hex[1] == '0' and i_w + 1 == len(word) and char_match(word[i_w], as_hex[0]):
            raw_match.append(s[i_s])
            processed_match.append(as_hex[0].upper())
            i_s += 1
            i_w += 2
        elif i_s + 1 == len(s) and as_hex[0] == '0' and i_w + 1 == len(word) and char_match(word[i_w], as_hex[1]):
            raw_match.append(s[i_s])
            processed_match.append(as_hex[1].upper())
            i_s += 1
            i_w += 2
        else:
            break

        if i_w >= len(word):
            return raw_match, processed_match

    return None


def char_match(c1: str, c2: str) -> bool:
    if len(c1) == 0 or len(c2) == 0:
        return len(c1) == len(c2)
    c2 = c2.lower()
    return c1 == c2 or (c1 in LEET and LEET[c1] == c2) or (c2 in LEET and LEET[c2] == c1)


def from_hex_or_empty(s: bytes) -> str:
    try:
        return chr(int(s, 16))
    except ValueError:
        return ""


def list_positions(r: int, c: int, dr: int, dc: int, processed_match: str) -> list[tuple[int, int, str]]:
    positions = []
    for ch in processed_match:
        positions.append((r, c, ch))
        r += dr
        c += dc
    return positions


def find_key(exclude: list[tuple[int, int]]) -> str:
    positions = []
    for r in range(R):
        for c in range(C):
            for dr, dc in DIRS:
                s = extract_chars(r, c, dr, dc)

                for word in WORDS:
                    match = extract_match(s, word)
                    if match is not None and (r, c) not in exclude:
                        raw, processed = match
                        print(f"row={r}, col={c}, dir_row={dr}, dir_col={dc}, raw_text={raw}, processed={processed}")
                        positions.extend(list_positions(r, c, dr, dc, processed))
    positions.sort()
    key = ""
    for r, c, char in positions:
        # print(r, c, hex(crossword[r][c])[2:].zfill(2), char)
        key += char
    return key


def main():
    ctf_instances = [(44, 0), (184, 8), (398, 12)]
    for excludes in itertools.combinations(ctf_instances, r=len(ctf_instances) - 1):
        print(find_key(list(excludes)))


if __name__ == '__main__':
    main()

# Tried:
# cF7yk00p5AliEnniCCts0h6chYACbK33rrS3CUr17yn1kpMuP
# cF7yk00p5AlienniCCts0h6chyACbk33rrs3Cur17yn1kpmup
# yk00p5F7cAliEnniCCts0h6chYACF7bKC33rrS3CUr17yn1kpMuP
# yk00p5F7cAliEnniCCts0h6cYF7bC3rS3CUr17yn1kpMuP
# yk00p5AliEnniCCts0h6chYACF7bKC33rrS3CUr17yn1kpMuP
# yk00p5F7cAlienniCCts0h6cyF7bC3rs3Cur17yn1kpmup

# flag: NICC{yk00p5AliEnniCCts0h6chYACF7bKC33rrS3CUr17yn1kpMuP}
