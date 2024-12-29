package com.unity3d.player;

/* renamed from: com.unity3d.player.n0  reason: case insensitive filesystem */
final class C0036n0 extends Q0 {
    final /* synthetic */ boolean b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0036n0(UnityPlayer unityPlayer, boolean z) {
        super(unityPlayer);
        this.b = z;
    }

    public final void a() {
        UnityAccessibilityDelegate.sendScreenReaderStatusChangedNotification(this.b);
    }
}
