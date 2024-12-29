package com.unity3d.player;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

final class q1 implements SurfaceHolder.Callback {
    final /* synthetic */ UnityPlayerForGameActivity a;

    q1(UnityPlayerForGameActivity unityPlayerForGameActivity) {
        this.a = unityPlayerForGameActivity;
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        UnityPlayerForGameActivity unityPlayerForGameActivity = this.a;
        I r0 = unityPlayerForGameActivity.m_PersistentUnitySurface;
        FrameLayout frameLayout = unityPlayerForGameActivity.getFrameLayout();
        H h = r0.b;
        if (h != null && h.getParent() == null) {
            frameLayout.addView(r0.b);
            frameLayout.bringChildToFront(r0.b);
        }
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        UnityPlayerForGameActivity unityPlayerForGameActivity = this.a;
        I r0 = unityPlayerForGameActivity.m_PersistentUnitySurface;
        SurfaceView surfaceView = unityPlayerForGameActivity.m_SurfaceView;
        r0.getClass();
        if (PlatformSupport.NOUGAT_SUPPORT && r0.a != null) {
            if (r0.b == null) {
                r0.b = new H(r0, r0.a);
            }
            r0.b.a(surfaceView);
        }
    }
}
