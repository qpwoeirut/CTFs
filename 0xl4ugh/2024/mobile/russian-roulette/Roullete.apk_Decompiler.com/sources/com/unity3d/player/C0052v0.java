package com.unity3d.player;

import android.os.Build;

/* renamed from: com.unity3d.player.v0  reason: case insensitive filesystem */
final class C0052v0 {
    /* access modifiers changed from: private */
    public UnityPlayer a;
    /* access modifiers changed from: private */
    public boolean b;

    public C0052v0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.a.runOnUiThread(new C0050u0(this));
        }
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.a.runOnUiThread(new C0048t0(this));
        }
    }
}
