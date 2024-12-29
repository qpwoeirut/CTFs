package com.unity3d.player;

import android.graphics.Rect;

/* renamed from: com.unity3d.player.b1  reason: case insensitive filesystem */
final class C0005b1 extends Q0 {
    final /* synthetic */ Rect b;
    final /* synthetic */ UnityPlayerForActivityOrService c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0005b1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, Rect rect) {
        super(unityPlayerForActivityOrService);
        this.c = unityPlayerForActivityOrService;
        this.b = rect;
    }

    public final void a() {
        UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.c;
        Rect rect = this.b;
        unityPlayerForActivityOrService.nativeSetInputArea(rect.left, rect.top, rect.right, rect.bottom);
    }
}
