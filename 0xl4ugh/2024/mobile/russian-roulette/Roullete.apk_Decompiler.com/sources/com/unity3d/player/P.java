package com.unity3d.player;

import android.view.KeyEvent;
import android.widget.TextView;

final class P implements TextView.OnEditorActionListener {
    final /* synthetic */ Q a;

    P(Q q) {
        this.a = q;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 6) {
            Q q = this.a;
            q.a(q.a(), false);
        }
        return false;
    }
}
