package com.unity3d.player;

final class O implements Runnable {
    final /* synthetic */ Q a;

    O(Q q) {
        this.a = q;
    }

    public final void run() {
        Q q = this.a;
        q.a(q.a(), true);
    }
}
