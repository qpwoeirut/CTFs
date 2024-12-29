package com.unity3d.player;

import android.content.Context;
import android.view.OrientationEventListener;

final class K0 extends OrientationEventListener {
    final /* synthetic */ UnityPlayer a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    K0(UnityPlayer unityPlayer, Context context, int i) {
        super(context, i);
        this.a = unityPlayer;
    }

    public final void onOrientationChanged(int i) {
        UnityPlayer unityPlayer = this.a;
        unityPlayer.onOrientationChanged(unityPlayer.mNaturalOrientation, i);
    }
}
