package com.unity3d.player;

import android.content.res.Configuration;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.CaptioningManager;
import java.util.Objects;
import java.util.concurrent.Semaphore;

class UnityAccessibilityDelegate extends View.AccessibilityDelegate {
    /* access modifiers changed from: private */
    public final UnityPlayer a;
    /* access modifiers changed from: private */
    public final SurfaceView b;
    /* access modifiers changed from: private */
    public AccessibilityManager c;
    /* access modifiers changed from: private */
    public C0038o0 d;
    /* access modifiers changed from: private */
    public CaptioningManager e;
    /* access modifiers changed from: private */
    public C0042q0 f;
    /* access modifiers changed from: private */
    public int g = -1;
    /* access modifiers changed from: private */
    public int h = -1;
    /* access modifiers changed from: private */
    public boolean i = false;
    private float j = 1.0f;
    private AccessibilityNodeProvider k = new C0034m0(this);

    UnityAccessibilityDelegate(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
        this.b = unityPlayer.getSurfaceView();
    }

    /* access modifiers changed from: private */
    public static native int[] getRootNodeIds();

    /* access modifiers changed from: private */
    public static native int hitTest(float f2, float f3);

    protected static UnityAccessibilityDelegate init(UnityPlayer unityPlayer) {
        UnityAccessibilityDelegate unityAccessibilityDelegate = new UnityAccessibilityDelegate(unityPlayer);
        unityAccessibilityDelegate.c = (AccessibilityManager) unityAccessibilityDelegate.a.getContext().getSystemService("accessibility");
        CaptioningManager captioningManager = (CaptioningManager) unityAccessibilityDelegate.a.getContext().getSystemService("captioning");
        unityAccessibilityDelegate.e = captioningManager;
        if (!(unityAccessibilityDelegate.c == null && captioningManager == null)) {
            Semaphore semaphore = new Semaphore(0);
            unityAccessibilityDelegate.a.runOnUiThread(new C0013e0(unityAccessibilityDelegate, semaphore));
            try {
                semaphore.acquire();
            } catch (InterruptedException unused) {
            }
        }
        unityAccessibilityDelegate.j = unityAccessibilityDelegate.a.getContext().getResources().getConfiguration().fontScale;
        unityAccessibilityDelegate.a.setAccessibilityDelegate(unityAccessibilityDelegate);
        return unityAccessibilityDelegate;
    }

    /* access modifiers changed from: private */
    public static native boolean isNodeDismissable(int i2);

    /* access modifiers changed from: private */
    public static native boolean isNodeSelectable(int i2);

    /* access modifiers changed from: private */
    public static native void onNodeDecremented(int i2);

    /* access modifiers changed from: private */
    public static native boolean onNodeDismissed(int i2);

    /* access modifiers changed from: private */
    public static native void onNodeFocusChanged(int i2, boolean z);

    /* access modifiers changed from: private */
    public static native void onNodeIncremented(int i2);

    /* access modifiers changed from: private */
    public static native boolean onNodeSelected(int i2);

    /* access modifiers changed from: private */
    public static native boolean populateNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i2, View view);

    /* access modifiers changed from: private */
    public static native void sendClosedCaptioningChangedNotification(boolean z);

    /* access modifiers changed from: private */
    public static native void sendElementFocusedNotification(int i2);

    /* access modifiers changed from: private */
    public static native void sendFontScaleChangedNotification(float f2);

    /* access modifiers changed from: private */
    public static native void sendScreenReaderStatusChangedNotification(boolean z);

    public final void a(Configuration configuration) {
        float f2 = configuration.fontScale;
        if (f2 != this.j) {
            this.j = f2;
            UnityPlayer unityPlayer = this.a;
            Objects.requireNonNull(unityPlayer);
            this.a.invokeOnMainThread((Runnable) new C0016f0(unityPlayer, configuration));
        }
    }

    /* access modifiers changed from: protected */
    public void cleanup() {
        C0038o0 o0Var = this.d;
        if (o0Var != null) {
            o0Var.cleanup();
        }
        C0042q0 q0Var = this.f;
        if (q0Var != null) {
            q0Var.cleanup();
        }
        this.a.setAccessibilityDelegate((UnityAccessibilityDelegate) null);
    }

    public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        return this.k;
    }

    /* access modifiers changed from: protected */
    public int getFocusedNodeId() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    public boolean sendAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        ViewGroup viewGroup;
        if (accessibilityEvent == null || (viewGroup = (ViewGroup) this.b.getParent()) == null) {
            return false;
        }
        return viewGroup.requestSendAccessibilityEvent(this.b, accessibilityEvent);
    }

    /* access modifiers changed from: protected */
    public boolean sendAnnouncementForVirtualViewId(int i2, String str) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
        obtain.setEnabled(true);
        obtain.setSource(this.b, i2);
        obtain.getText().add(str);
        return sendAccessibilityEvent(obtain);
    }

    /* access modifiers changed from: protected */
    public boolean sendEventForVirtualViewId(int i2, int i3) {
        if (!this.c.isEnabled()) {
            return false;
        }
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i3);
        obtain.setEnabled(true);
        obtain.setSource(this.b, i2);
        if (i3 == 2048) {
            obtain.setContentChangeTypes(1);
        }
        return sendAccessibilityEvent(obtain);
    }

    /* access modifiers changed from: protected */
    public boolean sendEventForVirtualViewIdFromNative(int i2, int i3) {
        this.a.runOnUiThread(new C0019g0(this, i2, i3));
        return true;
    }
}
