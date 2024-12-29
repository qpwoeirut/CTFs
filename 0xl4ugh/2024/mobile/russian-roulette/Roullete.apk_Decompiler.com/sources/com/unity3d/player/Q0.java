package com.unity3d.player;

public abstract class Q0 implements Runnable {
    final /* synthetic */ UnityPlayer a;

    protected Q0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    public abstract void a();

    public final void run() {
        if (!this.a.isFinishing()) {
            a();
        }
    }
}
