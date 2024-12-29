package com.unity3d.player;

import android.content.DialogInterface;

final class D0 implements DialogInterface.OnClickListener {
    final /* synthetic */ UnityPlayer a;

    D0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.finish();
    }
}
