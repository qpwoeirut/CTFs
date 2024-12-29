package com.unity3d.player;

final class v1 {
    private static boolean e = false;
    private boolean a = false;
    private boolean b = false;
    private boolean c = true;
    private boolean d = false;

    v1() {
    }

    static boolean d() {
        return e;
    }

    static void e() {
        e = true;
    }

    static void f() {
        e = false;
    }

    /* access modifiers changed from: package-private */
    public final boolean a() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public final boolean a(boolean z) {
        return e && (z || this.a) && !this.c && !this.b;
    }

    /* access modifiers changed from: package-private */
    public final void b(boolean z) {
        this.a = z;
    }

    /* access modifiers changed from: package-private */
    public final boolean b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public final void c(boolean z) {
        this.b = z;
    }

    /* access modifiers changed from: package-private */
    public final boolean c() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public final void d(boolean z) {
        this.d = z;
    }

    /* access modifiers changed from: package-private */
    public final void e(boolean z) {
        this.c = z;
    }

    public final String toString() {
        return super.toString();
    }
}
