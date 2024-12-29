package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;

final class N extends RelativeLayout {
    protected Button a;
    protected EditText b;
    protected Context c;
    protected Rect d = new Rect(16, 16, 16, 16);
    protected Rect e = new Rect(0, 0, 0, 0);

    public N(Context context, EditText editText) {
        super(context);
        this.b = editText;
        this.c = context;
        createUI();
        setBackgroundColor(-1);
    }

    /* access modifiers changed from: protected */
    public void createUI() {
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        Button button = new Button(this.c);
        this.a = button;
        button.setId(View.generateViewId());
        this.a.setText(this.c.getResources().getIdentifier("ok", TypedValues.Custom.S_STRING, "android"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(11);
        this.a.setLayoutParams(layoutParams);
        this.a.setBackgroundColor(0);
        addView(this.a);
        this.b.setId(View.generateViewId());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(0, this.a.getId());
        this.b.setLayoutParams(layoutParams2);
        addView(this.b);
        Rect rect = this.d;
        setPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
    }
}
