package com.unity3d.player;

import android.content.DialogInterface;

final class Y implements DialogInterface.OnCancelListener {
    final /* synthetic */ C0001a0 a;

    Y(C0001a0 a0Var) {
        this.a = a0Var;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        D d = this.a.g;
        if (d != null) {
            ((o1) d).a();
        }
    }
}
