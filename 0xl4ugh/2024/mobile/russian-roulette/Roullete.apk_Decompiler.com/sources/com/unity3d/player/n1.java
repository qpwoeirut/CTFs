package com.unity3d.player;

final class n1 implements Runnable {
    final /* synthetic */ float a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    n1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, float f) {
        this.b = unityPlayerForActivityOrService;
        this.a = f;
    }

    public final void run() {
        C0058y0 view = this.b.getView();
        if (view != null) {
            view.a(this.a);
        }
    }
}
