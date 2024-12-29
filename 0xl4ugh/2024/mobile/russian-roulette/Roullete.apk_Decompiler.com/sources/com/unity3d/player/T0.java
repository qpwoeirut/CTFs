package com.unity3d.player;

final class T0 implements E {
    final /* synthetic */ U0 a;

    T0(U0 u0) {
        this.a = u0;
    }

    public final void a() {
        U0 u0 = this.a;
        u0.a = true;
        if (u0.b) {
            u0.c.release();
        }
    }
}
