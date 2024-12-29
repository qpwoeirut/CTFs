package com.unity3d.player;

final class G0 implements Runnable {
    final /* synthetic */ PermissionRequest a;
    final /* synthetic */ String[] b;
    final /* synthetic */ int[] c;

    G0(PermissionRequest permissionRequest, String[] strArr, int[] iArr) {
        this.a = permissionRequest;
        this.b = strArr;
        this.c = iArr;
    }

    public final void run() {
        this.a.permissionResponse(this.b, this.c);
    }
}
