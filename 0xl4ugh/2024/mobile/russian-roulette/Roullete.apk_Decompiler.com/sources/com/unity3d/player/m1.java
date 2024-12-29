package com.unity3d.player;

final class m1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    m1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    public final void run() {
        C0058y0 view = this.a.getView();
        if (view != null) {
            view.c();
        }
    }
}
