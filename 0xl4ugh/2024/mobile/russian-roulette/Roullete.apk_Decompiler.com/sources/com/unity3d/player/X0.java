package com.unity3d.player;

final class X0 implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    X0(UnityPlayerForActivityOrService unityPlayerForActivityOrService, boolean z) {
        this.b = unityPlayerForActivityOrService;
        this.a = z;
    }

    public final void run() {
        Q r0 = this.b.mSoftInput;
        if (r0 != null) {
            r0.a(this.a);
        }
    }
}
