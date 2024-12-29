package com.unity3d.player;

import android.content.Context;
import android.provider.Settings;

public class OrientationLockListener implements C0004b0 {
    private C0010d0 a;
    private Context b;

    OrientationLockListener(Context context) {
        this.b = context;
        this.a = new C0010d0(context);
        nativeUpdateOrientationLockState(Settings.System.getInt(context.getContentResolver(), "accelerometer_rotation", 0));
        this.a.a(this);
    }

    public final void a() {
        this.a.a();
        this.a = null;
    }

    public final void b() {
        nativeUpdateOrientationLockState(Settings.System.getInt(this.b.getContentResolver(), "accelerometer_rotation", 0));
    }

    public final native void nativeUpdateOrientationLockState(int i);
}
