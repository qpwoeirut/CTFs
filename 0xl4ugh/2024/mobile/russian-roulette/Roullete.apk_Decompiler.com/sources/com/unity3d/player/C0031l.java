package com.unity3d.player;

import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;

/* renamed from: com.unity3d.player.l  reason: case insensitive filesystem */
final class C0031l extends ContentObserver {
    private final C0033m a;
    private final AudioManager b;
    private final int c = 3;
    private int d;

    public C0031l(Handler handler, AudioManager audioManager, C0033m mVar) {
        super(handler);
        this.b = audioManager;
        this.a = mVar;
        this.d = audioManager.getStreamVolume(3);
    }

    public final boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    public final void onChange(boolean z, Uri uri) {
        int streamVolume;
        AudioManager audioManager = this.b;
        if (audioManager != null && this.a != null && (streamVolume = audioManager.getStreamVolume(this.c)) != this.d) {
            this.d = streamVolume;
            this.a.onAudioVolumeChanged(streamVolume);
        }
    }
}
