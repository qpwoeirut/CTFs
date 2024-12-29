package com.unity3d.player;

/* renamed from: com.unity3d.player.i1  reason: case insensitive filesystem */
final class C0026i1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    C0026i1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    public final void run() {
        this.a.nativeSendSurfaceChangedEvent();
    }
}
