package com.unity3d.player;

import android.text.InputFilter;
import android.widget.EditText;

final class W0 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    W0(UnityPlayerForActivityOrService unityPlayerForActivityOrService, int i) {
        this.b = unityPlayerForActivityOrService;
        this.a = i;
    }

    public final void run() {
        Q r0 = this.b.mSoftInput;
        if (r0 != null) {
            int i = this.a;
            EditText editText = r0.c;
            if (editText == null) {
                return;
            }
            if (i > 0) {
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
                return;
            }
            editText.setFilters(new InputFilter[0]);
        }
    }
}
