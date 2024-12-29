package com.unity3d.player;

import android.content.res.Configuration;

/* renamed from: com.unity3d.player.f0  reason: case insensitive filesystem */
final class C0016f0 extends Q0 {
    final /* synthetic */ Configuration b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0016f0(UnityPlayer unityPlayer, Configuration configuration) {
        super(unityPlayer);
        this.b = configuration;
    }

    public final void a() {
        UnityAccessibilityDelegate.sendFontScaleChangedNotification(this.b.fontScale);
    }
}
