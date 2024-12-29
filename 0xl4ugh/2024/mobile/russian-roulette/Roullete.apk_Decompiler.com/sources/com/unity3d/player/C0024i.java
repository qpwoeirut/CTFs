package com.unity3d.player;

/* renamed from: com.unity3d.player.i  reason: case insensitive filesystem */
final class C0024i implements Runnable {
    private IAssetPackManagerStatusQueryCallback a;
    private long b;
    private String[] c;
    private int[] d;
    private int[] e;

    C0024i(IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback, long j, String[] strArr, int[] iArr, int[] iArr2) {
        this.a = iAssetPackManagerStatusQueryCallback;
        this.b = j;
        this.c = strArr;
        this.d = iArr;
        this.e = iArr2;
    }

    public final void run() {
        this.a.onStatusResult(this.b, this.c, this.d, this.e);
    }
}
