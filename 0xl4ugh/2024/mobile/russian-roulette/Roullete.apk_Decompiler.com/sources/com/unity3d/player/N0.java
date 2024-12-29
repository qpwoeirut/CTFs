package com.unity3d.player;

final class N0 extends Q0 {
    final /* synthetic */ boolean b;
    final /* synthetic */ O0 c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    N0(O0 o0, boolean z) {
        super(o0.b);
        this.c = o0;
        this.b = z;
    }

    public final void a() {
        UnityPlayer.permissionResponseToNative(this.c.a, this.b);
    }
}
