package com.unity3d.player;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.Objects;

/* renamed from: com.unity3d.player.m0  reason: case insensitive filesystem */
final class C0034m0 extends AccessibilityNodeProvider {
    final /* synthetic */ UnityAccessibilityDelegate a;

    C0034m0(UnityAccessibilityDelegate unityAccessibilityDelegate) {
        this.a = unityAccessibilityDelegate;
    }

    public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        if (i == -1) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain(this.a.b);
            ViewParent parent = this.a.b.getParent();
            if (parent instanceof View) {
                obtain.setParent((View) parent);
            }
            int[] r0 = UnityAccessibilityDelegate.getRootNodeIds();
            if (r0 != null) {
                for (int addChild : r0) {
                    obtain.addChild(this.a.b, addChild);
                }
            }
            return obtain;
        }
        AccessibilityNodeInfo obtain2 = AccessibilityNodeInfo.obtain();
        if (!UnityAccessibilityDelegate.populateNodeInfo(obtain2, i, this.a.b)) {
            return null;
        }
        return obtain2;
    }

    public final boolean performAction(int i, int i2, Bundle bundle) {
        if (i2 == 64) {
            UnityAccessibilityDelegate unityAccessibilityDelegate = this.a;
            if (unityAccessibilityDelegate.g == i) {
                return false;
            }
            unityAccessibilityDelegate.b.invalidate();
            UnityAccessibilityDelegate unityAccessibilityDelegate2 = this.a;
            unityAccessibilityDelegate2.g = i;
            unityAccessibilityDelegate2.sendEventForVirtualViewId(i, 32768);
            UnityPlayer r5 = this.a.a;
            Objects.requireNonNull(r5);
            this.a.a.invokeOnMainThread((Runnable) new C0022h0(r5, i));
            return true;
        } else if (i2 == 128) {
            this.a.b.invalidate();
            UnityAccessibilityDelegate unityAccessibilityDelegate3 = this.a;
            if (unityAccessibilityDelegate3.g == i) {
                unityAccessibilityDelegate3.g = -1;
            }
            unityAccessibilityDelegate3.sendEventForVirtualViewId(i, 65536);
            UnityPlayer r52 = this.a.a;
            Objects.requireNonNull(r52);
            this.a.a.invokeOnMainThread((Runnable) new C0025i0(r52, i));
            return true;
        } else if (i2 == 16) {
            if (!UnityAccessibilityDelegate.isNodeSelectable(i)) {
                return false;
            }
            UnityPlayer r53 = this.a.a;
            Objects.requireNonNull(r53);
            this.a.a.invokeOnMainThread((Runnable) new C0028j0(this, r53, i));
            return true;
        } else if (i2 == 4096 || i2 == 8192) {
            UnityPlayer r0 = this.a.a;
            Objects.requireNonNull(r0);
            this.a.a.invokeOnMainThread((Runnable) new C0030k0(this, r0, i2, i));
            return true;
        } else if (i2 != 1048576 || !UnityAccessibilityDelegate.isNodeDismissable(i)) {
            return false;
        } else {
            UnityPlayer r54 = this.a.a;
            Objects.requireNonNull(r54);
            this.a.a.invokeOnMainThread((Runnable) new C0032l0(r54, i));
            return true;
        }
    }
}
