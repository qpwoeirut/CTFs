package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.view.SurfaceView;
import android.widget.FrameLayout;

public class UnityPlayerForGameActivity extends UnityPlayer {
    Thread m_MainThread;
    /* access modifiers changed from: private */
    public I m_PersistentUnitySurface;
    SurfaceView m_SurfaceView;

    public UnityPlayerForGameActivity(Activity activity, FrameLayout frameLayout, SurfaceView surfaceView) {
        this(activity, frameLayout, surfaceView, (IUnityPlayerLifecycleEvents) null);
    }

    public UnityPlayerForGameActivity(Activity activity, FrameLayout frameLayout, SurfaceView surfaceView, IUnityPlayerLifecycleEvents iUnityPlayerLifecycleEvents) {
        super(activity, C0049u.GameActivity, iUnityPlayerLifecycleEvents);
        this.m_SurfaceView = surfaceView;
        initialize(frameLayout);
        this.m_MainThread = null;
        nativeUnityPlayerForGameActivityInitialized(v1.d() ^ true ? 1 : 0);
        this.m_PersistentUnitySurface = new I(activity);
        this.m_SurfaceView.getHolder().addCallback(new q1(this));
    }

    public static int getUnityViewIdentifier(Context context) {
        return context.getResources().getIdentifier("unitySurfaceView", "id", context.getPackageName());
    }

    /* access modifiers changed from: private */
    public final native void nativeOrientationChanged(int i, int i2);

    private final native void nativeUnityPlayerForGameActivityInitialized(int i);

    /* access modifiers changed from: private */
    public final native void nativeUnityPlayerSetRunning(boolean z);

    /* access modifiers changed from: protected */
    public void cleanupResourcesForDestroy() {
    }

    public SurfaceView getSurfaceView() {
        return this.m_SurfaceView;
    }

    public SurfaceView getView() {
        return getSurfaceView();
    }

    /* access modifiers changed from: package-private */
    public boolean handleFocus(boolean z) {
        return true;
    }

    /* access modifiers changed from: package-private */
    public void hidePreservedContent() {
        runOnUiThread(new u1(this));
    }

    /* access modifiers changed from: package-private */
    public void onOrientationChanged(int i, int i2) {
        invokeOnMainThread((Runnable) new r1(this, i, i2));
    }

    /* access modifiers changed from: package-private */
    public void pauseUnity() {
        super.pauseUnity();
        invokeOnMainThread((Runnable) new s1(this));
    }

    /* access modifiers changed from: package-private */
    public void resumeUnity() {
        super.resumeUnity();
        invokeOnMainThread((Runnable) new t1(this));
    }

    public boolean runningOnMainThread() {
        if (this.m_MainThread != null) {
            return Thread.currentThread() == this.m_MainThread;
        }
        throw new RuntimeException("Main Thread was not yet set.");
    }

    public void setMainSurfaceViewAspectRatio(float f) {
        C0055x.Log(6, "setMainSurfaceViewAspectRatio is not supported for GameActivity");
    }

    /* access modifiers changed from: protected */
    public void setMainThread() {
        if (this.m_MainThread == null) {
            this.m_MainThread = Thread.currentThread();
            return;
        }
        throw new RuntimeException("Main Thread was already set.");
    }
}
