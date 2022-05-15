#include <cassert>
#include <complex>
#include <iomanip>
#include <iostream>
#include <vector>

#include <gmpxx.h>

using namespace std;

constexpr int MAX_SIEVE = 1e9 + 1;

bool sieve[MAX_SIEVE];  // true for composite, false for prime

mpz_class pollard_rho(const mpz_class n) {
    mpz_class U = 2, V = 2;
    while (1) {
        U = (U * U + 1) % n;
        V = (V * V + 1) % n;
        V = (V * V + 1) % n;
        
        mpz_class g = gcd(U - V, n);
        if (g > 1) {
            return g;
        }
    }
}

mpz_class fermat_factorization(const mpz_class n) {
    mpz_class a = sqrt(n) + 1;  // ceil of sqrt, works since n isn't perfect square
    mpz_class s = a * a - n;

    while (mpz_perfect_square_p(s.get_mpz_t()) == 0) {
        ++a;
        s = a * a - n;
    }
    return a - sqrt(s);
}

mpz_class pollard_pm1(const mpz_class n, const int B) {
    //cerr << "B = " << B << endl;
    assert(B < MAX_SIEVE);

    mpz_class aM = 2;
    for (int p=2; p<=B; ++p) {
        if (sieve[p]) continue;
        int tmp = B;
        mpz_class e = 1;
        while (tmp >= p) {
            tmp /= p;
            e *= p;
        }
        mpz_powm(aM.get_mpz_t(), aM.get_mpz_t(), e.get_mpz_t(), n.get_mpz_t());
    }

    return gcd(aM - 1, n);
}

string mpz_to_string(const mpz_t n) {
    char* buf = nullptr;
    mpz_get_str(buf, 10, n);
    return string(buf);
}

int main() {
    int prime_ct = 0;
    for (long long i=2; i<MAX_SIEVE; ++i) {
        if (sieve[i]) continue;
        for (long long j=i*i; j<MAX_SIEVE; j+=i) {
            sieve[j] = true;
        }
        ++prime_ct;
    }
    cout << "Sieve done with " << prime_ct << " primes" << endl;
    cout << "Ready!" << endl;

    mpz_class n, p, q;

    //cerr << "Challenge 1:" << endl;
    cin >> n;
    p = pollard_rho(n);
    q = n / p;

    //cout << p << ' ' << q << endl;
    cout << p << endl;

    //cerr << "Challenge 2:" << endl;
    cin >> n;
    p = fermat_factorization(n);
    q = n / p;

    //cout << p << ' ' << q << endl;
    cout << p << endl;

    //cerr << "Challenge 3:" << endl;
    cin >> n;

    int B = 2e6;
    p = pollard_pm1(n, B);
    while (p == 1 || p == n) {
        if (p == 1) {
            B *= 5;
        } else {
            B /= 2;
        }
        p = pollard_pm1(n, B);
    }
    q = n / p;

    //cout << p << ' ' << q << endl;
    cout << p << endl;
}
