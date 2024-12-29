package com.unity3d.player;

/* renamed from: com.unity3d.player.d1  reason: case insensitive filesystem */
final class C0011d1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    C0011d1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    public final void run() {
        this.a.destroy();
    }
}
