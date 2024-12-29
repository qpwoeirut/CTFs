package com.unity3d.player;

import android.os.SystemClock;
import android.view.KeyEvent;

final class E0 implements Runnable {
    final /* synthetic */ UnityPlayer a;

    E0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    public final void run() {
        long uptimeMillis = SystemClock.uptimeMillis();
        KeyEvent keyEvent = r1;
        KeyEvent keyEvent2 = new KeyEvent(uptimeMillis, uptimeMillis, 0, 4, 1, 0, -1, 0, 0, 257);
        long j = uptimeMillis + 1;
        long j2 = uptimeMillis;
        KeyEvent keyEvent3 = r1;
        KeyEvent keyEvent4 = new KeyEvent(j2, j, 1, 4, 1, 0, -1, 0, 0, 257);
        this.a.getActivity().dispatchKeyEvent(keyEvent);
        this.a.getActivity().dispatchKeyEvent(keyEvent3);
    }
}
