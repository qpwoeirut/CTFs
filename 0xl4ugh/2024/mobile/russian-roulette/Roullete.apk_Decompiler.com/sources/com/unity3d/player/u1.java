package com.unity3d.player;

import android.widget.FrameLayout;

final class u1 implements Runnable {
    final /* synthetic */ UnityPlayerForGameActivity a;

    u1(UnityPlayerForGameActivity unityPlayerForGameActivity) {
        this.a = unityPlayerForGameActivity;
    }

    public final void run() {
        UnityPlayerForGameActivity unityPlayerForGameActivity = this.a;
        I r1 = unityPlayerForGameActivity.m_PersistentUnitySurface;
        FrameLayout frameLayout = unityPlayerForGameActivity.getFrameLayout();
        H h = r1.b;
        if (!(h == null || h.getParent() == null)) {
            frameLayout.removeView(r1.b);
        }
        this.a.m_PersistentUnitySurface.b = null;
    }
}
