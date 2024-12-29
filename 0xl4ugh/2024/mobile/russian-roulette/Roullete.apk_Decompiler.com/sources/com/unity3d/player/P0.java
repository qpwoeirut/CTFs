package com.unity3d.player;

import android.telephony.PhoneStateListener;

final class P0 extends PhoneStateListener {
    final /* synthetic */ UnityPlayer a;

    private P0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    public final void onCallStateChanged(int i, String str) {
        UnityPlayer unityPlayer = this.a;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        unityPlayer.nativeMuteMasterAudio(z);
    }
}
