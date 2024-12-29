package com.unity3d.player;

import android.view.View;
import android.view.accessibility.AccessibilityManager;
import java.util.Objects;

/* renamed from: com.unity3d.player.o0  reason: case insensitive filesystem */
final class C0038o0 implements AccessibilityManager.AccessibilityStateChangeListener, AccessibilityManager.TouchExplorationStateChangeListener {
    final /* synthetic */ UnityAccessibilityDelegate a;

    C0038o0(UnityAccessibilityDelegate unityAccessibilityDelegate) {
        this.a = unityAccessibilityDelegate;
        unityAccessibilityDelegate.c.addAccessibilityStateChangeListener(this);
        unityAccessibilityDelegate.c.addTouchExplorationStateChangeListener(this);
        if (unityAccessibilityDelegate.c.isEnabled()) {
            onAccessibilityStateChanged(true);
        }
    }

    /* access modifiers changed from: protected */
    public void cleanup() {
        this.a.c.removeAccessibilityStateChangeListener(this);
        this.a.c.removeTouchExplorationStateChangeListener(this);
    }

    public final void onAccessibilityStateChanged(boolean z) {
        if (z) {
            UnityAccessibilityDelegate unityAccessibilityDelegate = this.a;
            unityAccessibilityDelegate.b.setAccessibilityDelegate(unityAccessibilityDelegate);
            this.a.b.setWillNotDraw(false);
            onTouchExplorationStateChanged(this.a.c.isTouchExplorationEnabled());
            return;
        }
        this.a.b.setAccessibilityDelegate((View.AccessibilityDelegate) null);
        this.a.b.setWillNotDraw(true);
        onTouchExplorationStateChanged(false);
    }

    public final void onTouchExplorationStateChanged(boolean z) {
        boolean z2 = this.a.c.isEnabled() && z;
        UnityAccessibilityDelegate unityAccessibilityDelegate = this.a;
        if (z2) {
            unityAccessibilityDelegate.b.setOnHoverListener(new C0044r0(unityAccessibilityDelegate));
        } else {
            unityAccessibilityDelegate.b.setOnHoverListener((View.OnHoverListener) null);
        }
        UnityAccessibilityDelegate unityAccessibilityDelegate2 = this.a;
        if (unityAccessibilityDelegate2.i != z2) {
            unityAccessibilityDelegate2.i = z2;
            UnityPlayer r0 = unityAccessibilityDelegate2.a;
            Objects.requireNonNull(r0);
            this.a.a.invokeOnMainThread((Runnable) new C0036n0(r0, z2));
        }
    }
}
