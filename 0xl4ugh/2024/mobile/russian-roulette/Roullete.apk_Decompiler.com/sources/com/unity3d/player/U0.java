package com.unity3d.player;

import java.util.concurrent.Semaphore;

final class U0 implements Runnable {
    boolean a = false;
    boolean b = false;
    final /* synthetic */ Semaphore c;
    final /* synthetic */ UnityPlayerForActivityOrService d;
    final /* synthetic */ UnityPlayerForActivityOrService e;

    U0(UnityPlayerForActivityOrService unityPlayerForActivityOrService, Semaphore semaphore, UnityPlayerForActivityOrService unityPlayerForActivityOrService2) {
        this.e = unityPlayerForActivityOrService;
        this.c = semaphore;
        this.d = unityPlayerForActivityOrService2;
    }

    public final void run() {
        if (this.e.mSoftInput != null) {
            this.d.setOnHandleFocusListener(new S0(this));
            UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.e;
            unityPlayerForActivityOrService.mSoftInput.h = new T0(this);
            unityPlayerForActivityOrService.dismissSoftInput();
        }
    }
}
