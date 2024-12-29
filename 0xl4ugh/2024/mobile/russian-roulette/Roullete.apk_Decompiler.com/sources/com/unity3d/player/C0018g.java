package com.unity3d.player;

import com.google.android.gms.tasks.OnSuccessListener;

/* renamed from: com.unity3d.player.g  reason: case insensitive filesystem */
final class C0018g implements OnSuccessListener {
    private IAssetPackManagerMobileDataConfirmationCallback a;
    private UnityPlayer b;

    public C0018g(UnityPlayer unityPlayer, IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback) {
        this.b = unityPlayer;
        this.a = iAssetPackManagerMobileDataConfirmationCallback;
    }

    public final void onSuccess(Object obj) {
        Integer num = (Integer) obj;
        IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback = this.a;
        if (iAssetPackManagerMobileDataConfirmationCallback != null) {
            this.b.invokeOnMainThread((Runnable) new C0015f(iAssetPackManagerMobileDataConfirmationCallback, num.intValue() == -1));
        }
    }
}
