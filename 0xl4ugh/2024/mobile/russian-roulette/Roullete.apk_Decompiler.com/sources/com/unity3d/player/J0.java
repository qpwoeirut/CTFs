package com.unity3d.player;

final class J0 implements Runnable {
    final /* synthetic */ UnityPlayer a;

    J0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r2.a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r2 = this;
            com.unity3d.player.UnityPlayer r0 = r2.a
            boolean r0 = r0.nativeIsAutorotationOn()
            if (r0 == 0) goto L_0x0015
            com.unity3d.player.UnityPlayer r0 = r2.a
            android.app.Activity r1 = r0.mActivity
            if (r1 == 0) goto L_0x0015
            int r0 = r0.mInitialScreenOrientation
            r1.setRequestedOrientation(r0)
        L_0x0015:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.J0.run():void");
    }
}
