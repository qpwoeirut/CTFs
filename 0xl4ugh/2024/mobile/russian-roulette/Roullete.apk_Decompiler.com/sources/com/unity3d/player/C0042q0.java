package com.unity3d.player;

import android.view.accessibility.CaptioningManager;
import java.util.Objects;

/* renamed from: com.unity3d.player.q0  reason: case insensitive filesystem */
public final class C0042q0 extends CaptioningManager.CaptioningChangeListener {
    final /* synthetic */ UnityAccessibilityDelegate a;

    C0042q0(UnityAccessibilityDelegate unityAccessibilityDelegate) {
        this.a = unityAccessibilityDelegate;
        unityAccessibilityDelegate.e.addCaptioningChangeListener(this);
        onEnabledChanged(unityAccessibilityDelegate.e.isEnabled());
    }

    /* access modifiers changed from: protected */
    public void cleanup() {
        this.a.e.removeCaptioningChangeListener(this);
    }

    public final void onEnabledChanged(boolean z) {
        UnityPlayer r1 = this.a.a;
        Objects.requireNonNull(r1);
        this.a.a.invokeOnMainThread((Runnable) new C0040p0(r1, z));
    }
}
