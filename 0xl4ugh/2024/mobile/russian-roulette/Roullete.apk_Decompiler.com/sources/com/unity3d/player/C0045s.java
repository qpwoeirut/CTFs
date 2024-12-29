package com.unity3d.player;

import android.graphics.SurfaceTexture;

/* renamed from: com.unity3d.player.s  reason: case insensitive filesystem */
final class C0045s implements SurfaceTexture.OnFrameAvailableListener {
    final /* synthetic */ C0047t a;

    C0045s(C0047t tVar) {
        this.a = tVar;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        ((Camera2Wrapper) this.a.a).a(surfaceTexture);
    }
}
