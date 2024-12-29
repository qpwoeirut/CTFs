package com.unity3d.player;

import android.view.SurfaceView;
import android.view.ViewTreeObserver;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UnityPlayerGameActivity$$ExternalSyntheticLambda1 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ UnityPlayerGameActivity f$0;
    public final /* synthetic */ SurfaceView f$1;

    public /* synthetic */ UnityPlayerGameActivity$$ExternalSyntheticLambda1(UnityPlayerGameActivity unityPlayerGameActivity, SurfaceView surfaceView) {
        this.f$0 = unityPlayerGameActivity;
        this.f$1 = surfaceView;
    }

    public final void onGlobalLayout() {
        this.f$0.lambda$applyInsetListener$0(this.f$1);
    }
}
