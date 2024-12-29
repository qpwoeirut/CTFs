package com.unity3d.player;

import com.unity3d.player.a.e;

class A {
    protected e a = null;
    protected Runnable b;
    protected boolean c = true;

    protected A(Runnable runnable) {
        this.b = runnable;
    }

    /* access modifiers changed from: protected */
    public void registerOnBackPressedCallback() {
        if (this.a == null) {
            this.a = new C0059z(this.b);
        }
    }

    /* access modifiers changed from: protected */
    public void unregisterOnBackPressedCallback() {
        this.a = null;
    }
}
