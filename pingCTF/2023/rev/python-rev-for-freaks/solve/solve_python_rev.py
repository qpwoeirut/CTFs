target = [(13969439442922757926633137632, 3251133470245911671632840864),
          (6919844817045365871489845728, 3067821989026578174692487328),
          (11408842561461143227463443808, 3766356150094573135206359136),
          (11299068421490417286376379488, 3802947530149782083826679648),
          (9203465938188223031329433888, 2306614948612889330244181216),
          (9753400381846729757945770272, 4656479823873291748257812704)]


def inv_l(x):
    return ((x // 3862272608) ^ 12648430) - 3735928559


def invert(output):
    first = [inv_l(a) // 6 for a, b in output]
    second = [inv_l(b) // 2 for a, b in output][::-1]

    assert first == second
    return first


# for flag_input = "a"
assert invert([(8720086638672508234950878752, 2906695555875935187165459360)]) == [376293420533998489]

print(invert(target))
print(*map(hex, invert(target)))