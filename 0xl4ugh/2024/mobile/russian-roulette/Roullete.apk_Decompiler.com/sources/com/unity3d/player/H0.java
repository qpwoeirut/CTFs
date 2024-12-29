package com.unity3d.player;

final class H0 implements Runnable {
    final /* synthetic */ String a;

    H0(String str) {
        this.a = str;
    }

    public final void run() {
        UnityPlayer.nativeSetLaunchURL(this.a);
    }
}
