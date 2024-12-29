package com.unity3d.player;

final class t1 extends Q0 {
    final /* synthetic */ UnityPlayerForGameActivity b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    t1(UnityPlayerForGameActivity unityPlayerForGameActivity) {
        super(unityPlayerForGameActivity);
        this.b = unityPlayerForGameActivity;
    }

    public final void a() {
        this.b.nativeUnityPlayerSetRunning(true);
    }
}
