package com.unity3d.player;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* renamed from: com.unity3d.player.w  reason: case insensitive filesystem */
final class C0053w extends FrameLayout {
    private UnityPlayerForActivityOrService a;
    private C0058y0 b;

    public C0053w(Context context, UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        super(context);
        this.a = unityPlayerForActivityOrService;
        C0058y0 y0Var = new C0058y0(unityPlayerForActivityOrService);
        this.b = y0Var;
        addView(y0Var);
    }

    public final C0058y0 a() {
        return this.b;
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (!this.b.d()) {
            return this.a.injectEvent(motionEvent);
        }
        return false;
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.a.injectEvent(keyEvent);
    }

    public final boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return this.a.injectEvent(keyEvent);
    }

    public final boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return this.a.injectEvent(keyEvent);
    }

    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.a.injectEvent(keyEvent);
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.b.d()) {
            return this.a.injectEvent(motionEvent);
        }
        return false;
    }
}
