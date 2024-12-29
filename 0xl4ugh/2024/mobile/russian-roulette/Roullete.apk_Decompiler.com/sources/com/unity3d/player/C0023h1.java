package com.unity3d.player;

import android.content.res.Configuration;

/* renamed from: com.unity3d.player.h1  reason: case insensitive filesystem */
final class C0023h1 implements Runnable {
    final /* synthetic */ Configuration a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    C0023h1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, Configuration configuration) {
        this.b = unityPlayerForActivityOrService;
        this.a = configuration;
    }

    public final void run() {
        this.b.nativeConfigurationChanged(this.a);
    }
}
