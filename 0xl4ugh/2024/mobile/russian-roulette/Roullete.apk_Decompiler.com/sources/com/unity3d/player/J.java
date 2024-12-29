package com.unity3d.player;

import bitter.jnibridge.a$$ExternalSyntheticApiModelOutline0;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

final class J implements InvocationHandler {
    private Runnable a;
    private UnityPlayer b;
    private long c;
    final /* synthetic */ long d;

    J(UnityPlayer unityPlayer, long j) {
        this.d = j;
        long r1 = ReflectionHelper.b;
        this.a = new M(r1, j);
        this.b = unityPlayer;
        this.c = r1;
    }

    private static Object a(Object obj, Method method, Object[] objArr, L l) {
        if (objArr == null) {
            try {
                objArr = new Object[0];
            } catch (NoClassDefFoundError unused) {
                C0055x.Log(6, String.format("Java interface default methods are only supported since Android Oreo", new Object[0]));
                ReflectionHelper.nativeProxyLogJNIInvokeException(l.a);
                l.a = 0;
                return null;
            } catch (Throwable th) {
                long r10 = l.a;
                if (r10 != 0) {
                    ReflectionHelper.nativeProxyJNIFreeGCHandle(r10);
                }
                throw th;
            }
        }
        Class<?> declaringClass = method.getDeclaringClass();
        Constructor declaredConstructor = a$$ExternalSyntheticApiModelOutline0.m().getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
        declaredConstructor.setAccessible(true);
        Object m = a$$ExternalSyntheticApiModelOutline0.m(declaredConstructor.newInstance(new Object[]{declaringClass, 2})).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
        long r102 = l.a;
        if (r102 != 0) {
            ReflectionHelper.nativeProxyJNIFreeGCHandle(r102);
        }
        return m;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        this.b.invokeOnMainThread(this.a);
        super.finalize();
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        if (!ReflectionHelper.beginProxyCall(this.c)) {
            C0055x.Log(6, "Scripting proxy object was destroyed, because Unity player was unloaded.");
            return null;
        }
        try {
            Object r0 = ReflectionHelper.nativeProxyInvoke(this.d, method.getName(), objArr);
            if (r0 instanceof L) {
                L l = (L) r0;
                if (l.b && (method.getModifiers() & 1024) == 0) {
                    return a(obj, method, objArr, l);
                }
                ReflectionHelper.nativeProxyLogJNIInvokeException(l.a);
                ReflectionHelper.endProxyCall();
                return null;
            }
            ReflectionHelper.endProxyCall();
            return r0;
        } finally {
            ReflectionHelper.endProxyCall();
        }
    }
}
