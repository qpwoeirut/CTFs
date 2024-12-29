package com.unity3d.player;

final class w1 implements Runnable {
    final /* synthetic */ z1 a;

    w1(z1 z1Var) {
        this.a = z1Var;
    }

    public final void run() {
        this.a.destroyPlayer();
        this.a.a(3);
    }
}
