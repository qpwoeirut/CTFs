package com.unity3d.player;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

final class S extends Dialog implements View.OnClickListener {
    protected Context a;
    protected UnityPlayerForActivityOrService b;
    protected N c = null;
    protected Q d = null;

    public S(Context context, UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        super(context);
        this.a = context;
        this.b = unityPlayerForActivityOrService;
    }

    public final Rect a() {
        Rect rect = new Rect();
        FrameLayout frameLayout = this.b.getFrameLayout();
        frameLayout.getWindowVisibleDisplayFrame(rect);
        int[] iArr = new int[2];
        frameLayout.getLocationOnScreen(iArr);
        Point point = new Point(rect.left - iArr[0], rect.height() - this.c.getHeight());
        Point point2 = new Point();
        getWindow().getWindowManager().getDefaultDisplay().getSize(point2);
        int height = frameLayout.getHeight();
        int i = height - point2.y;
        int i2 = height - point.y;
        int height2 = this.c.getHeight() + i;
        UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.b;
        if (i2 != height2) {
            unityPlayerForActivityOrService.reportSoftInputIsVisible(true);
        } else {
            unityPlayerForActivityOrService.reportSoftInputIsVisible(false);
        }
        return new Rect(point.x, point.y, this.c.getWidth(), i2);
    }

    public final void a(Q q, boolean z, boolean z2) {
        this.d = q;
        Window window = getWindow();
        window.requestFeature(1);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable(0));
        this.c = createSoftInputView(this.d.c);
        window.setLayout(-1, -2);
        window.clearFlags(2);
        window.clearFlags(134217728);
        window.clearFlags(67108864);
        if (!z2) {
            window.addFlags(32);
            window.addFlags(262144);
        }
        a(z);
        getWindow().setSoftInputMode(5);
    }

    public final void a(boolean z) {
        N n = this.c;
        if (z) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) n.b.getLayoutParams();
            layoutParams.height = 1;
            n.b.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) n.a.getLayoutParams();
            layoutParams2.height = 1;
            n.a.setLayoutParams(layoutParams2);
            Rect rect = n.e;
            n.setPadding(rect.left, rect.top, rect.right, rect.bottom);
            n.setVisibility(4);
        } else {
            n.setVisibility(0);
            Rect rect2 = n.d;
            n.setPadding(rect2.left, rect2.top, rect2.right, rect2.bottom);
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) n.b.getLayoutParams();
            layoutParams3.height = -2;
            n.b.setLayoutParams(layoutParams3);
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) n.a.getLayoutParams();
            layoutParams4.height = -2;
            n.a.setLayoutParams(layoutParams4);
        }
        n.invalidate();
        n.requestLayout();
    }

    /* access modifiers changed from: protected */
    public N createSoftInputView(EditText editText) {
        N n = new N(this.a, editText);
        n.a.setOnClickListener(this);
        setContentView(n);
        return n;
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.d.c() || (motionEvent.getAction() != 4 && !this.d.e)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    public final void onBackPressed() {
        this.d.d();
    }

    public final void onClick(View view) {
        Q q = this.d;
        q.a(q.a(), false);
    }

    public final void onStop() {
        this.d.e();
        super.onStop();
    }
}
