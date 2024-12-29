package com.unity3d.player;

import java.lang.Thread;

/* renamed from: com.unity3d.player.w0  reason: case insensitive filesystem */
final class C0054w0 implements Thread.UncaughtExceptionHandler {
    private volatile Thread.UncaughtExceptionHandler a;
    private String b;

    C0054w0() {
    }

    static void a(String str) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler instanceof C0054w0) {
            C0054w0 w0Var = (C0054w0) defaultUncaughtExceptionHandler;
            int i = -1;
            int i2 = -1;
            while (true) {
                int indexOf = str.indexOf(47, i + 1);
                if (indexOf == -1) {
                    break;
                }
                i2 = i;
                i = indexOf;
            }
            w0Var.b = i2 < 0 ? "Unknown" : str.substring(i2 + 1);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != this) {
            this.a = defaultUncaughtExceptionHandler;
            this.b = "Unknown";
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x00ce */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void uncaughtException(java.lang.Thread r9, java.lang.Throwable r10) {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.Error r0 = new java.lang.Error     // Catch:{ all -> 0x00ce }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ce }
            r1.<init>()     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = "FATAL EXCEPTION [%s]\n"
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ce }
            java.lang.String r5 = r9.getName()     // Catch:{ all -> 0x00ce }
            r6 = 0
            r4[r6] = r5     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ all -> 0x00ce }
            r1.append(r2)     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = "Unity version     : %s\n"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ce }
            java.lang.String r5 = "6000.0.25f1"
            r4[r6] = r5     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ all -> 0x00ce }
            r1.append(r2)     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = "Device model      : %s %s\n"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00ce }
            java.lang.String r5 = android.os.Build.MANUFACTURER     // Catch:{ all -> 0x00ce }
            r4[r6] = r5     // Catch:{ all -> 0x00ce }
            java.lang.String r5 = android.os.Build.MODEL     // Catch:{ all -> 0x00ce }
            r4[r3] = r5     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ all -> 0x00ce }
            r1.append(r2)     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = "Device fingerprint: %s\n"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ce }
            java.lang.String r5 = android.os.Build.FINGERPRINT     // Catch:{ all -> 0x00ce }
            r4[r6] = r5     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ all -> 0x00ce }
            r1.append(r2)     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = "CPU supported ABI : %s\n"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ce }
            java.lang.String[] r5 = android.os.Build.SUPPORTED_ABIS     // Catch:{ all -> 0x00ce }
            java.lang.String r5 = java.util.Arrays.toString(r5)     // Catch:{ all -> 0x00ce }
            r4[r6] = r5     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ all -> 0x00ce }
            r1.append(r2)     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = "Build Type        : %s\n"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ce }
            java.lang.String r5 = "Release"
            r4[r6] = r5     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ all -> 0x00ce }
            r1.append(r2)     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = "Scripting Backend : %s\n"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ce }
            java.lang.String r5 = "IL2CPP"
            r4[r6] = r5     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ all -> 0x00ce }
            r1.append(r2)     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = "Libs loaded from  : %s\n"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ce }
            java.lang.String r5 = r8.b     // Catch:{ all -> 0x00ce }
            r4[r6] = r5     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ all -> 0x00ce }
            r1.append(r2)     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = "Strip Engine Code : %s\n"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ce }
            android.app.Activity r4 = com.unity3d.player.UnityPlayer.currentActivity     // Catch:{ Exception -> 0x00ae }
            android.content.pm.PackageManager r5 = r4.getPackageManager()     // Catch:{ Exception -> 0x00ae }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ Exception -> 0x00ae }
            r7 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r4 = r5.getApplicationInfo(r4, r7)     // Catch:{ Exception -> 0x00ae }
            android.os.Bundle r4 = r4.metaData     // Catch:{ Exception -> 0x00ae }
            java.lang.String r5 = "unity.strip-engine-code"
            boolean r4 = r4.getBoolean(r5)     // Catch:{ Exception -> 0x00ae }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Exception -> 0x00ae }
            goto L_0x00b0
        L_0x00ae:
            java.lang.String r4 = "Undefined"
        L_0x00b0:
            r3[r6] = r4     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = java.lang.String.format(r2, r3)     // Catch:{ all -> 0x00ce }
            r1.append(r2)     // Catch:{ all -> 0x00ce }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ce }
            r0.<init>(r1)     // Catch:{ all -> 0x00ce }
            java.lang.StackTraceElement[] r1 = new java.lang.StackTraceElement[r6]     // Catch:{ all -> 0x00ce }
            r0.setStackTrace(r1)     // Catch:{ all -> 0x00ce }
            r0.initCause(r10)     // Catch:{ all -> 0x00ce }
            java.lang.Thread$UncaughtExceptionHandler r1 = r8.a     // Catch:{ all -> 0x00ce }
            r1.uncaughtException(r9, r0)     // Catch:{ all -> 0x00ce }
            goto L_0x00d3
        L_0x00ce:
            java.lang.Thread$UncaughtExceptionHandler r0 = r8.a     // Catch:{ all -> 0x00d5 }
            r0.uncaughtException(r9, r10)     // Catch:{ all -> 0x00d5 }
        L_0x00d3:
            monitor-exit(r8)
            return
        L_0x00d5:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0054w0.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }
}
