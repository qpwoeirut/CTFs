package com.unity3d.player;

final class O0 implements IPermissionRequestCallbacks {
    /* access modifiers changed from: private */
    public long a;
    final /* synthetic */ UnityPlayer b;

    public O0(UnityPlayer unityPlayer, long j) {
        this.b = unityPlayer;
        this.a = j;
    }

    public final void onPermissionResult(String[] strArr, int[] iArr) {
        int length = iArr.length;
        boolean z = false;
        if (length != 0) {
            if (length != 1) {
                C0055x.Log(6, "Only a single permission request is supported");
                return;
            } else if (iArr[0] == 1) {
                z = true;
            }
        }
        if (this.a != 0) {
            this.b.invokeOnMainThread((Q0) new N0(this, z));
        }
    }
}
