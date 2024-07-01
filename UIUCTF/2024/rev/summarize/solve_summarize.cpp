#include <iostream>
#include <cassert>

// [MIN, MAX)
const int MIN = 100000001;
const int MAX = 1000000000;

const int MOD1 = 17492321;
const int RES1 = 4139449;

const int MOD2 = 17381917;
const int RES2 = 9166034;
const int MIN2 = RES2 + MOD2 * ((2 * MIN - RES2 + MOD2 - 1) / MOD2);
static_assert(2 * MIN <= MIN2 && 2 * MIN > MIN2 - MOD2 && MIN2 % MOD2 == RES2);

const int RES4 = 556569677;

const int MAX5 = 1073741824;  // 2**30

const int MOD6 = 28194;
const int RES6 = 12734;

const int RES10 = 540591164;

const int MOD7 = 1893928;
const int RES7 = 1279714;

const int MOD8 = 18294018;
const int RES8 = 17026895;

const int MOD9 = 48328579;
const int RES9 = 23769303;

const int EF_OPTIONS = 450;

int main() {
    int n_ef = 0;
    int e_opts[EF_OPTIONS];
    int f_opts[EF_OPTIONS];
    for (int var8 = RES8; var8 < MAX; var8 += MOD8) {  // 54
        for (long long var9 = RES9; var9 < 2 * MAX; var9 += MOD9) {  // 41
            if ((var8 + var9) & 1) continue;
            const int e = (var8 + var9) >> 1;
            const int f = var9 - e;
            if (e < MIN || e >= MAX || f < MIN || f >= MAX) continue;
            e_opts[n_ef] = e;
            f_opts[n_ef++] = f;
        }
    }

    assert(n_ef == EF_OPTIONS);
    std::cout << "There are " << n_ef << " options for e and f." << std::endl;

    for (int var5 = RES4 + 1; var5 < MAX5; ++var5) {  // 5.17e8
        if ((var5 & 0xffffff) == 0) std::cerr << "var5 = " << var5 << std::endl;
        for (unsigned int var4 = RES4; var4 < 3u * MAX; var4 += var5) {  // 5
            for (unsigned int var2 = MIN2; var2 < 2 * MAX; var2 += MOD2) {  // 115
                if ((2 * var2 + var4) % 5 != 0) continue;

                const int a = (2 * var2 + var4) / 5;
                const int b = (int)var2 - a;
                const int d = var5 ^ a;

                if (a < MIN || a >= MAX || b < MIN || b >= MAX || d < MIN || d >= MAX) continue;

                const int var10 = b + d;
                if (var10 % a != RES10) continue;

                std::cerr << "a, b, d: " << a << ' ' << b << ' ' << d << std::endl;

                for (int var1 = RES1; var1 < 2 * MAX; var1 += MOD1) {  // 114
                    const int c = var1 - a + b;
                    if (c < MIN || c >= MAX) continue;

                    const int var6 = b & (c + a);
                    if (var6 % MOD6 != RES6) continue;

                    for (int i = 0; i < EF_OPTIONS; ++i) {
                        const int var7 = c ^ (d + f_opts[i]);
                        if (var7 % MOD7 != RES7) continue;

                        const int e = e_opts[i];
                        const int f = f_opts[i];
                        std::cout << "Found an answer!\n";
                        std::cout << a << ' ' << b << ' ' << c << ' ' << d << ' ' << e << ' ' << f << std::endl;
                    }
                }
            }
        }
    }
}
