package com.unity3d.player;

import java.util.Set;

/* renamed from: com.unity3d.player.d  reason: case insensitive filesystem */
final class C0009d implements Runnable {
    private Set a;
    private String b;
    private int c;
    private long d;
    private long e;
    private int f;
    private int g;

    C0009d(Set set, String str, int i, long j, long j2, int i2, int i3) {
        this.a = set;
        this.b = str;
        this.c = i;
        this.d = j;
        this.e = j2;
        this.f = i2;
        this.g = i3;
    }

    public final void run() {
        for (IAssetPackManagerDownloadStatusCallback onStatusUpdate : this.a) {
            onStatusUpdate.onStatusUpdate(this.b, this.c, this.d, this.e, this.f, this.g);
        }
    }
}
