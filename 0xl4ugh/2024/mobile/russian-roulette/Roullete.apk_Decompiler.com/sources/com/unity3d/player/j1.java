package com.unity3d.player;

import android.view.Surface;
import java.util.concurrent.Semaphore;

final class j1 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Surface b;
    final /* synthetic */ Semaphore c;
    final /* synthetic */ UnityPlayerForActivityOrService d;

    j1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, int i, Surface surface, Semaphore semaphore) {
        this.d = unityPlayerForActivityOrService;
        this.a = i;
        this.b = surface;
        this.c = semaphore;
    }

    public final void run() {
        this.d.nativeRecreateGfxState(this.a, this.b);
        this.c.release();
    }
}
