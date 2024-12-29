package com.unity3d.player;

final class F0 implements Runnable {
    final /* synthetic */ UnityPlayer a;

    F0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    public final void run() {
        this.a.getFrameLayout().removeView(this.a.m_SplashScreen);
        this.a.m_SplashScreen = null;
    }
}
