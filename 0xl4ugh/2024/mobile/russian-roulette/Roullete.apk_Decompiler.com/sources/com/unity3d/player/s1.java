package com.unity3d.player;

final class s1 extends Q0 {
    final /* synthetic */ UnityPlayerForGameActivity b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    s1(UnityPlayerForGameActivity unityPlayerForGameActivity) {
        super(unityPlayerForGameActivity);
        this.b = unityPlayerForGameActivity;
    }

    public final void a() {
        this.b.nativeUnityPlayerSetRunning(false);
    }
}
