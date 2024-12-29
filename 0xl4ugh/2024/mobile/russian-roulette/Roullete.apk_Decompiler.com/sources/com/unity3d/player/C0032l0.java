package com.unity3d.player;

/* renamed from: com.unity3d.player.l0  reason: case insensitive filesystem */
final class C0032l0 extends Q0 {
    final /* synthetic */ int b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0032l0(UnityPlayer unityPlayer, int i) {
        super(unityPlayer);
        this.b = i;
    }

    public final void a() {
        boolean unused = UnityAccessibilityDelegate.onNodeDismissed(this.b);
    }
}
