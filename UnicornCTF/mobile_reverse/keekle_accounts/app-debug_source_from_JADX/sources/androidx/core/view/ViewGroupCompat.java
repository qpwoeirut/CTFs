package androidx.core.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.C0020R;

public final class ViewGroupCompat {
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;

    private ViewGroupCompat() {
    }

    @Deprecated
    public static boolean onRequestSendAccessibilityEvent(ViewGroup group, View child, AccessibilityEvent event) {
        return group.onRequestSendAccessibilityEvent(child, event);
    }

    @Deprecated
    public static void setMotionEventSplittingEnabled(ViewGroup group, boolean split) {
        group.setMotionEventSplittingEnabled(split);
    }

    public static int getLayoutMode(ViewGroup group) {
        if (VERSION.SDK_INT >= 18) {
            return group.getLayoutMode();
        }
        return 0;
    }

    public static void setLayoutMode(ViewGroup group, int mode) {
        if (VERSION.SDK_INT >= 18) {
            group.setLayoutMode(mode);
        }
    }

    public static void setTransitionGroup(ViewGroup group, boolean isTransitionGroup) {
        if (VERSION.SDK_INT >= 21) {
            group.setTransitionGroup(isTransitionGroup);
        } else {
            group.setTag(C0020R.C0022id.tag_transition_group, Boolean.valueOf(isTransitionGroup));
        }
    }

    public static boolean isTransitionGroup(ViewGroup group) {
        if (VERSION.SDK_INT >= 21) {
            return group.isTransitionGroup();
        }
        Boolean explicit = (Boolean) group.getTag(C0020R.C0022id.tag_transition_group);
        return ((explicit == null || !explicit.booleanValue()) && group.getBackground() == null && ViewCompat.getTransitionName(group) == null) ? false : true;
    }

    public static int getNestedScrollAxes(ViewGroup group) {
        if (VERSION.SDK_INT >= 21) {
            return group.getNestedScrollAxes();
        }
        if (group instanceof NestedScrollingParent) {
            return ((NestedScrollingParent) group).getNestedScrollAxes();
        }
        return 0;
    }
}
