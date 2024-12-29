package com.unity3d.player;

final class M implements Runnable {
    final long a;
    final long b;

    public M(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    public final void run() {
        if (ReflectionHelper.beginProxyCall(this.a)) {
            try {
                ReflectionHelper.nativeProxyFinalize(this.b);
            } finally {
                ReflectionHelper.endProxyCall();
            }
        }
    }
}
