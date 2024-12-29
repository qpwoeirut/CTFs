package com.unity3d.player;

import com.unity3d.player.a.a;
import com.unity3d.player.a.f;

abstract class SoftInputProvider {
    public static int a() {
        int nativeGetSoftInputType = nativeGetSoftInputType();
        for (int i : a.b(3)) {
            if (f.a(i) == nativeGetSoftInputType) {
                return i;
            }
        }
        return 1;
    }

    private static final native int nativeGetSoftInputType();
}
