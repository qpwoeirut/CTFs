package com.unity3d.player;

import android.widget.FrameLayout;

final class C1 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ boolean e;
    final /* synthetic */ long f;
    final /* synthetic */ long g;
    final /* synthetic */ H1 h;

    C1(H1 h1, String str, int i, int i2, int i3, boolean z, long j, long j2) {
        this.h = h1;
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = z;
        this.f = j;
        this.g = j2;
    }

    public final void run() {
        H1 h1 = this.h;
        if (h1.f != null) {
            C0055x.Log(5, "Video already playing");
            H1 h12 = this.h;
            h12.g = 2;
            h12.d.release();
            return;
        }
        H1 h13 = this.h;
        h1.f = new z1(h13.b, h13.a, this.a, this.b, this.c, this.d, this.e, this.f, this.g, new B1(this));
        H1 h14 = this.h;
        if (h14.f != null) {
            FrameLayout frameLayout = h14.a.getFrameLayout();
            frameLayout.bringToFront();
            frameLayout.addView(this.h.f);
        }
    }
}
