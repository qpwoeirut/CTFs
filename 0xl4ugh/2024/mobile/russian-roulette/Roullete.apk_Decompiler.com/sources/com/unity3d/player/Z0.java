package com.unity3d.player;

final class Z0 extends Q0 {
    final /* synthetic */ boolean b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ UnityPlayerForActivityOrService e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Z0(UnityPlayerForActivityOrService unityPlayerForActivityOrService, boolean z, String str, int i) {
        super(unityPlayerForActivityOrService);
        this.e = unityPlayerForActivityOrService;
        this.b = z;
        this.c = str;
        this.d = i;
    }

    public final void a() {
        if (this.b) {
            this.e.nativeSoftInputCanceled();
        } else {
            String str = this.c;
            if (str != null) {
                this.e.nativeSetInputString(str);
            }
        }
        if (this.d == 1) {
            this.e.nativeSoftInputClosed();
        }
    }
}
