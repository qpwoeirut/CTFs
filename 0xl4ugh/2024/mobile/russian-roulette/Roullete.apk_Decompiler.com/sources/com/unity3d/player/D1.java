package com.unity3d.player;

final class D1 implements Runnable {
    final /* synthetic */ H1 a;

    D1(H1 h1) {
        this.a = h1;
    }

    public final void run() {
        this.a.a.onPause();
    }
}
