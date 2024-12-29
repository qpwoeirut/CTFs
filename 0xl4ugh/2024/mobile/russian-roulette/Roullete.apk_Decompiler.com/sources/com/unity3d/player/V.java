package com.unity3d.player;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.FrameLayout;

final class V extends Q {
    private boolean i = false;
    private Handler j;
    private Runnable k;

    public V(Context context, UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        super(context, unityPlayerForActivityOrService);
    }

    public final void a(boolean z) {
        EditText editText;
        int i2;
        this.e = z;
        if (z) {
            editText = this.c;
            i2 = 4;
        } else {
            editText = this.c;
            i2 = 0;
        }
        editText.setVisibility(i2);
        this.c.invalidate();
        this.c.requestLayout();
    }

    public final void b() {
        Runnable runnable;
        Handler handler = this.j;
        if (!(handler == null || (runnable = this.k) == null)) {
            handler.removeCallbacks(runnable);
        }
        this.b.getFrameLayout().removeView(this.c);
        this.i = false;
        invokeOnClose();
    }

    public final boolean c() {
        return false;
    }

    /* access modifiers changed from: protected */
    public EditText createEditText(Q q) {
        return new U(this.a, q);
    }

    public final void f() {
        if (!this.i) {
            FrameLayout frameLayout = this.b.getFrameLayout();
            frameLayout.addView(this.c);
            frameLayout.bringChildToFront(this.c);
            this.c.setVisibility(0);
            this.c.requestFocus();
            this.k = new T(this);
            Handler handler = new Handler(Looper.getMainLooper());
            this.j = handler;
            handler.postDelayed(this.k, 400);
            this.i = true;
        }
    }
}
