package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import java.util.List;

class PlayAssetDeliveryUnityWrapper {
    private static PlayAssetDeliveryUnityWrapper b;
    private C0029k a = null;

    private PlayAssetDeliveryUnityWrapper(UnityPlayer unityPlayer, Context context) {
        if (b == null) {
            try {
                if (getClass().getClassLoader().loadClass("com.google.android.play.core.assetpacks.AssetPackManager").getMethod("getPackStates", new Class[]{List.class}).getReturnType().getName().equals("com.google.android.gms.tasks.Task")) {
                    this.a = a(unityPlayer, context);
                }
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException unused) {
            }
        } else {
            throw new RuntimeException("PlayAssetDeliveryUnityWrapper should be created only once. Use getInstance() instead.");
        }
    }

    private static C0029k a(UnityPlayer unityPlayer, Context context) {
        return C0029k.a(unityPlayer, context);
    }

    private void a() {
        if (playCoreApiMissing()) {
            throw new RuntimeException("AssetPackManager API is not available! Make sure your gradle project includes 'com.google.android.play:asset-delivery' dependency.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        if (r1 == null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0024, code lost:
        throw new java.lang.RuntimeException("PlayAssetDeliveryUnityWrapper is not yet initialised.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.unity3d.player.PlayAssetDeliveryUnityWrapper getInstance() {
        /*
            java.lang.Class<com.unity3d.player.PlayAssetDeliveryUnityWrapper> r0 = com.unity3d.player.PlayAssetDeliveryUnityWrapper.class
            monitor-enter(r0)
        L_0x0003:
            com.unity3d.player.PlayAssetDeliveryUnityWrapper r1 = b     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0019
            java.lang.Class<com.unity3d.player.PlayAssetDeliveryUnityWrapper> r1 = com.unity3d.player.PlayAssetDeliveryUnityWrapper.class
            r2 = 3000(0xbb8, double:1.482E-320)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x000f }
            goto L_0x0003
        L_0x000f:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0025 }
            r2 = 6
            com.unity3d.player.C0055x.Log(r2, r1)     // Catch:{ all -> 0x0025 }
            goto L_0x0003
        L_0x0019:
            if (r1 == 0) goto L_0x001d
            monitor-exit(r0)
            return r1
        L_0x001d:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x0025 }
            java.lang.String r2 = "PlayAssetDeliveryUnityWrapper is not yet initialised."
            r1.<init>(r2)     // Catch:{ all -> 0x0025 }
            throw r1     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.PlayAssetDeliveryUnityWrapper.getInstance():com.unity3d.player.PlayAssetDeliveryUnityWrapper");
    }

    public static synchronized PlayAssetDeliveryUnityWrapper init(UnityPlayer unityPlayer, Context context) {
        PlayAssetDeliveryUnityWrapper playAssetDeliveryUnityWrapper;
        synchronized (PlayAssetDeliveryUnityWrapper.class) {
            if (b == null) {
                b = new PlayAssetDeliveryUnityWrapper(unityPlayer, context);
                PlayAssetDeliveryUnityWrapper.class.notifyAll();
                playAssetDeliveryUnityWrapper = b;
            } else {
                throw new RuntimeException("PlayAssetDeliveryUnityWrapper.init() should be called only once. Use getInstance() instead.");
            }
        }
        return playAssetDeliveryUnityWrapper;
    }

    public void cancelAssetPackDownload(String str) {
        cancelAssetPackDownloads(new String[]{str});
    }

    public void cancelAssetPackDownloads(String[] strArr) {
        a();
        this.a.a(strArr);
    }

    public void downloadAssetPack(String str, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        downloadAssetPacks(new String[]{str}, iAssetPackManagerDownloadStatusCallback);
    }

    public void downloadAssetPacks(String[] strArr, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        a();
        this.a.a(strArr, iAssetPackManagerDownloadStatusCallback);
    }

    public String getAssetPackPath(String str) {
        a();
        return this.a.a(str);
    }

    public void getAssetPackState(String str, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback) {
        getAssetPackStates(new String[]{str}, iAssetPackManagerStatusQueryCallback);
    }

    public void getAssetPackStates(String[] strArr, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback) {
        a();
        this.a.a(strArr, iAssetPackManagerStatusQueryCallback);
    }

    public boolean playCoreApiMissing() {
        return this.a == null;
    }

    public Object registerDownloadStatusListener(IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        a();
        return this.a.a(iAssetPackManagerDownloadStatusCallback);
    }

    public void removeAssetPack(String str) {
        a();
        this.a.b(str);
    }

    public void requestToUseMobileData(Activity activity, IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback) {
        a();
        this.a.a(activity, iAssetPackManagerMobileDataConfirmationCallback);
    }

    public void unregisterDownloadStatusListener(Object obj) {
        a();
        this.a.a(obj);
    }
}
