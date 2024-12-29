package com.unity3d.player;

/* renamed from: com.unity3d.player.u  reason: case insensitive filesystem */
enum C0049u {
    ActivityOrService(0),
    GameActivity(1);
    
    final int a;

    static {
        ActivityOrService = new C0049u("ActivityOrService", 0, 0);
        GameActivity = new C0049u("GameActivity", 1, 1);
    }

    private C0049u(int i) {
        this.a = i;
    }
}
