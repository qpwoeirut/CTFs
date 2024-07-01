undefined4 check(uint a, uint b, uint c, uint d, uint e, uint f) {
    if (a < 100000001 || b < 100000001 || c < 100000001 || d < 100000001 || e < 100000001 || f < 100000001) {
        ok = 0;
    } else if (a < 1000000000 && b < 1000000000 && c < 1000000000 && d < 1000000000 && e < 1000000000 && f < 1000000000) {
        uVar1 = a+c-b;
        uVar2 = a+b;
        uVar3 = 3*a; // only used as an intermediate variable during calculations, not in checker
        uVar4 = 3*a-2*b;
        uVar5 = a^d;
        uVar6 = b&(c+a);
        uVar10 = b+d;
        uVar7 = c^(d+f);
        uVar8 = e-f;
        uVar9 = e+f;
        if (
            (uVar1 % 17492321 == 4139449) &&
            (uVar2 % 17381917 == 9166034) &&
            (uVar4 % uVar5 == 556569677) &&
            (uVar6 % 28194 == 12734) &&
            ((int)((uVar10 & 4294967295) % (ulong)a) == 540591164) &&
            (uVar7 % 1893928 == 1279714) &&
            (uVar8 % 18294018 == 17026895) &&
            (uVar9 % 48328579 == 23769303)
        ) {
            ok = 1;
        } else {
            ok = 0;
        }
    } else {
        ok = 0;
    }
    return ok;
}
