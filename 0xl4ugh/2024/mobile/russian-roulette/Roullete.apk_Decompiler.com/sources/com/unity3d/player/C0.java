package com.unity3d.player;

final class C0 implements Runnable {
    final /* synthetic */ UnityPlayer a;

    C0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    public final void run() {
        this.a.setupUnityToBePaused();
        this.a.windowFocusChanged(false);
        this.a.m_UnityPlayerLifecycleEvents.onUnityPlayerUnloaded();
    }
}
