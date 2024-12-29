package com.unity3d.player;

/* renamed from: com.unity3d.player.k0  reason: case insensitive filesystem */
final class C0030k0 extends Q0 {
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ C0034m0 d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0030k0(C0034m0 m0Var, UnityPlayer unityPlayer, int i, int i2) {
        super(unityPlayer);
        this.d = m0Var;
        this.b = i;
        this.c = i2;
    }

    public final void a() {
        if (this.b == 4096) {
            UnityAccessibilityDelegate.onNodeIncremented(this.c);
        } else {
            UnityAccessibilityDelegate.onNodeDecremented(this.c);
        }
        this.d.a.sendEventForVirtualViewId(this.c, 4);
    }
}
