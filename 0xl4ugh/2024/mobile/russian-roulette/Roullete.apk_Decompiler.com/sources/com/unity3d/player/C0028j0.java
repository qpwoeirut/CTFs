package com.unity3d.player;

/* renamed from: com.unity3d.player.j0  reason: case insensitive filesystem */
final class C0028j0 extends Q0 {
    final /* synthetic */ int b;
    final /* synthetic */ C0034m0 c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0028j0(C0034m0 m0Var, UnityPlayer unityPlayer, int i) {
        super(unityPlayer);
        this.c = m0Var;
        this.b = i;
    }

    public final void a() {
        if (UnityAccessibilityDelegate.onNodeSelected(this.b)) {
            this.c.a.sendEventForVirtualViewId(this.b, 1);
        }
    }
}
