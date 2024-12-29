package com.unity3d.player;

/* renamed from: com.unity3d.player.f  reason: case insensitive filesystem */
final class C0015f implements Runnable {
    private IAssetPackManagerMobileDataConfirmationCallback a;
    private boolean b;

    C0015f(IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback, boolean z) {
        this.a = iAssetPackManagerMobileDataConfirmationCallback;
        this.b = z;
    }

    public final void run() {
        this.a.onMobileDataConfirmationResult(this.b);
    }
}
