package com.unity3d.player;

import android.content.Context;
import com.unity3d.player.a.a;
import java.util.concurrent.Semaphore;

final class p1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;
    final /* synthetic */ String b;
    final /* synthetic */ int c;
    final /* synthetic */ boolean d;
    final /* synthetic */ boolean e;
    final /* synthetic */ boolean f;
    final /* synthetic */ boolean g;
    final /* synthetic */ String h;
    final /* synthetic */ int i;
    final /* synthetic */ boolean j;
    final /* synthetic */ boolean k;
    final /* synthetic */ Semaphore l;
    final /* synthetic */ UnityPlayerForActivityOrService m;

    p1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, UnityPlayerForActivityOrService unityPlayerForActivityOrService2, String str, int i2, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i3, boolean z5, boolean z6, Semaphore semaphore) {
        this.m = unityPlayerForActivityOrService;
        this.a = unityPlayerForActivityOrService2;
        this.b = str;
        this.c = i2;
        this.d = z;
        this.e = z2;
        this.f = z3;
        this.g = z4;
        this.h = str2;
        this.i = i3;
        this.j = z5;
        this.k = z6;
        this.l = semaphore;
    }

    public final void run() {
        try {
            UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.m;
            if (unityPlayerForActivityOrService.mSoftInput != null) {
                unityPlayerForActivityOrService.dismissSoftInput();
            }
            UnityPlayerForActivityOrService unityPlayerForActivityOrService2 = this.m;
            int a2 = SoftInputProvider.a();
            Context context = this.m.mContext;
            UnityPlayerForActivityOrService unityPlayerForActivityOrService3 = this.a;
            unityPlayerForActivityOrService2.mSoftInput = a.a(a2) != 2 ? new C0001a0(context, unityPlayerForActivityOrService3) : new V(context, unityPlayerForActivityOrService3);
            this.m.mSoftInput.a(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k);
            Q r1 = this.m.mSoftInput;
            r1.g = new o1(this);
            r1.f();
            this.m.nativeReportKeyboardConfigChanged();
        } catch (Exception e2) {
            C0055x.Log(6, "Exception when opening Softinput " + e2);
        } catch (Throwable th) {
            this.l.release();
            throw th;
        }
        this.l.release();
    }
}
