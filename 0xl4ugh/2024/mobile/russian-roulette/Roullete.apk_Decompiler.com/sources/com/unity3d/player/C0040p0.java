package com.unity3d.player;

/* renamed from: com.unity3d.player.p0  reason: case insensitive filesystem */
final class C0040p0 extends Q0 {
    final /* synthetic */ boolean b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0040p0(UnityPlayer unityPlayer, boolean z) {
        super(unityPlayer);
        this.b = z;
    }

    public final void a() {
        UnityAccessibilityDelegate.sendClosedCaptioningChangedNotification(this.b);
    }
}
