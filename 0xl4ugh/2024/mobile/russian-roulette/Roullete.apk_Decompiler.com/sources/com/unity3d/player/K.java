package com.unity3d.player;

import java.lang.reflect.Member;

final class K {
    private final Class a;
    private final String b;
    private final String c;
    /* access modifiers changed from: private */
    public final int d;
    public volatile Member e;

    K(Class cls, String str, String str2) {
        this.a = cls;
        this.b = str;
        this.c = str2;
        int hashCode = str.hashCode();
        this.d = str2.hashCode() + ((hashCode + ((cls.hashCode() + 527) * 31)) * 31);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof K)) {
            return false;
        }
        K k = (K) obj;
        return this.d == k.d && this.c.equals(k.c) && this.b.equals(k.b) && this.a.equals(k.a);
    }

    public final int hashCode() {
        return this.d;
    }
}
