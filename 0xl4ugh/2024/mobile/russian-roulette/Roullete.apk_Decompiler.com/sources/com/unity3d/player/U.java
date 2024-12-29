package com.unity3d.player;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.EditText;

final class U extends EditText {
    final /* synthetic */ Q a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    U(Context context, Q q) {
        super(context);
        this.a = q;
    }

    public final void onEditorAction(int i) {
        if (i == 6) {
            Q q = this.a;
            q.a(q.a(), false);
        }
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
            if (i != 66 || keyEvent.getAction() != 0 || (getInputType() & 131072) != 0) {
                return super.onKeyPreIme(i, keyEvent);
            }
            Q q = this.a;
            q.a(q.a(), false);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        this.a.b.reportSoftInputSelection(i, i2 - i);
    }
}
