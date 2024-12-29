package com.unity3d.player.a;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

final class c extends ConnectivityManager.NetworkCallback {
    final /* synthetic */ d a;

    c(d dVar) {
        this.a = dVar;
    }

    public final void onAvailable(Network network) {
        super.onAvailable(network);
    }

    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        int i;
        d dVar;
        super.onCapabilitiesChanged(network, networkCapabilities);
        if (networkCapabilities.hasTransport(0)) {
            dVar = this.a;
            i = 1;
        } else {
            dVar = this.a;
            i = 2;
        }
        dVar.b = i;
    }

    public final void onLost(Network network) {
        super.onLost(network);
        this.a.b = 0;
    }

    public final void onUnavailable() {
        super.onUnavailable();
        this.a.b = 0;
    }
}
