package com.unity3d.player;

final class B1 implements x1 {
    final /* synthetic */ C1 a;

    B1(C1 c1) {
        this.a = c1;
    }

    public final void a(int i) {
        this.a.h.e.lock();
        H1 h1 = this.a.h;
        h1.g = i;
        if (i == 3 && h1.i) {
            h1.runOnUiThread(new A1(this));
        }
        if (i != 0) {
            this.a.h.d.release();
        }
        this.a.h.e.unlock();
    }
}
