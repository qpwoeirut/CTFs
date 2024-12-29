package com.unity3d.player;

final class l1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    l1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    public final void run() {
        this.a.nativeResume();
    }
}
