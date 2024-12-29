package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.SizeF;
import android.view.Surface;
import com.unity3d.player.a.b;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* renamed from: com.unity3d.player.t  reason: case insensitive filesystem */
public final class C0047t {
    private static CameraManager B;
    private static String[] C;
    /* access modifiers changed from: private */
    public static Semaphore D = new Semaphore(1);
    private int A = 3;
    /* access modifiers changed from: private */
    public b a = null;
    /* access modifiers changed from: private */
    public CameraDevice b;
    private HandlerThread c;
    private Handler d;
    private Rect e;
    private Rect f;
    private int g;
    private int h;
    private float i = -1.0f;
    private float j = -1.0f;
    private int k;
    private int l;
    private boolean m = false;
    /* access modifiers changed from: private */
    public Range n;
    private ImageReader o = null;
    /* access modifiers changed from: private */
    public Image p;
    /* access modifiers changed from: private */
    public CaptureRequest.Builder q;
    /* access modifiers changed from: private */
    public CameraCaptureSession r = null;
    /* access modifiers changed from: private */
    public Object s = new Object();
    private int t;
    private SurfaceTexture u;
    /* access modifiers changed from: private */
    public Surface v = null;
    private CameraCaptureSession.CaptureCallback w = new C0037o(this);
    private final CameraDevice.StateCallback x = new C0041q(this);
    private final ImageReader.OnImageAvailableListener y = new C0043r(this);
    private final SurfaceTexture.OnFrameAvailableListener z = new C0045s(this);

    protected C0047t(b bVar) {
        this.a = bVar;
        e();
    }

    public static int a(Context context) {
        return b(context).length;
    }

    public static int a(Context context, int i2) {
        try {
            CameraCharacteristics cameraCharacteristics = c(context).getCameraCharacteristics(b(context)[i2]);
            float[] fArr = (float[]) cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
            SizeF sizeF = (SizeF) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
            if (fArr.length > 0) {
                return (int) ((fArr[0] * 36.0f) / sizeF.getWidth());
            }
        } catch (CameraAccessException e2) {
            C0055x.Log(6, "Camera2: CameraAccessException " + e2);
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public void a(CameraDevice cameraDevice) {
        synchronized (this.s) {
            this.r = null;
        }
        cameraDevice.close();
        this.b = null;
    }

    /* access modifiers changed from: private */
    public void a(Object obj) {
        if (obj == "Focus") {
            this.m = false;
            synchronized (this.s) {
                if (this.r != null) {
                    try {
                        this.q.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                        this.q.setTag("Regular");
                        this.r.setRepeatingRequest(this.q.build(), this.w, this.d);
                    } catch (CameraAccessException e2) {
                        C0055x.Log(6, "Camera2: CameraAccessException " + e2);
                    }
                }
            }
        } else if (obj == "Cancel focus") {
            synchronized (this.s) {
                if (this.r != null) {
                    g();
                }
            }
        }
    }

    private void b() {
        try {
            Semaphore semaphore = D;
            TimeUnit timeUnit = TimeUnit.SECONDS;
            if (!semaphore.tryAcquire(4, timeUnit)) {
                C0055x.Log(5, "Camera2: Timeout waiting to lock camera for closing.");
                return;
            }
            this.b.close();
            try {
                if (!D.tryAcquire(4, timeUnit)) {
                    C0055x.Log(5, "Camera2: Timeout waiting to close camera.");
                }
            } catch (InterruptedException e2) {
                C0055x.Log(6, "Camera2: Interrupted while waiting to close camera " + e2);
            }
            this.b = null;
            D.release();
        } catch (InterruptedException e3) {
            C0055x.Log(6, "Camera2: Interrupted while trying to lock camera for closing " + e3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        if (r3.length != 0) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int[] b(android.content.Context r3, int r4) {
        /*
            r0 = 6
            r1 = 0
            android.hardware.camera2.CameraManager r2 = c(r3)     // Catch:{ CameraAccessException -> 0x0051 }
            java.lang.String[] r3 = b(r3)     // Catch:{ CameraAccessException -> 0x0051 }
            r3 = r3[r4]     // Catch:{ CameraAccessException -> 0x0051 }
            android.hardware.camera2.CameraCharacteristics r3 = r2.getCameraCharacteristics(r3)     // Catch:{ CameraAccessException -> 0x0051 }
            android.hardware.camera2.CameraCharacteristics$Key r4 = android.hardware.camera2.CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP
            java.lang.Object r3 = r3.get(r4)
            android.hardware.camera2.params.StreamConfigurationMap r3 = (android.hardware.camera2.params.StreamConfigurationMap) r3
            if (r3 != 0) goto L_0x0021
            java.lang.String r3 = "Camera2: configuration map is not available."
            com.unity3d.player.C0055x.Log(r0, r3)
        L_0x001f:
            r3 = r1
            goto L_0x002d
        L_0x0021:
            r4 = 35
            android.util.Size[] r3 = r3.getOutputSizes(r4)
            if (r3 == 0) goto L_0x001f
            int r4 = r3.length
            if (r4 != 0) goto L_0x002d
            goto L_0x001f
        L_0x002d:
            if (r3 == 0) goto L_0x0050
            int r4 = r3.length
            int r4 = r4 * 2
            int[] r4 = new int[r4]
            r0 = 0
        L_0x0035:
            int r1 = r3.length
            if (r0 >= r1) goto L_0x004f
            int r1 = r0 * 2
            r2 = r3[r0]
            int r2 = r2.getWidth()
            r4[r1] = r2
            int r1 = r1 + 1
            r2 = r3[r0]
            int r2 = r2.getHeight()
            r4[r1] = r2
            int r0 = r0 + 1
            goto L_0x0035
        L_0x004f:
            return r4
        L_0x0050:
            return r1
        L_0x0051:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r2 = "Camera2: CameraAccessException "
            r4.<init>(r2)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.unity3d.player.C0055x.Log(r0, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0047t.b(android.content.Context, int):int[]");
    }

    private static String[] b(Context context) {
        if (C == null) {
            try {
                C = c(context).getCameraIdList();
            } catch (CameraAccessException e2) {
                C0055x.Log(6, "Camera2: CameraAccessException " + e2);
                C = new String[0];
            }
        }
        return C;
    }

    public static int c(Context context, int i2) {
        try {
            return ((Integer) c(context).getCameraCharacteristics(b(context)[i2]).get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        } catch (CameraAccessException e2) {
            C0055x.Log(6, "Camera2: CameraAccessException " + e2);
            return 0;
        }
    }

    private static CameraManager c(Context context) {
        if (B == null) {
            B = (CameraManager) context.getSystemService("camera");
        }
        return B;
    }

    public static boolean d(Context context, int i2) {
        try {
            return ((Integer) c(context).getCameraCharacteristics(b(context)[i2]).get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0;
        } catch (CameraAccessException e2) {
            C0055x.Log(6, "Camera2: CameraAccessException " + e2);
            return false;
        }
    }

    private void e() {
        HandlerThread handlerThread = new HandlerThread("CameraBackground");
        this.c = handlerThread;
        handlerThread.start();
        this.d = new Handler(this.c.getLooper());
    }

    public static boolean e(Context context, int i2) {
        try {
            return ((Integer) c(context).getCameraCharacteristics(b(context)[i2]).get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
        } catch (CameraAccessException e2) {
            C0055x.Log(6, "Camera2: CameraAccessException " + e2);
            return false;
        }
    }

    private void f() {
        try {
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.stopRepeating();
                this.q.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
                this.q.set(CaptureRequest.CONTROL_AF_MODE, 0);
                this.q.setTag("Cancel focus");
                this.r.capture(this.q.build(), this.w, this.d);
            }
        } catch (CameraAccessException e2) {
            C0055x.Log(6, "Camera2: CameraAccessException " + e2);
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        try {
            if (this.h != 0) {
                float f2 = this.i;
                if (f2 >= 0.0f && f2 <= 1.0f) {
                    float f3 = this.j;
                    if (f3 >= 0.0f) {
                        if (f3 <= 1.0f) {
                            this.m = true;
                            int width = this.f.width();
                            int i2 = this.k;
                            int height = this.f.height();
                            int i3 = this.l;
                            int max = Math.max(this.g + 1, Math.min((int) ((((float) (width - (i2 * 2))) * this.i) + ((float) i2)), (this.f.width() - this.g) - 1));
                            int max2 = Math.max(this.g + 1, Math.min((int) (((1.0d - ((double) this.j)) * ((double) (height - (i3 * 2)))) + ((double) i3)), (this.f.height() - this.g) - 1));
                            CaptureRequest.Builder builder = this.q;
                            CaptureRequest.Key key = CaptureRequest.CONTROL_AF_REGIONS;
                            int i4 = this.g;
                            int i5 = i4 * 2;
                            builder.set(key, new MeteringRectangle[]{new MeteringRectangle(max - i4, max2 - i4, i5, i5, 999)});
                            this.q.set(CaptureRequest.CONTROL_AF_MODE, 1);
                            this.q.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
                            this.q.setTag("Focus");
                            this.r.capture(this.q.build(), this.w, this.d);
                            return;
                        }
                    }
                }
            }
            this.q.set(CaptureRequest.CONTROL_AF_MODE, 4);
            this.q.setTag("Regular");
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.setRepeatingRequest(this.q.build(), this.w, this.d);
            }
        } catch (CameraAccessException e2) {
            C0055x.Log(6, "Camera2: CameraAccessException " + e2);
        }
    }

    public final void a() {
        if (this.b != null) {
            i();
            b();
            this.w = null;
            this.v = null;
            this.u = null;
            Image image = this.p;
            if (image != null) {
                image.close();
                this.p = null;
            }
            ImageReader imageReader = this.o;
            if (imageReader != null) {
                imageReader.close();
                this.o = null;
            }
        }
        this.c.quit();
        try {
            this.c.join(4000);
            this.c = null;
            this.d = null;
        } catch (InterruptedException e2) {
            this.c.interrupt();
            C0055x.Log(6, "Camera2: Interrupted while waiting for the background thread to finish " + e2);
        }
    }

    public final boolean a(float f2, float f3) {
        if (this.h <= 0) {
            return false;
        }
        if (!this.m) {
            this.i = f2;
            this.j = f3;
            synchronized (this.s) {
                if (!(this.r == null || this.A == 2)) {
                    f();
                }
            }
            return true;
        }
        C0055x.Log(5, "Camera2: Setting manual focus point already started.");
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x0182 A[Catch:{ InterruptedException -> 0x0190 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x018a A[Catch:{ InterruptedException -> 0x0190 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.content.Context r23, int r24, int r25, int r26, int r27, int r28, android.view.Surface r29) {
        /*
            r22 = this;
            r1 = r22
            r0 = r27
            java.lang.String r2 = "Camera2: CameraAccessException "
            r3 = 6
            android.hardware.camera2.CameraManager r5 = B     // Catch:{ CameraAccessException -> 0x0268 }
            java.lang.String[] r6 = b(r23)     // Catch:{ CameraAccessException -> 0x0268 }
            r6 = r6[r24]     // Catch:{ CameraAccessException -> 0x0268 }
            android.hardware.camera2.CameraCharacteristics r5 = r5.getCameraCharacteristics(r6)     // Catch:{ CameraAccessException -> 0x0268 }
            android.hardware.camera2.CameraCharacteristics$Key r6 = android.hardware.camera2.CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL
            java.lang.Object r6 = r5.get(r6)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r7 = 2
            r8 = 5
            if (r6 != r7) goto L_0x0028
            java.lang.String r6 = "Camera2: only LEGACY hardware level is supported."
            com.unity3d.player.C0055x.Log(r8, r6)
        L_0x0028:
            android.hardware.camera2.CameraCharacteristics$Key r6 = android.hardware.camera2.CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP
            java.lang.Object r6 = r5.get(r6)
            android.hardware.camera2.params.StreamConfigurationMap r6 = (android.hardware.camera2.params.StreamConfigurationMap) r6
            r7 = 0
            if (r6 != 0) goto L_0x0039
            java.lang.String r6 = "Camera2: configuration map is not available."
            com.unity3d.player.C0055x.Log(r3, r6)
            goto L_0x0046
        L_0x0039:
            r9 = 35
            android.util.Size[] r6 = r6.getOutputSizes(r9)
            if (r6 == 0) goto L_0x0046
            int r9 = r6.length
            if (r9 != 0) goto L_0x0045
            goto L_0x0046
        L_0x0045:
            r7 = r6
        L_0x0046:
            if (r7 == 0) goto L_0x0266
            int r6 = r7.length
            if (r6 != 0) goto L_0x004d
            goto L_0x0266
        L_0x004d:
            r6 = r25
            double r9 = (double) r6
            r6 = r26
            double r11 = (double) r6
            r6 = 0
            r13 = 0
            r15 = 0
            r16 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
        L_0x005b:
            int r14 = r7.length
            if (r6 >= r14) goto L_0x0096
            r14 = r7[r6]
            int r14 = r14.getWidth()
            r18 = r7[r6]
            int r3 = r18.getHeight()
            r19 = r5
            double r4 = (double) r14
            double r4 = r9 / r4
            double r4 = java.lang.Math.log(r4)
            double r4 = java.lang.Math.abs(r4)
            r20 = r9
            double r8 = (double) r3
            double r8 = r11 / r8
            double r8 = java.lang.Math.log(r8)
            double r8 = java.lang.Math.abs(r8)
            double r8 = r8 + r4
            int r4 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r4 >= 0) goto L_0x008d
            r13 = r3
            r16 = r8
            r15 = r14
        L_0x008d:
            int r6 = r6 + 1
            r5 = r19
            r9 = r20
            r3 = 6
            r8 = 5
            goto L_0x005b
        L_0x0096:
            r19 = r5
            android.graphics.Rect r3 = new android.graphics.Rect
            r4 = 0
            r3.<init>(r4, r4, r15, r13)
            r1.e = r3
            android.hardware.camera2.CameraCharacteristics$Key r3 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES
            r4 = r19
            java.lang.Object r3 = r4.get(r3)
            android.util.Range[] r3 = (android.util.Range[]) r3
            if (r3 == 0) goto L_0x025e
            int r5 = r3.length
            if (r5 != 0) goto L_0x00b1
            goto L_0x025e
        L_0x00b1:
            r5 = -1
            r6 = 0
            r13 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
        L_0x00b8:
            int r7 = r3.length
            if (r6 >= r7) goto L_0x0100
            r7 = r3[r6]
            java.lang.Comparable r7 = r7.getLower()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r8 = r3[r6]
            java.lang.Comparable r8 = r8.getUpper()
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            float r9 = (float) r0
            r10 = 1036831949(0x3dcccccd, float:0.1)
            float r11 = r9 + r10
            float r12 = (float) r7
            int r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r11 <= 0) goto L_0x00e5
            float r9 = r9 - r10
            float r10 = (float) r8
            int r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r9 >= 0) goto L_0x00e5
            goto L_0x0121
        L_0x00e5:
            int r7 = r0 - r7
            int r7 = java.lang.Math.abs(r7)
            int r8 = r0 - r8
            int r8 = java.lang.Math.abs(r8)
            int r7 = java.lang.Math.min(r7, r8)
            float r7 = (float) r7
            double r7 = (double) r7
            int r9 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r9 >= 0) goto L_0x00fd
            r5 = r6
            r13 = r7
        L_0x00fd:
            int r6 = r6 + 1
            goto L_0x00b8
        L_0x0100:
            r6 = r3[r5]
            java.lang.Comparable r6 = r6.getUpper()
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            if (r0 <= r6) goto L_0x0115
            r0 = r3[r5]
            java.lang.Comparable r0 = r0.getUpper()
            goto L_0x011b
        L_0x0115:
            r0 = r3[r5]
            java.lang.Comparable r0 = r0.getLower()
        L_0x011b:
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
        L_0x0121:
            android.util.Range r3 = new android.util.Range
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r3.<init>(r5, r0)
            r1.n = r3
            java.util.concurrent.Semaphore r0 = D     // Catch:{ InterruptedException -> 0x0249 }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0249 }
            r5 = 4
            boolean r0 = r0.tryAcquire(r5, r3)     // Catch:{ InterruptedException -> 0x0249 }
            if (r0 != 0) goto L_0x0144
            java.lang.String r0 = "Camera2: Timeout waiting to lock camera for opening."
            r2 = 5
            com.unity3d.player.C0055x.Log(r2, r0)     // Catch:{ InterruptedException -> 0x0249 }
            r2 = 0
            return r2
        L_0x0144:
            android.hardware.camera2.CameraManager r0 = B     // Catch:{ CameraAccessException -> 0x0231, SecurityException -> 0x0165, IllegalArgumentException -> 0x0154 }
            java.lang.String[] r3 = b(r23)     // Catch:{ CameraAccessException -> 0x0231, SecurityException -> 0x0165, IllegalArgumentException -> 0x0154 }
            r3 = r3[r24]     // Catch:{ CameraAccessException -> 0x0231, SecurityException -> 0x0165, IllegalArgumentException -> 0x0154 }
            android.hardware.camera2.CameraDevice$StateCallback r7 = r1.x     // Catch:{ CameraAccessException -> 0x0231, SecurityException -> 0x0165, IllegalArgumentException -> 0x0154 }
            android.os.Handler r8 = r1.d     // Catch:{ CameraAccessException -> 0x0231, SecurityException -> 0x0165, IllegalArgumentException -> 0x0154 }
            r0.openCamera(r3, r7, r8)     // Catch:{ CameraAccessException -> 0x0231, SecurityException -> 0x0165, IllegalArgumentException -> 0x0154 }
            goto L_0x0178
        L_0x0154:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Camera2: IllegalArgumentException "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r2 = 6
            goto L_0x0175
        L_0x0165:
            r0 = move-exception
            r2 = 6
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r7 = "Camera2: SecurityException "
            r3.<init>(r7)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
        L_0x0175:
            com.unity3d.player.C0055x.Log(r2, r0)
        L_0x0178:
            java.util.concurrent.Semaphore r0 = D     // Catch:{ InterruptedException -> 0x0190 }
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0190 }
            boolean r0 = r0.tryAcquire(r5, r2)     // Catch:{ InterruptedException -> 0x0190 }
            if (r0 != 0) goto L_0x018a
            java.lang.String r0 = "Camera2: Timeout waiting to open camera."
            r2 = 5
            com.unity3d.player.C0055x.Log(r2, r0)     // Catch:{ InterruptedException -> 0x0190 }
            r2 = 0
            return r2
        L_0x018a:
            java.util.concurrent.Semaphore r0 = D     // Catch:{ InterruptedException -> 0x0190 }
            r0.release()     // Catch:{ InterruptedException -> 0x0190 }
            goto L_0x01a3
        L_0x0190:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Camera2: Interrupted while waiting to open camera "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r2 = 6
            com.unity3d.player.C0055x.Log(r2, r0)
        L_0x01a3:
            r2 = r28
            r1.t = r2
            r2 = r29
            r1.v = r2
            android.hardware.camera2.CameraCharacteristics$Key r0 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF
            java.lang.Object r0 = r4.get(r0)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r1.h = r0
            if (r0 <= 0) goto L_0x0229
            android.hardware.camera2.CameraCharacteristics$Key r0 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE
            java.lang.Object r0 = r4.get(r0)
            android.graphics.Rect r0 = (android.graphics.Rect) r0
            r1.f = r0
            int r0 = r0.width()
            float r0 = (float) r0
            android.graphics.Rect r2 = r1.f
            int r2 = r2.height()
            float r2 = (float) r2
            float r0 = r0 / r2
            android.graphics.Rect r2 = r1.e
            int r2 = r2.width()
            float r2 = (float) r2
            android.graphics.Rect r3 = r1.e
            int r3 = r3.height()
            float r3 = (float) r3
            float r2 = r2 / r3
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = 0
            if (r0 <= 0) goto L_0x01ff
            r1.k = r4
            android.graphics.Rect r0 = r1.f
            int r0 = r0.height()
            float r0 = (float) r0
            android.graphics.Rect r4 = r1.f
            int r4 = r4.width()
            float r4 = (float) r4
            float r4 = r4 / r2
            float r0 = r0 - r4
            float r0 = r0 / r3
            int r0 = (int) r0
            r1.l = r0
            goto L_0x0215
        L_0x01ff:
            r1.l = r4
            android.graphics.Rect r0 = r1.f
            int r0 = r0.width()
            float r0 = (float) r0
            android.graphics.Rect r4 = r1.f
            int r4 = r4.height()
            float r4 = (float) r4
            float r4 = r4 * r2
            float r0 = r0 - r4
            float r0 = r0 / r3
            int r0 = (int) r0
            r1.k = r0
        L_0x0215:
            android.graphics.Rect r0 = r1.f
            int r0 = r0.width()
            android.graphics.Rect r2 = r1.f
            int r2 = r2.height()
            int r0 = java.lang.Math.min(r0, r2)
            int r0 = r0 / 20
            r1.g = r0
        L_0x0229:
            android.hardware.camera2.CameraDevice r0 = r1.b
            if (r0 == 0) goto L_0x022f
            r4 = 1
            goto L_0x0230
        L_0x022f:
            r4 = 0
        L_0x0230:
            return r4
        L_0x0231:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2 = 6
            com.unity3d.player.C0055x.Log(r2, r0)
            java.util.concurrent.Semaphore r0 = D
            r0.release()
        L_0x0247:
            r2 = 0
            return r2
        L_0x0249:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Camera2: Interrupted while trying to lock camera for opening "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r2 = 6
            com.unity3d.player.C0055x.Log(r2, r0)
            r3 = 0
            return r3
        L_0x025e:
            r2 = 6
            r3 = 0
            java.lang.String r0 = "Camera2: target FPS ranges are not avialable."
            com.unity3d.player.C0055x.Log(r2, r0)
            return r3
        L_0x0266:
            r3 = 0
            return r3
        L_0x0268:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2 = 6
            com.unity3d.player.C0055x.Log(r2, r0)
            goto L_0x0247
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0047t.a(android.content.Context, int, int, int, int, int, android.view.Surface):boolean");
    }

    public final Rect c() {
        return this.e;
    }

    public final void d() {
        synchronized (this.s) {
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.stopRepeating();
                    this.A = 2;
                } catch (CameraAccessException e2) {
                    C0055x.Log(6, "Camera2: CameraAccessException " + e2);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0066 A[Catch:{ CameraAccessException -> 0x0092 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0078 A[Catch:{ CameraAccessException -> 0x0092 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void h() {
        /*
            r5 = this;
            int r0 = r5.t
            r1 = 2
            if (r0 == 0) goto L_0x0034
            android.view.Surface r0 = r5.v
            if (r0 != 0) goto L_0x0061
            android.graphics.SurfaceTexture r0 = new android.graphics.SurfaceTexture
            int r2 = r5.t
            r0.<init>(r2)
            r5.u = r0
            android.graphics.Rect r2 = r5.e
            int r2 = r2.width()
            android.graphics.Rect r3 = r5.e
            int r3 = r3.height()
            r0.setDefaultBufferSize(r2, r3)
            android.graphics.SurfaceTexture r0 = r5.u
            android.graphics.SurfaceTexture$OnFrameAvailableListener r2 = r5.z
            android.os.Handler r3 = r5.d
            r0.setOnFrameAvailableListener(r2, r3)
            android.view.Surface r0 = new android.view.Surface
            android.graphics.SurfaceTexture r2 = r5.u
            r0.<init>(r2)
        L_0x0031:
            r5.v = r0
            goto L_0x0061
        L_0x0034:
            android.view.Surface r0 = r5.v
            if (r0 != 0) goto L_0x0061
            android.media.ImageReader r0 = r5.o
            if (r0 != 0) goto L_0x0061
            android.graphics.Rect r0 = r5.e
            int r0 = r0.width()
            android.graphics.Rect r2 = r5.e
            int r2 = r2.height()
            r3 = 35
            android.media.ImageReader r0 = android.media.ImageReader.newInstance(r0, r2, r3, r1)
            r5.o = r0
            android.media.ImageReader$OnImageAvailableListener r2 = r5.y
            android.os.Handler r3 = r5.d
            r0.setOnImageAvailableListener(r2, r3)
            r0 = 0
            r5.p = r0
            android.media.ImageReader r0 = r5.o
            android.view.Surface r0 = r0.getSurface()
            goto L_0x0031
        L_0x0061:
            android.hardware.camera2.CameraCaptureSession r0 = r5.r     // Catch:{ CameraAccessException -> 0x0092 }
            r2 = 1
            if (r0 == 0) goto L_0x0078
            int r3 = r5.A     // Catch:{ CameraAccessException -> 0x0092 }
            if (r3 != r1) goto L_0x008f
            android.hardware.camera2.CaptureRequest$Builder r1 = r5.q     // Catch:{ CameraAccessException -> 0x0092 }
            android.hardware.camera2.CaptureRequest r1 = r1.build()     // Catch:{ CameraAccessException -> 0x0092 }
            android.hardware.camera2.CameraCaptureSession$CaptureCallback r3 = r5.w     // Catch:{ CameraAccessException -> 0x0092 }
            android.os.Handler r4 = r5.d     // Catch:{ CameraAccessException -> 0x0092 }
            r0.setRepeatingRequest(r1, r3, r4)     // Catch:{ CameraAccessException -> 0x0092 }
            goto L_0x008f
        L_0x0078:
            android.hardware.camera2.CameraDevice r0 = r5.b     // Catch:{ CameraAccessException -> 0x0092 }
            android.view.Surface[] r1 = new android.view.Surface[r2]     // Catch:{ CameraAccessException -> 0x0092 }
            android.view.Surface r3 = r5.v     // Catch:{ CameraAccessException -> 0x0092 }
            r4 = 0
            r1[r4] = r3     // Catch:{ CameraAccessException -> 0x0092 }
            java.util.List r1 = java.util.Arrays.asList(r1)     // Catch:{ CameraAccessException -> 0x0092 }
            com.unity3d.player.p r3 = new com.unity3d.player.p     // Catch:{ CameraAccessException -> 0x0092 }
            r3.<init>(r5)     // Catch:{ CameraAccessException -> 0x0092 }
            android.os.Handler r4 = r5.d     // Catch:{ CameraAccessException -> 0x0092 }
            r0.createCaptureSession(r1, r3, r4)     // Catch:{ CameraAccessException -> 0x0092 }
        L_0x008f:
            r5.A = r2     // Catch:{ CameraAccessException -> 0x0092 }
            goto L_0x00a5
        L_0x0092:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Camera2: CameraAccessException "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r1 = 6
            com.unity3d.player.C0055x.Log(r1, r0)
        L_0x00a5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0047t.h():void");
    }

    public final void i() {
        synchronized (this.s) {
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.abortCaptures();
                } catch (CameraAccessException e2) {
                    C0055x.Log(6, "Camera2: CameraAccessException " + e2);
                }
                this.r.close();
                this.r = null;
                this.A = 3;
            }
        }
    }
}
