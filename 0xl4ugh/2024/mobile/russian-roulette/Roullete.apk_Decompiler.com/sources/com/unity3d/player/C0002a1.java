package com.unity3d.player;

/* renamed from: com.unity3d.player.a1  reason: case insensitive filesystem */
final class C0002a1 extends Q0 {
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ UnityPlayerForActivityOrService d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0002a1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, int i, int i2) {
        super(unityPlayerForActivityOrService);
        this.d = unityPlayerForActivityOrService;
        this.b = i;
        this.c = i2;
    }

    public final void a() {
        this.d.nativeSetInputSelection(this.b, this.c);
    }
}
