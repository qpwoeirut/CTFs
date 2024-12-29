package com.unity3d.player;

import android.os.Handler;

/* renamed from: com.unity3d.player.z0  reason: case insensitive filesystem */
final class C0060z0 implements Handler.Callback {
    final /* synthetic */ B0 a;

    C0060z0(B0 b0) {
        this.a = b0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00eb, code lost:
        if (r6.a.d != false) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0121, code lost:
        if (r6.a.d == false) goto L_0x0147;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r7) {
        /*
            r6 = this;
            int r0 = r7.what
            r1 = 0
            r2 = 2269(0x8dd, float:3.18E-42)
            if (r0 == r2) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.Object r7 = r7.obj
            com.unity3d.player.A0 r7 = (com.unity3d.player.A0) r7
            com.unity3d.player.A0 r0 = com.unity3d.player.A0.h
            r3 = 1
            if (r7 != r0) goto L_0x009d
            com.unity3d.player.B0 r7 = r6.a
            int r1 = r7.f
            int r1 = r1 - r3
            r7.f = r1
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            r7.executeMainThreadJobs()
            com.unity3d.player.B0 r7 = r6.a
            boolean r1 = r7.c
            if (r1 != 0) goto L_0x002b
            return r3
        L_0x002b:
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            boolean r7 = r7.getHaveAndroidWindowSupport()
            if (r7 == 0) goto L_0x003e
            com.unity3d.player.B0 r7 = r6.a
            boolean r7 = r7.d
            if (r7 != 0) goto L_0x003e
            return r3
        L_0x003e:
            com.unity3d.player.B0 r7 = r6.a
            int r1 = r7.i
            if (r1 < 0) goto L_0x007a
            if (r1 != 0) goto L_0x0070
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            boolean r7 = r7.getSplashEnabled()
            if (r7 == 0) goto L_0x005b
            com.unity3d.player.B0 r7 = r6.a
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            r7.disableStaticSplashScreen()
        L_0x005b:
            com.unity3d.player.B0 r7 = r6.a
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            boolean r7 = r7.shouldReportFullyDrawn()
            if (r7 == 0) goto L_0x0070
            com.unity3d.player.B0 r7 = r6.a
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            r7.reportFullyDrawn()
        L_0x0070:
            com.unity3d.player.B0 r7 = r6.a
            int r1 = r7.i
            int r1 = r1 - r3
            r7.i = r1
        L_0x007a:
            com.unity3d.player.B0 r7 = r6.a
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            boolean r7 = r7.isFinishing()
            if (r7 != 0) goto L_0x0147
            com.unity3d.player.B0 r7 = r6.a
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            boolean r7 = r7.nativeRender()
            if (r7 != 0) goto L_0x0147
            com.unity3d.player.B0 r7 = r6.a
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            r7.finish()
            goto L_0x0147
        L_0x009d:
            com.unity3d.player.A0 r4 = com.unity3d.player.A0.c
            if (r7 != r4) goto L_0x00aa
            android.os.Looper r7 = android.os.Looper.myLooper()
            r7.quit()
            goto L_0x0147
        L_0x00aa:
            com.unity3d.player.A0 r4 = com.unity3d.player.A0.b
            if (r7 != r4) goto L_0x00b5
            com.unity3d.player.B0 r7 = r6.a
            r7.c = r3
            goto L_0x0147
        L_0x00b5:
            com.unity3d.player.A0 r4 = com.unity3d.player.A0.a
            if (r7 != r4) goto L_0x00c0
            com.unity3d.player.B0 r7 = r6.a
            r7.c = r1
            goto L_0x0147
        L_0x00c0:
            com.unity3d.player.A0 r4 = com.unity3d.player.A0.d
            if (r7 != r4) goto L_0x00cb
            com.unity3d.player.B0 r7 = r6.a
            r7.d = r1
            goto L_0x0147
        L_0x00cb:
            com.unity3d.player.A0 r4 = com.unity3d.player.A0.e
            r5 = 3
            if (r7 != r4) goto L_0x00ee
            com.unity3d.player.B0 r7 = r6.a
            r7.d = r3
            int r1 = r7.e
            if (r1 != r5) goto L_0x0147
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            boolean r7 = r7.getHaveAndroidWindowSupport()
            if (r7 == 0) goto L_0x0123
            com.unity3d.player.B0 r7 = r6.a
            boolean r7 = r7.d
            if (r7 == 0) goto L_0x0147
            goto L_0x0123
        L_0x00ee:
            com.unity3d.player.A0 r4 = com.unity3d.player.A0.f
            if (r7 != r4) goto L_0x0108
            com.unity3d.player.B0 r7 = r6.a
            int r4 = r7.e
            if (r4 != r3) goto L_0x0101
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            r7.nativeFocusChanged(r1)
        L_0x0101:
            com.unity3d.player.B0 r7 = r6.a
            r1 = 2
            r7.e = r1
            goto L_0x0147
        L_0x0108:
            com.unity3d.player.A0 r1 = com.unity3d.player.A0.g
            if (r7 != r1) goto L_0x0132
            com.unity3d.player.B0 r7 = r6.a
            r7.e = r5
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            boolean r7 = r7.getHaveAndroidWindowSupport()
            if (r7 == 0) goto L_0x0123
            com.unity3d.player.B0 r7 = r6.a
            boolean r7 = r7.d
            if (r7 == 0) goto L_0x0147
        L_0x0123:
            com.unity3d.player.B0 r7 = r6.a
            com.unity3d.player.UnityPlayerForActivityOrService r7 = r7.a
            r7.nativeFocusChanged(r3)
            com.unity3d.player.B0 r7 = r6.a
            r7.e = r3
            goto L_0x0147
        L_0x0132:
            com.unity3d.player.A0 r1 = com.unity3d.player.A0.i
            if (r7 != r1) goto L_0x0147
            com.unity3d.player.B0 r7 = r6.a
            com.unity3d.player.UnityPlayerForActivityOrService r1 = r7.a
            int r4 = r7.g
            int r7 = r7.h
            r1.nativeOrientationChanged(r4, r7)
        L_0x0147:
            com.unity3d.player.B0 r7 = r6.a
            boolean r1 = r7.c
            if (r1 == 0) goto L_0x016a
            int r1 = r7.f
            if (r1 > 0) goto L_0x016a
            android.os.Handler r7 = r7.b
            android.os.Message r7 = android.os.Message.obtain(r7, r2, r0)
            r7.sendToTarget()
            com.unity3d.player.B0 r7 = r6.a
            int r0 = r7.f
            int r0 = r0 + r3
            r7.f = r0
        L_0x016a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0060z0.handleMessage(android.os.Message):boolean");
    }
}
