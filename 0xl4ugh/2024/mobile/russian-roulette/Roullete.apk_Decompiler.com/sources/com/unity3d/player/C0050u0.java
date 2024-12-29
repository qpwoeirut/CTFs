package com.unity3d.player;

import android.view.View;
import android.view.View$OnCapturedPointerListener;

/* renamed from: com.unity3d.player.u0  reason: case insensitive filesystem */
final class C0050u0 implements Runnable {
    final /* synthetic */ C0052v0 a;

    C0050u0(C0052v0 v0Var) {
        this.a = v0Var;
    }

    public final void run() {
        View view = this.a.a.getView();
        view.releasePointerCapture();
        if (this.a.a.getContextType() == C0049u.GameActivity) {
            view.setOnCapturedPointerListener((View$OnCapturedPointerListener) null);
            view.setClickable(this.a.b);
        }
    }
}
