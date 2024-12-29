package com.unity3d.player;

import android.app.Activity;
import android.app.Dialog;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

final class B extends A {
    private OnBackInvokedCallback d = null;
    private OnBackInvokedDispatcher e;
    private int f;

    private B(OnBackInvokedDispatcher onBackInvokedDispatcher, int i, Runnable runnable) {
        super(runnable);
        this.f = i;
        this.e = onBackInvokedDispatcher;
    }

    public static A a(Object obj, int i, Runnable runnable) {
        A a = (!PlatformSupport.TIRAMISU_SUPPORT || (!(obj instanceof Activity) && !(obj instanceof Dialog))) ? new A(runnable) : new B(C0003b.a(obj), i, runnable);
        a.registerOnBackPressedCallback();
        return a;
    }

    /* access modifiers changed from: protected */
    public void registerOnBackPressedCallback() {
        if (this.a == null) {
            super.registerOnBackPressedCallback();
            if (PlatformSupport.TIRAMISU_SUPPORT) {
                C0000a aVar = new C0000a(this.a);
                this.d = aVar;
                C0003b.a(this.e, this.f, aVar);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void unregisterOnBackPressedCallback() {
        if (this.a != null) {
            if (PlatformSupport.TIRAMISU_SUPPORT) {
                C0003b.a(this.e, this.d);
                this.d = null;
            }
            super.unregisterOnBackPressedCallback();
        }
    }
}
