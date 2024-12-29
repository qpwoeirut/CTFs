package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

final class H1 {
    /* access modifiers changed from: private */
    public UnityPlayer a = null;
    /* access modifiers changed from: private */
    public Context b = null;
    /* access modifiers changed from: private */
    public G1 c;
    /* access modifiers changed from: private */
    public final Semaphore d = new Semaphore(0);
    /* access modifiers changed from: private */
    public final ReentrantLock e = new ReentrantLock();
    /* access modifiers changed from: private */
    public z1 f = null;
    /* access modifiers changed from: private */
    public int g = 2;
    private boolean h = false;
    /* access modifiers changed from: private */
    public boolean i = false;

    H1(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    public final void a() {
        this.e.lock();
        z1 z1Var = this.f;
        if (z1Var != null) {
            z1Var.updateVideoLayout();
        }
        this.e.unlock();
    }

    public final boolean a(Context context, String str, int i2, int i3, int i4, boolean z, long j, long j2, G1 g1) {
        this.e.lock();
        this.c = g1;
        this.b = context;
        this.d.drainPermits();
        this.g = 2;
        runOnUiThread(new C1(this, str, i2, i3, i4, z, j, j2));
        boolean z2 = false;
        try {
            this.e.unlock();
            this.d.acquire();
            this.e.lock();
            if (this.g != 2) {
                z2 = true;
            }
        } catch (InterruptedException unused) {
        }
        runOnUiThread(new D1(this));
        runOnUiThread((!z2 || this.g == 3) ? new F1(this) : new E1(this));
        this.e.unlock();
        return z2;
    }

    public final void b() {
        this.e.lock();
        z1 z1Var = this.f;
        if (z1Var != null) {
            if (this.g == 0) {
                z1Var.cancelOnPrepare();
            } else if (this.i) {
                boolean a2 = z1Var.a();
                this.h = a2;
                if (!a2) {
                    this.f.pause();
                }
            }
        }
        this.e.unlock();
    }

    public final void c() {
        this.e.lock();
        z1 z1Var = this.f;
        if (z1Var != null && this.i && !this.h) {
            z1Var.start();
        }
        this.e.unlock();
    }

    /* access modifiers changed from: protected */
    public void runOnUiThread(Runnable runnable) {
        Context context = this.b;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
        } else {
            C0055x.Log(5, "Not running from an Activity; Ignoring execution request...");
        }
    }
}
