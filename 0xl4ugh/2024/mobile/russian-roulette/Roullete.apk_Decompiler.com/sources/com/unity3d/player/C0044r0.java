package com.unity3d.player;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: com.unity3d.player.r0  reason: case insensitive filesystem */
public final class C0044r0 implements View.OnHoverListener {
    final /* synthetic */ UnityAccessibilityDelegate a;

    protected C0044r0(UnityAccessibilityDelegate unityAccessibilityDelegate) {
        this.a = unityAccessibilityDelegate;
    }

    public final boolean onHover(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int r5 = UnityAccessibilityDelegate.hitTest(motionEvent.getX(), motionEvent.getY());
            UnityAccessibilityDelegate unityAccessibilityDelegate = this.a;
            int r0 = unityAccessibilityDelegate.h;
            if (r0 == r5) {
                return true;
            }
            unityAccessibilityDelegate.h = r5;
            if (r5 != -1) {
                unityAccessibilityDelegate.sendEventForVirtualViewId(r5, 128);
            }
            if (r0 == -1) {
                return true;
            }
            unityAccessibilityDelegate.sendEventForVirtualViewId(r0, 256);
            return true;
        } else if (action != 10) {
            Log.i("a11y", "hover unknown" + motionEvent.toString());
            return true;
        } else {
            UnityAccessibilityDelegate unityAccessibilityDelegate2 = this.a;
            int r6 = unityAccessibilityDelegate2.h;
            if (r6 == -1) {
                return true;
            }
            unityAccessibilityDelegate2.h = -1;
            if (r6 == -1) {
                return true;
            }
            unityAccessibilityDelegate2.sendEventForVirtualViewId(r6, 256);
            return true;
        }
    }
}
