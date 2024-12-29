package com.unity3d.player;

import java.util.concurrent.Semaphore;

/* renamed from: com.unity3d.player.f1  reason: case insensitive filesystem */
final class C0017f1 implements Runnable {
    final /* synthetic */ Semaphore a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    C0017f1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, Semaphore semaphore) {
        this.b = unityPlayerForActivityOrService;
        this.a = semaphore;
    }

    public final void run() {
        this.b.shutdown();
        this.a.release();
    }
}
