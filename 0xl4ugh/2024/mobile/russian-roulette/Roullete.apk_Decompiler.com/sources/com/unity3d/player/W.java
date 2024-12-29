package com.unity3d.player;

import android.content.DialogInterface;

final class W implements DialogInterface.OnDismissListener {
    final /* synthetic */ C0001a0 a;

    W(C0001a0 a0Var) {
        this.a = a0Var;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.a.invokeOnClose();
    }
}
