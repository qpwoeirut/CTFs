package com.unity3d.player.a;

import android.content.Context;
import android.net.ConnectivityManager;
import com.unity3d.player.C0057y;

public final class d extends C0057y {
    /* access modifiers changed from: private */
    public int b = 0;
    private final ConnectivityManager.NetworkCallback c;

    public d(Context context) {
        super(context);
        c cVar = new c(this);
        this.c = cVar;
        if (this.a != null) {
            this.b = super.b();
            this.a.registerDefaultNetworkCallback(cVar);
        }
    }

    public final void a() {
        ConnectivityManager connectivityManager = this.a;
        if (connectivityManager != null) {
            connectivityManager.unregisterNetworkCallback(this.c);
        }
    }

    public final int b() {
        return this.b;
    }
}
