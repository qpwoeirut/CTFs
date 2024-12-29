package com.unity3d.player;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.core.view.WindowInsetsCompat$Impl28$$ExternalSyntheticApiModelOutline0;
import bitter.jnibridge.a$$ExternalSyntheticApiModelOutline0;

abstract class I1 {
    static Point a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (PlatformSupport.RED_VELVET_CAKE_SUPPORT) {
            WindowMetrics m = windowManager.getCurrentWindowMetrics();
            WindowInsetsCompat$Impl28$$ExternalSyntheticApiModelOutline0.m(a$$ExternalSyntheticApiModelOutline0.m(m), a$$ExternalSyntheticApiModelOutline0.m());
            return new Point(a$$ExternalSyntheticApiModelOutline0.m(m).width(), a$$ExternalSyntheticApiModelOutline0.m(m).height());
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }
}
