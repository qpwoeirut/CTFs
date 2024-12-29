package com.unity3d.player;

import android.content.Context;
import android.widget.EditText;

/* renamed from: com.unity3d.player.a0  reason: case insensitive filesystem */
final class C0001a0 extends Q {
    S i;

    public C0001a0(Context context, UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        super(context, unityPlayerForActivityOrService);
    }

    public final void a(String str, int i2, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i3, boolean z5, boolean z6) {
        S s = new S(this.a, this.b);
        this.i = s;
        s.a(this, z5, z6);
        this.i.setOnDismissListener(new W(this));
        super.a(str, i2, z, z2, z3, z4, str2, i3, z5, z6);
        this.b.getFrameLayout().getViewTreeObserver().addOnGlobalLayoutListener(new X(this));
        this.c.requestFocus();
        this.i.setOnCancelListener(new Y(this));
    }

    public final void a(boolean z) {
        this.e = z;
        this.i.a(z);
    }

    public final void b() {
        this.i.dismiss();
    }

    /* access modifiers changed from: protected */
    public EditText createEditText(Q q) {
        return new Z(this.a, q);
    }

    public final void f() {
        this.i.show();
    }

    /* access modifiers changed from: protected */
    public void reportSoftInputArea() {
        if (this.i.isShowing()) {
            this.b.reportSoftInputArea(this.i.a());
        }
    }
}
