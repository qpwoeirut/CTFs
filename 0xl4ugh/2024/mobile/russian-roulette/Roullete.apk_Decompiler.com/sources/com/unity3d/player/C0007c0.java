package com.unity3d.player;

import android.database.ContentObserver;
import android.os.Handler;

/* renamed from: com.unity3d.player.c0  reason: case insensitive filesystem */
final class C0007c0 extends ContentObserver {
    private C0004b0 a;

    public C0007c0(Handler handler, C0004b0 b0Var) {
        super(handler);
        this.a = b0Var;
    }

    public final boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    public final void onChange(boolean z) {
        C0004b0 b0Var = this.a;
        if (b0Var != null) {
            ((OrientationLockListener) b0Var).b();
        }
    }
}
