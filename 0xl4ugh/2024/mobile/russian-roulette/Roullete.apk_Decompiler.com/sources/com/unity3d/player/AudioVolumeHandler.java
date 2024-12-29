package com.unity3d.player;

import android.content.Context;

public class AudioVolumeHandler implements C0033m {
    private C0035n a;

    AudioVolumeHandler(Context context) {
        C0035n nVar = new C0035n(context);
        this.a = nVar;
        nVar.a(this);
    }

    public final void a() {
        this.a.a();
        this.a = null;
    }

    public final native void onAudioVolumeChanged(int i);
}
