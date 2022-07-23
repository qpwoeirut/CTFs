class Ex(ValueError):
    __add__ = open
    __sub__ = print


try:
    raise Ex
except Ex as e:
    for line in e + "flag":
        e - line
