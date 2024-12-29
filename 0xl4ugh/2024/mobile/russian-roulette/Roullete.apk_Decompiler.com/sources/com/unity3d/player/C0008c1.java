package com.unity3d.player;

/* renamed from: com.unity3d.player.c1  reason: case insensitive filesystem */
final class C0008c1 extends Q0 {
    final /* synthetic */ boolean b;
    final /* synthetic */ UnityPlayerForActivityOrService c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0008c1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, boolean z) {
        super(unityPlayerForActivityOrService);
        this.c = unityPlayerForActivityOrService;
        this.b = z;
    }

    public final void a() {
        this.c.nativeSetKeyboardIsVisible(this.b);
    }
}
