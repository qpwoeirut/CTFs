package com.unity3d.player;

import android.view.View;
import android.view.WindowInsets;

/* renamed from: com.unity3d.player.e1  reason: case insensitive filesystem */
final class C0014e1 implements View.OnApplyWindowInsetsListener {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    C0014e1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.a.invokeOnMainThread((Runnable) new R0(this, windowInsets));
        return windowInsets;
    }
}
