package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.view.PixelCopy$OnPixelCopyFinishedListener;
import java.util.concurrent.Semaphore;

final class G implements PixelCopy$OnPixelCopyFinishedListener {
    final /* synthetic */ Semaphore a;
    final /* synthetic */ H b;

    G(H h, Semaphore semaphore) {
        this.b = h;
        this.a = semaphore;
    }

    public final void onPixelCopyFinished(int i) {
        this.a.release();
        if (i == 0) {
            Context context = this.b.b.a;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new F(this));
            }
        }
    }
}
