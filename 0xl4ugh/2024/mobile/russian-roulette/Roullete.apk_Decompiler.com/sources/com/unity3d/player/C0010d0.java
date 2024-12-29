package com.unity3d.player;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;

/* renamed from: com.unity3d.player.d0  reason: case insensitive filesystem */
final class C0010d0 {
    private Context a;
    private C0007c0 b;

    public C0010d0(Context context) {
        this.a = context;
    }

    public final void a() {
        if (this.b != null) {
            this.a.getContentResolver().unregisterContentObserver(this.b);
            this.b = null;
        }
    }

    public final void a(C0004b0 b0Var) {
        this.b = new C0007c0(new Handler(Looper.getMainLooper()), b0Var);
        this.a.getContentResolver().registerContentObserver(Settings.System.getUriFor("accelerometer_rotation"), true, this.b);
    }
}
