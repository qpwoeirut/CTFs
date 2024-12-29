package com.unity3d.player;

/* renamed from: com.unity3d.player.g0  reason: case insensitive filesystem */
final class C0019g0 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ UnityAccessibilityDelegate c;

    C0019g0(UnityAccessibilityDelegate unityAccessibilityDelegate, int i, int i2) {
        this.c = unityAccessibilityDelegate;
        this.a = i;
        this.b = i2;
    }

    public final void run() {
        this.c.sendEventForVirtualViewId(this.a, this.b);
    }
}
