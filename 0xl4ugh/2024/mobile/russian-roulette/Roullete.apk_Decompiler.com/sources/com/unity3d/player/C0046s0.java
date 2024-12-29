package com.unity3d.player;

import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnCapturedPointerListener;

/* renamed from: com.unity3d.player.s0  reason: case insensitive filesystem */
final class C0046s0 implements View$OnCapturedPointerListener {
    final /* synthetic */ C0048t0 a;

    C0046s0(C0048t0 t0Var) {
        this.a = t0Var;
    }

    public final boolean onCapturedPointer(View view, MotionEvent motionEvent) {
        return this.a.a.a.getActivity().onTouchEvent(motionEvent);
    }
}
