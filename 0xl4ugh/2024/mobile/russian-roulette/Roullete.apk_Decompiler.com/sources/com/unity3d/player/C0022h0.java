package com.unity3d.player;

/* renamed from: com.unity3d.player.h0  reason: case insensitive filesystem */
final class C0022h0 extends Q0 {
    final /* synthetic */ int b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0022h0(UnityPlayer unityPlayer, int i) {
        super(unityPlayer);
        this.b = i;
    }

    public final void a() {
        UnityAccessibilityDelegate.onNodeFocusChanged(this.b, true);
        UnityAccessibilityDelegate.sendElementFocusedNotification(this.b);
    }
}