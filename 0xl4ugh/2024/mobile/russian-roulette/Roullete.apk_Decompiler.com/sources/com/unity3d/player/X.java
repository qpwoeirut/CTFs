package com.unity3d.player;

import android.view.ViewTreeObserver;

final class X implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ C0001a0 a;

    X(C0001a0 a0Var) {
        this.a = a0Var;
    }

    public final void onGlobalLayout() {
        this.a.reportSoftInputArea();
    }
}
