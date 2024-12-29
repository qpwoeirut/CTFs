package com.unity3d.player;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class B0 extends Thread {
    /* access modifiers changed from: private */
    public UnityPlayerForActivityOrService a;
    /* access modifiers changed from: private */
    public Handler b;
    /* access modifiers changed from: private */
    public boolean c = false;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public int e = 2;
    /* access modifiers changed from: private */
    public int f = 0;
    /* access modifiers changed from: private */
    public int g;
    /* access modifiers changed from: private */
    public int h;
    /* access modifiers changed from: private */
    public int i = 5;

    B0(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    private void a(A0 a0) {
        Handler handler = this.b;
        if (handler != null) {
            Message.obtain(handler, 2269, a0).sendToTarget();
        }
    }

    public final void a() {
        a(A0.g);
    }

    public final void a(int i2, int i3) {
        this.g = i2;
        this.h = i3;
        a(A0.i);
    }

    public final void a(Runnable runnable) {
        if (this.b != null) {
            a(A0.a);
            Message.obtain(this.b, runnable).sendToTarget();
        }
    }

    public final void b() {
        a(A0.f);
    }

    public final void b(Runnable runnable) {
        Handler handler = this.b;
        if (handler != null) {
            Message.obtain(handler, runnable).sendToTarget();
            a(A0.e);
        }
    }

    public final void c() {
        a(A0.c);
    }

    public final void c(Runnable runnable) {
        Handler handler = this.b;
        if (handler != null) {
            Message.obtain(handler, runnable).sendToTarget();
        }
    }

    public final void d() {
        a(A0.b);
    }

    public final void d(Runnable runnable) {
        if (this.b != null) {
            a(A0.d);
            Message.obtain(this.b, runnable).sendToTarget();
        }
    }

    public final void run() {
        setName("UnityMain");
        Looper.prepare();
        this.b = new Handler(Looper.myLooper(), new C0060z0(this));
        Looper.loop();
    }
}
