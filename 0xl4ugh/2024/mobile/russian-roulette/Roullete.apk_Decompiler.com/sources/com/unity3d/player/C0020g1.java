package com.unity3d.player;

import java.util.concurrent.Semaphore;

/* renamed from: com.unity3d.player.g1  reason: case insensitive filesystem */
final class C0020g1 implements Runnable {
    final /* synthetic */ Semaphore a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    C0020g1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, Semaphore semaphore) {
        this.b = unityPlayerForActivityOrService;
        this.a = semaphore;
    }

    public final void run() {
        if (this.b.nativePause()) {
            UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.b;
            unityPlayerForActivityOrService.mQuitting = true;
            unityPlayerForActivityOrService.shutdown();
            this.b.queueDestroy();
        }
        this.a.release();
    }
}
