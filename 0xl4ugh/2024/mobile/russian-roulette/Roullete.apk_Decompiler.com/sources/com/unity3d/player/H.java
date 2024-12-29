package com.unity3d.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.PixelCopy;
import android.view.SurfaceView;
import android.view.View;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

final class H extends View {
    Bitmap a;
    final /* synthetic */ I b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    H(I i, Context context) {
        super(context);
        this.b = i;
    }

    public final void a(SurfaceView surfaceView) {
        this.a = Bitmap.createBitmap(surfaceView.getWidth(), surfaceView.getHeight(), Bitmap.Config.ARGB_8888);
        HandlerThread handlerThread = new HandlerThread("PlaceHolderView");
        handlerThread.start();
        Semaphore semaphore = new Semaphore(0);
        PixelCopy.request(surfaceView, this.a, new G(this, semaphore), new Handler(handlerThread.getLooper()));
        try {
            if (!semaphore.tryAcquire(2000, TimeUnit.MILLISECONDS)) {
                C0055x.Log(5, "Timeout while copying surface view.");
            }
        } catch (InterruptedException e) {
            C0055x.Log(6, e.getMessage());
        }
        handlerThread.quitSafely();
    }
}
