package com.unity3d.player;

enum A0 {
    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    i;

    static {
        a = new A0(0, "PAUSE");
        b = new A0(1, "RESUME");
        c = new A0(2, "QUIT");
        d = new A0(3, "SURFACE_LOST");
        e = new A0(4, "SURFACE_ACQUIRED");
        f = new A0(5, "FOCUS_LOST");
        g = new A0(6, "FOCUS_GAINED");
        h = new A0(7, "NEXT_FRAME");
        i = new A0(8, "ORIENTATION_ANGLE_CHANGE");
    }

    private A0() {
    }
}
