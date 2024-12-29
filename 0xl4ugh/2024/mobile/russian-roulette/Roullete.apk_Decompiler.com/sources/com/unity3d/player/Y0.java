package com.unity3d.player;

import android.widget.EditText;

final class Y0 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ UnityPlayerForActivityOrService c;

    Y0(UnityPlayerForActivityOrService unityPlayerForActivityOrService, int i, int i2) {
        this.c = unityPlayerForActivityOrService;
        this.a = i;
        this.b = i2;
    }

    public final void run() {
        int i;
        Q r0 = this.c.mSoftInput;
        if (r0 != null) {
            int i2 = this.a;
            int i3 = this.b;
            EditText editText = r0.c;
            if (editText != null && editText.getText().length() >= (i = i3 + i2)) {
                r0.c.setSelection(i2, i);
            }
        }
    }
}
