package com.unity3d.player;

import android.util.Log;

/* renamed from: com.unity3d.player.x  reason: case insensitive filesystem */
abstract class C0055x {
    protected static boolean a = false;

    protected static void Log(int i, String str) {
        if (!a) {
            if (i == 6) {
                Log.e("Unity", str);
            }
            if (i == 5) {
                Log.w("Unity", str);
            }
        }
    }
}
