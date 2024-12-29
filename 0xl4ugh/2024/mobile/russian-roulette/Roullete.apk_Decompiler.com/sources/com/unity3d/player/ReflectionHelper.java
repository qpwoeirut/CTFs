package com.unity3d.player;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;

final class ReflectionHelper {
    protected static boolean LOG = false;
    protected static final boolean LOGV = false;
    private static K[] a = new K[4096];
    /* access modifiers changed from: private */
    public static long b = 0;
    private static long c = 0;
    private static boolean d = false;

    /* JADX WARNING: Can't wrap try/catch for region: R(5:40|41|(1:43)|44|45) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:21|22|(2:24|54)|25|26|(2:28|55)) */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0075, code lost:
        if (r12.asSubclass(r11) != null) goto L_0x0079;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0041 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x0071 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static float a(java.lang.Class r11, java.lang.Class[] r12, java.lang.Class[] r13) {
        /*
            int r0 = r13.length
            r1 = 1036831949(0x3dcccccd, float:0.1)
            if (r0 != 0) goto L_0x0007
            return r1
        L_0x0007:
            r0 = 0
            if (r12 != 0) goto L_0x000c
            r2 = r0
            goto L_0x000d
        L_0x000c:
            int r2 = r12.length
        L_0x000d:
            int r2 = r2 + 1
            int r3 = r13.length
            r4 = 0
            if (r2 == r3) goto L_0x0014
            return r4
        L_0x0014:
            r2 = 1056964608(0x3f000000, float:0.5)
            r3 = 1065353216(0x3f800000, float:1.0)
            if (r12 == 0) goto L_0x004f
            int r5 = r12.length
            r6 = r0
            r7 = r3
        L_0x001d:
            if (r0 >= r5) goto L_0x0050
            r8 = r12[r0]
            int r9 = r6 + 1
            r6 = r13[r6]
            boolean r10 = r8.equals(r6)
            if (r10 == 0) goto L_0x002d
            r6 = r3
            goto L_0x004a
        L_0x002d:
            boolean r10 = r8.isPrimitive()
            if (r10 != 0) goto L_0x0049
            boolean r10 = r6.isPrimitive()
            if (r10 != 0) goto L_0x0049
            java.lang.Class r10 = r8.asSubclass(r6)     // Catch:{ ClassCastException -> 0x0041 }
            if (r10 == 0) goto L_0x0041
            r6 = r2
            goto L_0x004a
        L_0x0041:
            java.lang.Class r6 = r6.asSubclass(r8)     // Catch:{ ClassCastException -> 0x0049 }
            if (r6 == 0) goto L_0x0049
            r6 = r1
            goto L_0x004a
        L_0x0049:
            r6 = r4
        L_0x004a:
            float r7 = r7 * r6
            int r0 = r0 + 1
            r6 = r9
            goto L_0x001d
        L_0x004f:
            r7 = r3
        L_0x0050:
            int r12 = r13.length
            int r12 = r12 + -1
            r12 = r13[r12]
            boolean r13 = r11.equals(r12)
            if (r13 == 0) goto L_0x005d
            r1 = r3
            goto L_0x0079
        L_0x005d:
            boolean r13 = r11.isPrimitive()
            if (r13 != 0) goto L_0x0078
            boolean r13 = r12.isPrimitive()
            if (r13 != 0) goto L_0x0078
            java.lang.Class r13 = r11.asSubclass(r12)     // Catch:{ ClassCastException -> 0x0071 }
            if (r13 == 0) goto L_0x0071
            r1 = r2
            goto L_0x0079
        L_0x0071:
            java.lang.Class r11 = r12.asSubclass(r11)     // Catch:{ ClassCastException -> 0x0078 }
            if (r11 == 0) goto L_0x0078
            goto L_0x0079
        L_0x0078:
            r1 = r4
        L_0x0079:
            float r7 = r7 * r1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.ReflectionHelper.a(java.lang.Class, java.lang.Class[], java.lang.Class[]):float");
    }

    private static Class a(String str, int[] iArr) {
        while (iArr[0] < str.length()) {
            int i = iArr[0];
            iArr[0] = i + 1;
            char charAt = str.charAt(i);
            if (charAt != '(' && charAt != ')') {
                if (charAt == 'L') {
                    int indexOf = str.indexOf(59, iArr[0]);
                    if (indexOf == -1) {
                        return null;
                    }
                    String substring = str.substring(iArr[0], indexOf);
                    iArr[0] = indexOf + 1;
                    try {
                        return Class.forName(substring.replace('/', '.'));
                    } catch (ClassNotFoundException unused) {
                        return null;
                    }
                } else if (charAt == 'Z') {
                    return Boolean.TYPE;
                } else {
                    if (charAt == 'I') {
                        return Integer.TYPE;
                    }
                    if (charAt == 'F') {
                        return Float.TYPE;
                    }
                    if (charAt == 'V') {
                        return Void.TYPE;
                    }
                    if (charAt == 'B') {
                        return Byte.TYPE;
                    }
                    if (charAt == 'C') {
                        return Character.TYPE;
                    }
                    if (charAt == 'S') {
                        return Short.TYPE;
                    }
                    if (charAt == 'J') {
                        return Long.TYPE;
                    }
                    if (charAt == 'D') {
                        return Double.TYPE;
                    }
                    if (charAt == '[') {
                        return Array.newInstance(a(str, iArr), 0).getClass();
                    }
                    C0055x.Log(5, "! parseType; " + charAt + " is not known!");
                    return null;
                }
            }
        }
        return null;
    }

    private static synchronized boolean a(K k) {
        synchronized (ReflectionHelper.class) {
            K k2 = a[k.d & 4095];
            if (!k.equals(k2)) {
                return false;
            }
            k.e = k2.e;
            return true;
        }
    }

    private static Class[] a(String str) {
        Class a2;
        int i = 0;
        int[] iArr = {0};
        ArrayList arrayList = new ArrayList();
        while (iArr[0] < str.length() && (a2 = a(str, iArr)) != null) {
            arrayList.add(a2);
        }
        Class[] clsArr = new Class[arrayList.size()];
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            clsArr[i] = (Class) it.next();
            i++;
        }
        return clsArr;
    }

    protected static synchronized boolean beginProxyCall(long j) {
        synchronized (ReflectionHelper.class) {
            if (j != b) {
                return false;
            }
            c++;
            return true;
        }
    }

    protected static Object createInvocationError(long j, boolean z) {
        return new L(j, z);
    }

    protected static synchronized void endProxyCall() {
        synchronized (ReflectionHelper.class) {
            long j = c - 1;
            c = j;
            if (0 == j && d) {
                ReflectionHelper.class.notifyAll();
            }
        }
    }

    protected static synchronized void endUnityLaunch() {
        synchronized (ReflectionHelper.class) {
            try {
                b++;
                d = true;
                while (c > 0) {
                    ReflectionHelper.class.wait();
                }
            } catch (InterruptedException unused) {
                C0055x.Log(6, "Interrupted while waiting for all proxies to exit.");
            }
            d = false;
        }
    }

    protected static Constructor getConstructorID(Class cls, String str) {
        Constructor constructor;
        K k = new K(cls, "", str);
        if (a(k)) {
            constructor = (Constructor) k.e;
        } else {
            Class[] a2 = a(str);
            Constructor[] constructors = cls.getConstructors();
            int length = constructors.length;
            Constructor constructor2 = null;
            float f = 0.0f;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Constructor constructor3 = constructors[i];
                float a3 = a(Void.TYPE, constructor3.getParameterTypes(), a2);
                if (a3 > f) {
                    if (a3 == 1.0f) {
                        constructor2 = constructor3;
                        break;
                    }
                    constructor2 = constructor3;
                    f = a3;
                }
                i++;
            }
            synchronized (ReflectionHelper.class) {
                k.e = constructor2;
                a[k.d & 4095] = k;
            }
            constructor = constructor2;
        }
        if (constructor != null) {
            return constructor;
        }
        throw new NoSuchMethodError("<init>" + str + " in class " + cls.getName());
    }

    protected static Field getFieldID(Class cls, String str, String str2, boolean z) {
        Field field;
        String str3 = str;
        String str4 = str2;
        boolean z2 = z;
        Class cls2 = cls;
        K k = new K(cls2, str3, str4);
        if (a(k)) {
            field = (Field) k.e;
        } else {
            Class[] a2 = a(str2);
            float f = 0.0f;
            Field field2 = null;
            while (cls2 != null) {
                Field[] declaredFields = cls2.getDeclaredFields();
                int length = declaredFields.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Field field3 = declaredFields[i];
                    if (z2 == Modifier.isStatic(field3.getModifiers()) && field3.getName().compareTo(str3) == 0) {
                        float a3 = a(field3.getType(), (Class[]) null, a2);
                        if (a3 > f) {
                            field2 = field3;
                            if (a3 == 1.0f) {
                                f = a3;
                                break;
                            }
                            f = a3;
                        } else {
                            continue;
                        }
                    }
                    i++;
                }
                if (f == 1.0f || cls2.isPrimitive() || cls2.isInterface() || cls2.equals(Object.class) || cls2.equals(Void.TYPE)) {
                    break;
                }
                cls2 = cls2.getSuperclass();
            }
            synchronized (ReflectionHelper.class) {
                k.e = field2;
                a[k.d & 4095] = k;
            }
            field = field2;
        }
        if (field != null) {
            return field;
        }
        Object[] objArr = new Object[4];
        objArr[0] = z2 ? "static" : "non-static";
        objArr[1] = str3;
        objArr[2] = str4;
        objArr[3] = cls2.getName();
        throw new NoSuchFieldError(String.format("no %s field with name='%s' signature='%s' in class L%s;", objArr));
    }

    protected static String getFieldSignature(Field field) {
        Class<?> type = field.getType();
        if (type.isPrimitive()) {
            String name = type.getName();
            return TypedValues.Custom.S_BOOLEAN.equals(name) ? "Z" : "byte".equals(name) ? "B" : "char".equals(name) ? "C" : "double".equals(name) ? "D" : TypedValues.Custom.S_FLOAT.equals(name) ? "F" : "int".equals(name) ? "I" : "long".equals(name) ? "J" : "short".equals(name) ? "S" : name;
        } else if (type.isArray()) {
            return type.getName().replace('.', '/');
        } else {
            return "L" + type.getName().replace('.', '/') + ";";
        }
    }

    protected static Method getMethodID(Class cls, String str, String str2, boolean z) {
        Method method;
        K k = new K(cls, str, str2);
        if (a(k)) {
            method = (Method) k.e;
        } else {
            Class[] a2 = a(str2);
            Method method2 = null;
            float f = 0.0f;
            while (cls != null) {
                Method[] declaredMethods = cls.getDeclaredMethods();
                int length = declaredMethods.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Method method3 = declaredMethods[i];
                    if (z == Modifier.isStatic(method3.getModifiers()) && method3.getName().compareTo(str) == 0) {
                        float a3 = a(method3.getReturnType(), method3.getParameterTypes(), a2);
                        if (a3 <= f) {
                            continue;
                        } else if (a3 == 1.0f) {
                            method2 = method3;
                            f = a3;
                            break;
                        } else {
                            method2 = method3;
                            f = a3;
                        }
                    }
                    i++;
                }
                if (f == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    break;
                }
                cls = cls.getSuperclass();
            }
            synchronized (ReflectionHelper.class) {
                k.e = method2;
                a[k.d & 4095] = k;
            }
            method = method2;
        }
        if (method != null) {
            return method;
        }
        Object[] objArr = new Object[4];
        objArr[0] = z ? "static" : "non-static";
        objArr[1] = str;
        objArr[2] = str2;
        objArr[3] = cls.getName();
        throw new NoSuchMethodError(String.format("no %s method with name='%s' signature='%s' in class L%s;", objArr));
    }

    /* access modifiers changed from: private */
    public static native void nativeProxyFinalize(long j);

    /* access modifiers changed from: private */
    public static native Object nativeProxyInvoke(long j, String str, Object[] objArr);

    /* access modifiers changed from: private */
    public static native void nativeProxyJNIFreeGCHandle(long j);

    /* access modifiers changed from: private */
    public static native void nativeProxyLogJNIInvokeException(long j);

    protected static Object newProxyInstance(UnityPlayer unityPlayer, long j, Class cls) {
        return newProxyInstance(unityPlayer, j, new Class[]{cls});
    }

    protected static Object newProxyInstance(UnityPlayer unityPlayer, long j, Class[] clsArr) {
        return Proxy.newProxyInstance(ReflectionHelper.class.getClassLoader(), clsArr, new J(unityPlayer, j));
    }
}
