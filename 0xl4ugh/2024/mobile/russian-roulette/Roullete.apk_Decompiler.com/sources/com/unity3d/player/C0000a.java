package com.unity3d.player;

import android.window.OnBackInvokedCallback;
import com.unity3d.player.a.e;

/* renamed from: com.unity3d.player.a  reason: case insensitive filesystem */
final class C0000a implements OnBackInvokedCallback {
    final /* synthetic */ e a;

    C0000a(e eVar) {
        this.a = eVar;
    }

    public final void onBackInvoked() {
        Runnable runnable = ((C0059z) this.a).a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
