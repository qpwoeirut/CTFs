package com.unity3d.player;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.widget.FrameLayout;

/* renamed from: com.unity3d.player.x0  reason: case insensitive filesystem */
final class C0056x0 implements SurfaceHolder.Callback {
    final /* synthetic */ C0058y0 a;

    C0056x0(C0058y0 y0Var) {
        this.a = y0Var;
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.a.b.updateGLDisplay(0, surfaceHolder.getSurface());
        this.a.b.sendSurfaceChangedEvent();
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.a.b.updateGLDisplay(0, surfaceHolder.getSurface());
        C0058y0 y0Var = this.a;
        I r0 = y0Var.c;
        FrameLayout frameLayout = y0Var.b.getFrameLayout();
        H h = r0.b;
        if (h != null && h.getParent() == null) {
            frameLayout.addView(r0.b);
            frameLayout.bringChildToFront(r0.b);
        }
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        C0058y0 y0Var = this.a;
        I r0 = y0Var.c;
        C0006c r4 = y0Var.a;
        r0.getClass();
        if (PlatformSupport.NOUGAT_SUPPORT && r0.a != null) {
            if (r0.b == null) {
                r0.b = new H(r0, r0.a);
            }
            r0.b.a(r4);
        }
        this.a.b.updateGLDisplay(0, (Surface) null);
    }
}
