#include <bits/stdc++.h>

using namespace std;
using ll=long long;

int main() {
    cin.tie(0)->sync_with_stdio(0);

    ll n, a, b, m;
    cin >> n >> a >> b >> m;

    ll x = 0;
    ll ans = 0;
    while (n --> 0) {
        x = (x * a + b) % m;
        ans ^= x;
    }

    cout << ans << '\n';
}
