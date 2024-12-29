package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;

/* renamed from: com.unity3d.player.y0  reason: case insensitive filesystem */
final class C0058y0 extends FrameLayout {
    /* access modifiers changed from: private */
    public C0006c a;
    /* access modifiers changed from: private */
    public UnityPlayerForActivityOrService b;
    /* access modifiers changed from: private */
    public I c;

    public C0058y0(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        super(unityPlayerForActivityOrService.getContext());
        int i;
        Context context = unityPlayerForActivityOrService.getContext();
        this.c = new I(context);
        this.b = unityPlayerForActivityOrService;
        C0006c cVar = new C0006c(unityPlayerForActivityOrService);
        this.a = cVar;
        cVar.setId(context.getResources().getIdentifier("unitySurfaceView", "id", context.getPackageName()));
        if (a()) {
            this.a.getHolder().setFormat(-3);
            this.a.setZOrderOnTop(true);
            i = 0;
        } else {
            this.a.getHolder().setFormat(-1);
            i = ViewCompat.MEASURED_STATE_MASK;
        }
        setBackgroundColor(i);
        this.a.getHolder().addCallback(new C0056x0(this));
        this.a.setFocusable(true);
        this.a.setFocusableInTouchMode(true);
        this.a.setContentDescription(a(context));
        addView(this.a, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    private static String a(Context context) {
        return context.getResources().getString(context.getResources().getIdentifier("game_view_content_description", TypedValues.Custom.S_STRING, context.getPackageName()));
    }

    private boolean a() {
        Activity activity = this.b.getActivity();
        if (activity == null) {
            return false;
        }
        TypedArray obtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{16842840});
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z;
    }

    /* access modifiers changed from: package-private */
    public final void a(float f) {
        this.a.a(f);
    }

    /* access modifiers changed from: package-private */
    public final C0006c b() {
        return this.a;
    }

    public final void c() {
        I i = this.c;
        FrameLayout frameLayout = this.b.getFrameLayout();
        H h = i.b;
        if (!(h == null || h.getParent() == null)) {
            frameLayout.removeView(i.b);
        }
        this.c.b = null;
    }

    public final boolean d() {
        C0006c cVar = this.a;
        return cVar != null && cVar.a();
    }
}
