package com.unity3d.player;

final class A1 implements Runnable {
    final /* synthetic */ B1 a;

    A1(B1 b1) {
        this.a = b1;
    }

    public final void run() {
        H1 h1 = this.a.a.h;
        z1 r1 = h1.f;
        if (r1 != null) {
            h1.a.removeViewFromPlayer(r1);
            h1.i = false;
            h1.f.destroyPlayer();
            h1.f = null;
            G1 r0 = h1.c;
            if (r0 != null) {
                ((I0) r0).a();
            }
        }
        this.a.a.h.a.onResume();
    }
}
