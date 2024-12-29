package com.unity3d.player;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;

/* renamed from: com.unity3d.player.n  reason: case insensitive filesystem */
final class C0035n {
    private final Context a;
    private final AudioManager b;
    private C0031l c;

    public C0035n(Context context) {
        this.a = context;
        this.b = (AudioManager) context.getSystemService("audio");
    }

    public final void a() {
        if (this.c != null) {
            this.a.getContentResolver().unregisterContentObserver(this.c);
            this.c = null;
        }
    }

    public final void a(C0033m mVar) {
        this.c = new C0031l(new Handler(Looper.getMainLooper()), this.b, mVar);
        this.a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.c);
    }
}
