package com.unity3d.player;

final class T implements Runnable {
    final /* synthetic */ V a;

    T(V v) {
        this.a = v;
    }

    public final void run() {
        this.a.c.requestFocus();
        this.a.g();
    }
}
