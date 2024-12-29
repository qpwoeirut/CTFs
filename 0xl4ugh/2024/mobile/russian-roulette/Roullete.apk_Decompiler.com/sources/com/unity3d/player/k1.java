package com.unity3d.player;

final class k1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    k1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    public final void run() {
        UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.a;
        if (unityPlayerForActivityOrService.mMainDisplayOverride) {
            unityPlayerForActivityOrService.getFrameLayout().removeView(this.a.getView());
        } else if (unityPlayerForActivityOrService.getView().getParent() == null) {
            this.a.getFrameLayout().addView(this.a.getView());
        } else {
            C0055x.Log(5, "Couldn't add view, because it's already assigned to another parent");
        }
    }
}
