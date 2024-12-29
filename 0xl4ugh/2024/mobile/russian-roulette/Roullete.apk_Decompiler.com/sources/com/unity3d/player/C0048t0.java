package com.unity3d.player;

import android.view.View;

/* renamed from: com.unity3d.player.t0  reason: case insensitive filesystem */
final class C0048t0 implements Runnable {
    final /* synthetic */ C0052v0 a;

    C0048t0(C0052v0 v0Var) {
        this.a = v0Var;
    }

    public final void run() {
        View view = this.a.a.getView();
        if (this.a.a.getContextType() == C0049u.GameActivity) {
            this.a.b = view.isClickable();
            view.setClickable(true);
            view.setOnCapturedPointerListener(new C0046s0(this));
        }
        view.requestPointerCapture();
    }
}
