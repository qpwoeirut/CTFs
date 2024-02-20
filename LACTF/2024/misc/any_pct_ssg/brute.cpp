#include <iostream>
#include <thread>
#include <vector>

using ll=long long;

const ll m = 1L << 52;
const ll mask = m - 1;
const __int128 a = 2760624790958533L;
const ll c = 4164880461924199L;

const ll lo = 9 * m / 10;

const ll START = lo + 8e13;
const ll FINISH = m;
static_assert(START >= lo);
static_assert(FINISH <= m);

const ll RANGE = FINISH - START;

const int THREADS = 7;

void brute(int i, std::vector<ll>& seeds) {
    const ll start = START + (i * RANGE / THREADS);
    const ll finish = START + ((i + 1) * RANGE / THREADS);
    for (ll s1 = start; s1 < finish; ++s1) {
        const ll s2 = (a * s1 + c) & mask;
        if (s2 > lo) {
            const ll s3 = (a * s2 + c) & mask;
            if (s3 > lo) {
                const ll s4 = (a * s3 + c) & mask;
                if (s4 > lo) {
                    const ll s5 = (a * s4 + c) & mask;
                    if (s5 > lo) {
                        const ll s6 = (a * s5 + c) & mask;
                        if (s6 > lo) {
                            const ll s7 = (a * s6 + c) & mask;
                            if (s7 > lo) {
                                const ll s8 = (a * s7 + c) & mask;
                                if (s8 > lo) {
                                    const ll s9 = (a * s8 + c) & mask;
                                    if (s9 > lo) {
                                        const ll s10 = (a * s9 + c) & mask;
                                        if (s10 > lo) {
                                            const ll s11 = (a * s10 + c) & mask;
                                            if (s11 > lo) {
                                                const ll s12 = (a * s11 + c) & mask;
                                                if (s12 > lo) {
                                                    const ll s13 = (a * s12 + c) & mask;
                                                    if (s13 > lo) {
                                                        const ll s14 = (a * s13 + c) & mask;
                                                        if (s14 > lo) {
                                                            seeds.push_back(s1);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

int main() {
    std::cout << "Searching in [" << START << ", " << FINISH << ")" << std::endl;
    std::cout << "Range = " << RANGE << std::endl;

    std::thread threads[THREADS];
    std::vector<ll> seeds[THREADS];
    for (int t=0; t<THREADS; ++t) {
        threads[t] = std::thread(brute, t, std::ref(seeds[t]));
    }
    for (int t=0; t<THREADS; ++t) {
        threads[t].join();
        for (const ll x: seeds[t]) {
            std::cout << x << '\n';
        }
    }
}
