package com.unity3d.player;

final class E1 implements Runnable {
    final /* synthetic */ H1 a;

    E1(H1 h1) {
        this.a = h1;
    }

    public final void run() {
        H1 h1 = this.a;
        z1 r1 = h1.f;
        if (r1 != null) {
            h1.a.addViewToPlayer(r1, true);
            H1 h12 = this.a;
            h12.i = true;
            h12.f.requestFocus();
        }
    }
}
