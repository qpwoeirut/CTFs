package com.unity3d.player;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Looper;
import android.provider.Settings;
import android.view.Surface;
import android.view.WindowInsets;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class UnityPlayerForActivityOrService extends UnityPlayer {
    /* access modifiers changed from: private */
    public boolean mMainDisplayOverride;
    private C mOnHandleFocusListener;
    private boolean mProcessKillRequested;
    /* access modifiers changed from: private */
    public Q mSoftInput;
    private long mSoftInputTimeoutMilliSeconds;
    private int m_IsNoWindowMode;
    private B0 m_MainThread;

    public enum MemoryUsage {
        Low(1),
        Medium(2),
        High(3),
        Critical(4);
        
        public final int value;

        private MemoryUsage(int i) {
            this.value = i;
        }
    }

    public enum SynchronizationTimeout {
        Pause(0),
        SurfaceDetach(1),
        Destroy(2);
        
        private int m_TimeoutMilliseconds;
        final int value;

        private SynchronizationTimeout(int i) {
            this.value = i;
            this.m_TimeoutMilliseconds = 2000;
        }

        public static void setTimeoutForAll(int i) {
            for (SynchronizationTimeout timeout : (SynchronizationTimeout[]) SynchronizationTimeout.class.getEnumConstants()) {
                timeout.setTimeout(i);
            }
        }

        public int getTimeout() {
            return this.m_TimeoutMilliseconds;
        }

        public void setTimeout(int i) {
            this.m_TimeoutMilliseconds = i;
        }
    }

    public UnityPlayerForActivityOrService(Context context) {
        this(context, (IUnityPlayerLifecycleEvents) null);
    }

    public UnityPlayerForActivityOrService(Context context, IUnityPlayerLifecycleEvents iUnityPlayerLifecycleEvents) {
        super(context, C0049u.ActivityOrService, iUnityPlayerLifecycleEvents);
        this.m_MainThread = new B0(this);
        this.mMainDisplayOverride = false;
        this.mSoftInput = null;
        this.m_IsNoWindowMode = -1;
        this.mProcessKillRequested = true;
        this.mSoftInputTimeoutMilliSeconds = 1000;
        C0053w wVar = new C0053w(context, this);
        wVar.setOnApplyWindowInsetsListener(new C0014e1(this));
        initialize(wVar);
        this.m_MainThread.start();
    }

    /* access modifiers changed from: private */
    public void dismissSoftInput() {
        Q q = this.mSoftInput;
        if (q != null) {
            q.b();
            nativeReportKeyboardConfigChanged();
        }
    }

    private long getSoftInputTimeout() {
        return Math.round(((double) this.mSoftInputTimeoutMilliSeconds) * ((double) Math.max(1.0f, Settings.System.getFloat(this.mContext.getContentResolver(), "animator_duration_scale", 0.0f))));
    }

    private final native boolean nativeDone();

    private final native boolean nativeGetNoWindowMode();

    private final native void nativeMemoryUsageChanged(int i);

    /* access modifiers changed from: private */
    public final native void nativeOnApplyWindowInsets(WindowInsets windowInsets);

    /* access modifiers changed from: private */
    public final native boolean nativePause();

    /* access modifiers changed from: private */
    public final native void nativeRecreateGfxState(int i, Surface surface);

    /* access modifiers changed from: private */
    public final native void nativeReportKeyboardConfigChanged();

    /* access modifiers changed from: private */
    public final native void nativeResume();

    /* access modifiers changed from: private */
    public final native void nativeSendSurfaceChangedEvent();

    /* access modifiers changed from: private */
    public final native void nativeSetInputArea(int i, int i2, int i3, int i4);

    /* access modifiers changed from: private */
    public final native void nativeSetInputSelection(int i, int i2);

    /* access modifiers changed from: private */
    public final native void nativeSetInputString(String str);

    /* access modifiers changed from: private */
    public final native void nativeSetKeyboardIsVisible(boolean z);

    /* access modifiers changed from: private */
    public final native void nativeSoftInputCanceled();

    /* access modifiers changed from: private */
    public final native void nativeSoftInputClosed();

    /* access modifiers changed from: private */
    public final native void nativeSoftInputLostFocus();

    /* access modifiers changed from: private */
    public void queueDestroy() {
        C0055x.Log(4, "Queue Destroy");
        runOnUiThread(new C0011d1(this));
    }

    private void raiseFocusListener(boolean z) {
        C c = this.mOnHandleFocusListener;
        if (c != null) {
            U0 u0 = ((S0) c).a;
            u0.b = true;
            if (u0.a) {
                u0.c.release();
            }
        }
    }

    private boolean updateDisplayInternal(int i, Surface surface) {
        if (!isNativeInitialized()) {
            return false;
        }
        Semaphore semaphore = new Semaphore(0);
        j1 j1Var = new j1(this, i, surface, semaphore);
        if (i == 0) {
            B0 b0 = this.m_MainThread;
            if (surface == null) {
                b0.d(j1Var);
            } else {
                b0.b(j1Var);
            }
        } else {
            j1Var.run();
        }
        if (surface != null || i != 0) {
            return true;
        }
        try {
            SynchronizationTimeout synchronizationTimeout = SynchronizationTimeout.SurfaceDetach;
            if (semaphore.tryAcquire((long) synchronizationTimeout.getTimeout(), TimeUnit.MILLISECONDS)) {
                return true;
            }
            C0055x.Log(5, "Timeout (" + synchronizationTimeout.getTimeout() + " ms) while trying detaching primary window.");
            return true;
        } catch (InterruptedException unused) {
            C0055x.Log(5, "UI thread got interrupted while trying to detach the primary window from the Unity Engine.");
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void cleanupResourcesForDestroy() {
        this.m_MainThread.c();
        try {
            this.m_MainThread.join((long) SynchronizationTimeout.Destroy.getTimeout());
        } catch (InterruptedException unused) {
            this.m_MainThread.interrupt();
        }
        if (v1.d()) {
            getFrameLayout().removeAllViews();
        }
        if (this.mProcessKillRequested) {
            this.m_UnityPlayerLifecycleEvents.onUnityPlayerQuitted();
            kill();
        }
    }

    public void configurationChanged(Configuration configuration) {
        super.configurationChanged(configuration);
        if (isNativeInitialized()) {
            invokeOnMainThread((Runnable) new C0023h1(this, new Configuration(configuration)));
        }
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.mMainDisplayOverride = surface != null;
            runOnUiThread(new k1(this));
        }
        return updateDisplayInternal(i, surface);
    }

    /* access modifiers changed from: package-private */
    public boolean getHaveAndroidWindowSupport() {
        if (this.m_IsNoWindowMode == -1) {
            this.m_IsNoWindowMode = nativeGetNoWindowMode() ? 1 : 0;
        }
        return this.m_IsNoWindowMode == 1;
    }

    public C0006c getSurfaceView() {
        return getView().b();
    }

    public C0058y0 getView() {
        return ((C0053w) getFrameLayout()).a();
    }

    /* access modifiers changed from: package-private */
    public boolean handleFocus(boolean z) {
        Q q;
        if (!this.mState.a() || ((q = this.mSoftInput) != null && !q.c())) {
            raiseFocusListener(z);
            return false;
        }
        B0 b0 = this.m_MainThread;
        if (z) {
            b0.a();
        } else {
            b0.b();
        }
        raiseFocusListener(z);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void hidePreservedContent() {
        runOnUiThread(new m1(this));
    }

    /* access modifiers changed from: protected */
    public void hideSoftInput() {
        if (this.mSoftInput != null) {
            reportSoftInputArea(new Rect());
            reportSoftInputIsVisible(false);
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                dismissSoftInput();
                this.mSoftInput = null;
                return;
            }
            Semaphore semaphore = new Semaphore(0);
            postOnUiThread(new U0(this, semaphore, this));
            try {
                if (!semaphore.tryAcquire(getSoftInputTimeout(), TimeUnit.MILLISECONDS)) {
                    C0055x.Log(6, "Timeout (" + getSoftInputTimeout() + " ms) while waiting softinput hiding operation.");
                }
            } catch (InterruptedException unused) {
                C0055x.Log(6, "UI thread got interrupted while waiting softinput hiding operation.");
            } catch (Throwable th) {
                this.mSoftInput = null;
                throw th;
            }
            this.mSoftInput = null;
        }
    }

    /* access modifiers changed from: package-private */
    public final native void nativeConfigurationChanged(Configuration configuration);

    /* access modifiers changed from: package-private */
    public final native void nativeFocusChanged(boolean z);

    /* access modifiers changed from: package-private */
    public final native void nativeOrientationChanged(int i, int i2);

    /* access modifiers changed from: package-private */
    public final native boolean nativeRender();

    /* access modifiers changed from: package-private */
    public void onOrientationChanged(int i, int i2) {
        this.m_MainThread.a(this.mNaturalOrientation, i2);
    }

    public void onTrimMemory(MemoryUsage memoryUsage) {
        if (v1.d()) {
            nativeMemoryUsageChanged(memoryUsage.value);
        }
    }

    /* access modifiers changed from: package-private */
    public void pauseUnity() {
        super.pauseUnity();
        reportSoftInputStr((String) null, 1, true);
        this.mState.c(false);
        this.mState.e(true);
        if (v1.d()) {
            Semaphore semaphore = new Semaphore(0);
            this.m_MainThread.a(isFinishing() ? new C0017f1(this, semaphore) : new C0020g1(this, semaphore));
            try {
                SynchronizationTimeout synchronizationTimeout = SynchronizationTimeout.Pause;
                if (!semaphore.tryAcquire((long) synchronizationTimeout.getTimeout(), TimeUnit.MILLISECONDS)) {
                    C0055x.Log(5, "Timeout (" + synchronizationTimeout.getTimeout() + " ms) while trying to pause the Unity Engine.");
                }
            } catch (InterruptedException unused) {
                C0055x.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
            }
        }
        if (this.m_AddPhoneCallListener) {
            this.m_TelephonyManager.listen(this.m_PhoneCallListener, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void reportSoftInputArea(Rect rect) {
        invokeOnMainThread((Runnable) new C0005b1(this, rect));
    }

    /* access modifiers changed from: protected */
    public void reportSoftInputIsVisible(boolean z) {
        invokeOnMainThread((Runnable) new C0008c1(this, z));
    }

    /* access modifiers changed from: protected */
    public void reportSoftInputSelection(int i, int i2) {
        invokeOnMainThread((Runnable) new C0002a1(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public void reportSoftInputStr(String str, int i, boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        invokeOnMainThread((Runnable) new Z0(this, z, str, i));
    }

    /* access modifiers changed from: package-private */
    public void resumeUnity() {
        super.resumeUnity();
        invokeOnMainThread((Runnable) new l1(this));
        this.m_MainThread.d();
    }

    public boolean runningOnMainThread() {
        return Thread.currentThread() == this.m_MainThread;
    }

    /* access modifiers changed from: package-private */
    public void sendSurfaceChangedEvent() {
        if (isNativeInitialized()) {
            this.m_MainThread.c(new C0026i1(this));
        }
    }

    /* access modifiers changed from: protected */
    public void setCharacterLimit(int i) {
        runOnUiThread(new W0(this, i));
    }

    /* access modifiers changed from: protected */
    public void setHideInputField(boolean z) {
        runOnUiThread(new X0(this, z));
    }

    public void setMainSurfaceViewAspectRatio(float f) {
        runOnUiThread(new n1(this, f));
    }

    public void setOnHandleFocusListener(C c) {
        this.mOnHandleFocusListener = c;
    }

    /* access modifiers changed from: protected */
    public void setSelection(int i, int i2) {
        runOnUiThread(new Y0(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public void setSoftInputStr(String str) {
        runOnUiThread(new V0(this, str));
    }

    /* access modifiers changed from: protected */
    public void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i2, boolean z5, boolean z6) {
        Semaphore semaphore = new Semaphore(0);
        String str3 = "Timeout (";
        p1 p1Var = r1;
        p1 p1Var2 = new p1(this, this, str, i, z, z2, z3, z4, str2, i2, z5, z6, semaphore);
        postOnUiThread(p1Var);
        try {
            if (!semaphore.tryAcquire(getSoftInputTimeout(), TimeUnit.MILLISECONDS)) {
                C0055x.Log(6, str3 + getSoftInputTimeout() + " ms) while waiting softinput showing operation.");
            }
        } catch (InterruptedException unused) {
            C0055x.Log(6, "UI thread got interrupted while waiting softinput showing operation.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        this.mProcessKillRequested = nativeDone();
        super.shutdown();
    }

    /* access modifiers changed from: package-private */
    public void updateGLDisplay(int i, Surface surface) {
        if (!this.mMainDisplayOverride) {
            updateDisplayInternal(i, surface);
        }
    }
}
