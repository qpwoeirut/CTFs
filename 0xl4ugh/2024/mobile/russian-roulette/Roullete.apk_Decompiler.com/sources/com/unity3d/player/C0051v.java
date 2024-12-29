package com.unity3d.player;

/* renamed from: com.unity3d.player.v  reason: case insensitive filesystem */
abstract class C0051v {
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r0.metaData.getBoolean("unity.render-outside-safearea") != false) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.app.Activity r4) {
        /*
            if (r4 == 0) goto L_0x0052
            android.view.Window r0 = r4.getWindow()
            if (r0 == 0) goto L_0x0052
            boolean r0 = com.unity3d.player.PlatformSupport.PIE_SUPPORT
            if (r0 == 0) goto L_0x0052
            boolean r0 = com.unity3d.player.PlatformSupport.VANILLA_ICE_CREAM_SUPPORT
            if (r0 == 0) goto L_0x0011
            goto L_0x002f
        L_0x0011:
            boolean r0 = com.unity3d.player.PlatformSupport.RED_VELVET_CAKE_SUPPORT
            java.lang.String r1 = "unity.render-outside-safearea"
            r2 = 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0031
            android.content.pm.PackageManager r0 = r4.getPackageManager()     // Catch:{ Exception -> 0x0046 }
            java.lang.String r3 = r4.getPackageName()     // Catch:{ Exception -> 0x0046 }
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r3, r2)     // Catch:{ Exception -> 0x0046 }
            if (r0 == 0) goto L_0x0046
            android.os.Bundle r0 = r0.metaData     // Catch:{ Exception -> 0x0046 }
            boolean r0 = r0.getBoolean(r1)     // Catch:{ Exception -> 0x0046 }
            if (r0 == 0) goto L_0x0046
        L_0x002f:
            r0 = 3
            goto L_0x0047
        L_0x0031:
            android.content.pm.PackageManager r0 = r4.getPackageManager()     // Catch:{ Exception -> 0x0046 }
            java.lang.String r3 = r4.getPackageName()     // Catch:{ Exception -> 0x0046 }
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r3, r2)     // Catch:{ Exception -> 0x0046 }
            if (r0 == 0) goto L_0x0046
            android.os.Bundle r0 = r0.metaData     // Catch:{ Exception -> 0x0046 }
            boolean r0 = r0.getBoolean(r1)     // Catch:{ Exception -> 0x0046 }
            goto L_0x0047
        L_0x0046:
            r0 = 0
        L_0x0047:
            android.view.Window r4 = r4.getWindow()
            android.view.WindowManager$LayoutParams r4 = r4.getAttributes()
            r4.layoutInDisplayCutoutMode = r0
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0051v.a(android.app.Activity):void");
    }
}
