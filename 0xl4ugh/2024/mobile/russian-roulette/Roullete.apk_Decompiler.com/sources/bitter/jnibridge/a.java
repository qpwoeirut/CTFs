package bitter.jnibridge;

import java.io.PrintStream;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

final class a implements InvocationHandler {
    /* access modifiers changed from: private */
    public Object[] a = new Object[0];
    /* access modifiers changed from: private */
    public long b;

    public a(long j) {
        this.b = j;
    }

    private static Object a(Object obj, Method method, Object[] objArr) {
        if (objArr == null) {
            objArr = new Object[0];
        }
        try {
            return a$$ExternalSyntheticApiModelOutline0.m().findSpecial(method.getDeclaringClass(), method.getName(), MethodType.methodType(method.getReturnType(), method.getParameterTypes()), obj.getClass()).bindTo(obj).invokeWithArguments(objArr);
        } catch (Exception e) {
            PrintStream printStream = System.err;
            printStream.println("JNIBridge error calling default method: " + e.getMessage());
            return null;
        }
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        synchronized (this.a) {
            long j = this.b;
            if (j == 0) {
                return null;
            }
            try {
                Object invoke = JNIBridge.invoke(j, method.getDeclaringClass(), method, objArr);
                return invoke;
            } catch (NoSuchMethodError e) {
                if (method.isDefault()) {
                    return a(obj, method, objArr);
                }
                System.err.println("JNIBridge error: Java interface default methods are only supported since Android Oreo");
                throw e;
            }
        }
    }
}
