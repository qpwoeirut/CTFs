package com.unity3d.player;

public class PermissionRequest {
    static final int DENIED = 2;
    static final int DENIED_DONT_ASK_AGAIN = 3;
    static final int GRANTED = 1;
    private IPermissionRequestCallbacks mCallbacks;
    private String[] mPermissionNames;

    public PermissionRequest(String[] strArr, IPermissionRequestCallbacks iPermissionRequestCallbacks) {
        this.mPermissionNames = strArr;
        this.mCallbacks = iPermissionRequestCallbacks;
    }

    /* access modifiers changed from: package-private */
    public String[] getPermissionNames() {
        return this.mPermissionNames;
    }

    /* access modifiers changed from: package-private */
    public void permissionResponse(String[] strArr, int[] iArr) {
        IPermissionRequestCallbacks iPermissionRequestCallbacks = this.mCallbacks;
        if (iPermissionRequestCallbacks != null) {
            iPermissionRequestCallbacks.onPermissionResult(strArr, iArr);
        }
    }
}
