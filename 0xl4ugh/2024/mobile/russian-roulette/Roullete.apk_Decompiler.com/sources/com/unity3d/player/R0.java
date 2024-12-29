package com.unity3d.player;

import android.view.WindowInsets;

final class R0 extends Q0 {
    final /* synthetic */ WindowInsets b;
    final /* synthetic */ C0014e1 c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    R0(C0014e1 e1Var, WindowInsets windowInsets) {
        super(e1Var.a);
        this.c = e1Var;
        this.b = windowInsets;
    }

    public final void a() {
        this.c.a.nativeOnApplyWindowInsets(this.b);
    }
}
