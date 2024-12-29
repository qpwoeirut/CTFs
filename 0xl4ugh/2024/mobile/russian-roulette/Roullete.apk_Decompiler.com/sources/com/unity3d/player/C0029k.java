package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import com.google.android.play.core.assetpacks.AssetPackLocation;
import com.google.android.play.core.assetpacks.AssetPackManager;
import com.google.android.play.core.assetpacks.AssetPackManagerFactory;
import java.util.Arrays;
import java.util.HashSet;

/* renamed from: com.unity3d.player.k  reason: case insensitive filesystem */
final class C0029k {
    /* access modifiers changed from: private */
    public static C0029k e;
    /* access modifiers changed from: private */
    public UnityPlayer a;
    /* access modifiers changed from: private */
    public AssetPackManager b;
    /* access modifiers changed from: private */
    public HashSet c;
    /* access modifiers changed from: private */
    public Object d;

    private C0029k(UnityPlayer unityPlayer, Context context) {
        if (e == null) {
            this.a = unityPlayer;
            this.b = AssetPackManagerFactory.getInstance(context);
            this.c = new HashSet();
            return;
        }
        throw new RuntimeException("AssetPackManagerWrapper should be created only once. Use getInstance() instead.");
    }

    public static C0029k a(UnityPlayer unityPlayer, Context context) {
        if (e == null) {
            e = new C0029k(unityPlayer, context);
        }
        return e;
    }

    public final Object a(IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        C0012e eVar = new C0012e(this, this.a, iAssetPackManagerDownloadStatusCallback);
        this.b.registerListener(eVar);
        return eVar;
    }

    public final String a(String str) {
        AssetPackLocation packLocation = this.b.getPackLocation(str);
        return packLocation == null ? "" : packLocation.assetsPath();
    }

    public final void a(Activity activity, IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback) {
        this.b.showCellularDataConfirmation(activity).addOnSuccessListener(new C0018g(this.a, iAssetPackManagerMobileDataConfirmationCallback));
    }

    public final void a(Object obj) {
        if (obj instanceof C0012e) {
            this.b.unregisterListener((C0012e) obj);
        }
    }

    public final void a(String[] strArr) {
        this.b.cancel(Arrays.asList(strArr));
    }

    public final void a(String[] strArr, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        if (strArr != null && strArr.length != 0) {
            this.b.getPackStates(Arrays.asList(strArr)).addOnCompleteListener(new C0021h(this.a, iAssetPackManagerDownloadStatusCallback, strArr));
        }
    }

    public final void a(String[] strArr, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback) {
        this.b.getPackStates(Arrays.asList(strArr)).addOnCompleteListener(new C0027j(this.a, iAssetPackManagerStatusQueryCallback, strArr));
    }

    public final void b(String str) {
        this.b.removePack(str);
    }
}
