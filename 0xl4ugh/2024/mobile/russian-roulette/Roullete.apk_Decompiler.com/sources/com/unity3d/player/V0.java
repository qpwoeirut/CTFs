package com.unity3d.player;

import android.widget.EditText;

final class V0 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    V0(UnityPlayerForActivityOrService unityPlayerForActivityOrService, String str) {
        this.b = unityPlayerForActivityOrService;
        this.a = str;
    }

    public final void run() {
        String str;
        EditText editText;
        Q r0 = this.b.mSoftInput;
        if (r0 != null && (str = this.a) != null && (editText = r0.c) != null) {
            editText.setText(str);
            r0.c.setSelection(str.length());
        }
    }
}
