package com.unity3d.player;

final class r1 extends Q0 {
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ UnityPlayerForGameActivity d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    r1(UnityPlayerForGameActivity unityPlayerForGameActivity, int i, int i2) {
        super(unityPlayerForGameActivity);
        this.d = unityPlayerForGameActivity;
        this.b = i;
        this.c = i2;
    }

    public final void a() {
        this.d.nativeOrientationChanged(this.b, this.c);
    }
}
