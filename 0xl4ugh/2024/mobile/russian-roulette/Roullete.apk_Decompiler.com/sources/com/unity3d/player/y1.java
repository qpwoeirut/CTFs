package com.unity3d.player;

public final class y1 implements Runnable {
    private z1 a;
    /* access modifiers changed from: private */
    public boolean b = false;

    public y1(z1 z1Var) {
        this.a = z1Var;
    }

    public final void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
        if (!this.b) {
            int i = z1.A;
            this.a.cancelOnPrepare();
        }
    }
}
