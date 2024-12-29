package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.view.Surface;
import com.unity3d.player.a.b;
import java.nio.ByteBuffer;

public class Camera2Wrapper implements b {
    private Context a;
    private C0047t b = null;

    public Camera2Wrapper(Context context) {
        this.a = context;
        initCamera2Jni();
    }

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object obj, Object obj2, Object obj3, int i, int i2, int i3);

    private final native void nativeSurfaceTextureReady(Object obj);

    public final void a(Object obj) {
        nativeSurfaceTextureReady(obj);
    }

    public final void a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i, int i2, int i3) {
        nativeFrameReady(byteBuffer, byteBuffer2, byteBuffer3, i, i2, i3);
    }

    /* access modifiers changed from: protected */
    public void closeCamera2() {
        C0047t tVar = this.b;
        if (tVar != null) {
            tVar.a();
        }
        this.b = null;
    }

    /* access modifiers changed from: protected */
    public int getCamera2Count() {
        return C0047t.a(this.a);
    }

    /* access modifiers changed from: protected */
    public int getCamera2FocalLengthEquivalent(int i) {
        return C0047t.a(this.a, i);
    }

    /* access modifiers changed from: protected */
    public int[] getCamera2Resolutions(int i) {
        return C0047t.b(this.a, i);
    }

    /* access modifiers changed from: protected */
    public int getCamera2SensorOrientation(int i) {
        return C0047t.c(this.a, i);
    }

    /* access modifiers changed from: protected */
    public Rect getFrameSizeCamera2() {
        C0047t tVar = this.b;
        return tVar != null ? tVar.c() : new Rect();
    }

    /* access modifiers changed from: protected */
    public boolean initializeCamera2(int i, int i2, int i3, int i4, int i5, Surface surface) {
        if (this.b != null || UnityPlayer.currentActivity == null) {
            return false;
        }
        C0047t tVar = new C0047t(this);
        this.b = tVar;
        return tVar.a(this.a, i, i2, i3, i4, i5, surface);
    }

    /* access modifiers changed from: protected */
    public boolean isCamera2AutoFocusPointSupported(int i) {
        return C0047t.d(this.a, i);
    }

    /* access modifiers changed from: protected */
    public boolean isCamera2FrontFacing(int i) {
        return C0047t.e(this.a, i);
    }

    /* access modifiers changed from: protected */
    public void pauseCamera2() {
        C0047t tVar = this.b;
        if (tVar != null) {
            tVar.d();
        }
    }

    /* access modifiers changed from: protected */
    public boolean setAutoFocusPoint(float f, float f2) {
        C0047t tVar = this.b;
        if (tVar != null) {
            return tVar.a(f, f2);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void startCamera2() {
        C0047t tVar = this.b;
        if (tVar != null) {
            tVar.h();
        }
    }

    /* access modifiers changed from: protected */
    public void stopCamera2() {
        C0047t tVar = this.b;
        if (tVar != null) {
            tVar.i();
        }
    }
}
