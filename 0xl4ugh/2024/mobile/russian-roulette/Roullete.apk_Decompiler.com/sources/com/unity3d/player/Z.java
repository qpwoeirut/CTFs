package com.unity3d.player;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.EditText;

final class Z extends EditText {
    final /* synthetic */ Q a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Z(Context context, Q q) {
        super(context);
        this.a = q;
    }

    public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (keyEvent.getAction() == 1) {
                this.a.d();
            }
            return true;
        } else if (i == 84) {
            return true;
        } else {
            if (i == 66 && keyEvent.getAction() == 0 && (getInputType() & 131072) == 0) {
                Q q = this.a;
                q.a(q.a(), false);
                return true;
            } else if (i != 111 || keyEvent.getAction() != 0) {
                return super.onKeyPreIme(i, keyEvent);
            } else {
                Q q2 = this.a;
                q2.a(q2.a(), true);
                return true;
            }
        }
    }

    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            requestFocus();
            this.a.g();
        }
    }
}
