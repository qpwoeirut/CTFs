package com.unity3d.player;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.InputEvent;
import android.view.OrientationEventListener;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.unity3d.player.a.a;
import com.unity3d.player.a.d;
import com.unity3d.player.a.g;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.fmod.FmodAndroidAudioManager;

public abstract class UnityPlayer implements IUnityPlayerLifecycleEvents {
    private static final String ARCORE_ENABLE_METADATA_NAME = "unity.arcore-enable";
    private static final String AUTO_REPORT_FULLY_DRAWN_ENABLE_METADATA_NAME = "unity.auto-report-fully-drawn";
    private static final String AUTO_SET_GAME_STATE_ENABLE_METADATA_NAME = "unity.auto-set-game-state";
    private static final String LAUNCH_FULLSCREEN = "unity.launch-fullscreen";
    private static final String SPLASH_ENABLE_METADATA_NAME = "unity.splash-enable";
    private static final String SPLASH_MODE_METADATA_NAME = "unity.splash-mode";
    public static Activity currentActivity;
    public static Context currentContext;
    Activity mActivity;
    Context mContext;
    private C0049u mContextType;
    Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public int mInitialScreenOrientation = -1;
    private boolean mIsFullscreen = true;
    int mNaturalOrientation;
    private OrientationEventListener mOrientationListener = null;
    boolean mQuitting;
    v1 mState = new v1();
    /* access modifiers changed from: private */
    public H1 mVideoPlayerProxy;
    private GoogleARCoreApi m_ARCoreApi = null;
    private UnityAccessibilityDelegate m_AccessibilityDelegate = null;
    boolean m_AddPhoneCallListener = false;
    private AudioVolumeHandler m_AudioVolumeHandler = null;
    private Camera2Wrapper m_Camera2Wrapper = null;
    private ClipboardManager m_ClipboardManager;
    private C0052v0 m_Cursor = null;
    private M0 m_FakeListener = new M0();
    private FmodAndroidAudioManager m_FmodAndroidAudioManager = null;
    private FrameLayout m_FrameLayout;
    private HFPStatus m_HFPStatus = null;
    private final ConcurrentLinkedQueue m_MainThreadJobs = new ConcurrentLinkedQueue();
    private C0057y m_NetworkConnectivity = null;
    private A m_OnBackPressedDispatcher = null;
    private OrientationLockListener m_OrientationLockListener = null;
    private HashMap m_PermissionRequests;
    P0 m_PhoneCallListener = new P0(this);
    /* access modifiers changed from: private */
    public g m_SplashScreen;
    TelephonyManager m_TelephonyManager;
    private Thread m_UIThread = Thread.currentThread();
    protected IUnityPlayerLifecycleEvents m_UnityPlayerLifecycleEvents = null;
    Window m_Window;
    private Configuration prevConfig;

    static {
        new C0054w0().a();
    }

    protected UnityPlayer(Context context, C0049u uVar, IUnityPlayerLifecycleEvents iUnityPlayerLifecycleEvents) {
        this.mContext = context;
        this.mContextType = uVar;
        this.m_UnityPlayerLifecycleEvents = iUnityPlayerLifecycleEvents == null ? this : iUnityPlayerLifecycleEvents;
        C0054w0.a(getUnityNativeLibraryPath(context));
        currentContext = context;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            this.mActivity = activity;
            currentActivity = activity;
            this.mInitialScreenOrientation = activity.getRequestedOrientation();
        }
    }

    private void EarlyEnableFullScreenIfEnabled() {
        View decorView;
        Activity activity = this.mActivity;
        if (!(activity == null || activity.getWindow() == null || ((!getLaunchFullscreen() && !this.mActivity.getIntent().getBooleanExtra("android.intent.extra.VR_LAUNCH", false)) || (decorView = this.mActivity.getWindow().getDecorView()) == null))) {
            decorView.setSystemUiVisibility(7);
        }
        C0051v.a(this.mActivity);
    }

    private String GetGlViewContentDescription(Context context) {
        return context.getResources().getString(context.getResources().getIdentifier("game_view_content_description", TypedValues.Custom.S_STRING, context.getPackageName()));
    }

    private boolean IsWindowTranslucent() {
        Activity activity = this.mActivity;
        if (activity == null) {
            return false;
        }
        TypedArray obtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{16842840});
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z;
    }

    public static void UnitySendMessage(String str, String str2, String str3) {
        byte[] bArr;
        if (!v1.d()) {
            C0055x.Log(5, "Native libraries not loaded - dropping message for " + str + "." + str2);
            return;
        }
        if (str3 == null) {
            bArr = null;
        } else {
            try {
                bArr = str3.getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
                return;
            }
        }
        nativeUnitySendMessage(str, str2, bArr);
    }

    private void developmentPlayerInitialize() {
    }

    private boolean getARCoreEnabled() {
        try {
            return getApplicationInfo().metaData.getBoolean(ARCORE_ENABLE_METADATA_NAME);
        } catch (Exception unused) {
            return false;
        }
    }

    private ActivityInfo getActivityInfo() {
        return this.mActivity.getPackageManager().getActivityInfo(this.mActivity.getComponentName(), 128);
    }

    private boolean getLaunchFullscreen() {
        try {
            return getApplicationInfo().metaData.getBoolean(LAUNCH_FULLSCREEN);
        } catch (Exception unused) {
            return false;
        }
    }

    private int getNaturalOrientation(int i) {
        int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        if ((rotation == 0 || rotation == 2) && i == 2) {
            return 0;
        }
        return ((rotation == 1 || rotation == 3) && i == 1) ? 0 : 1;
    }

    private String getProcessName() {
        int myPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.mContext.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.pid == myPid) {
                return next.processName;
            }
        }
        return null;
    }

    private float getScreenBrightness() {
        Window window = this.m_Window;
        if (window == null) {
            return 1.0f;
        }
        float f = window.getAttributes().screenBrightness;
        if (f >= 0.0f) {
            return f;
        }
        int i = Settings.System.getInt(getContext().getContentResolver(), "screen_brightness", 255);
        return PlatformSupport.PIE_SUPPORT ? (float) Math.max(0.0d, Math.min(1.0d, ((Math.log((double) i) * 19.811d) - 9.411d) / 100.0d)) : ((float) i) / 255.0f;
    }

    private static String getUnityNativeLibraryPath(Context context) {
        return context.getApplicationInfo().nativeLibraryDir;
    }

    private void hideStatusBar() {
        Activity activity = this.mActivity;
        if (activity != null) {
            activity.getWindow().setFlags(1024, 1024);
        }
    }

    private final native void initJni(Context context, int i);

    /* access modifiers changed from: private */
    public void invokeOnMainThread(Q0 q0) {
        if (!isFinishing()) {
            invokeOnMainThread((Runnable) q0);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        return logLoadLibMainError(r0, r2.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0015, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        java.lang.System.loadLibrary("main");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0015 A[ExcHandler: SecurityException (r2v7 'e' java.lang.SecurityException A[CUSTOM_DECLARE]), Splitter:B:1:0x0011] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String loadNative(java.lang.String r2) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            java.lang.String r1 = "/libmain.so"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.System.load(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0017, SecurityException -> 0x0015 }
            goto L_0x001c
        L_0x0015:
            r2 = move-exception
            goto L_0x002f
        L_0x0017:
            java.lang.String r1 = "main"
            java.lang.System.loadLibrary(r1)     // Catch:{ UnsatisfiedLinkError -> 0x0038, SecurityException -> 0x0015 }
        L_0x001c:
            boolean r2 = com.unity3d.player.NativeLoader.load(r2)
            if (r2 == 0) goto L_0x0028
            com.unity3d.player.v1.e()
            java.lang.String r2 = ""
            return r2
        L_0x0028:
            r2 = 6
            java.lang.String r0 = "NativeLoader.load failure, Unity libraries were not loaded."
            com.unity3d.player.C0055x.Log(r2, r0)
            return r0
        L_0x002f:
            java.lang.String r2 = r2.toString()
            java.lang.String r2 = logLoadLibMainError(r0, r2)
            return r2
        L_0x0038:
            r2 = move-exception
            java.lang.String r2 = r2.toString()
            java.lang.String r2 = logLoadLibMainError(r0, r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityPlayer.loadNative(java.lang.String):java.lang.String");
    }

    private static String logLoadLibMainError(String str, String str2) {
        String str3 = "Failed to load 'libmain.so'\n\n" + str2;
        C0055x.Log(6, str3);
        return str3;
    }

    private final native void nativeApplicationUnload();

    private final native void nativeHidePreservedContent();

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    /* access modifiers changed from: private */
    public final native boolean nativeIsAutorotationOn();

    /* access modifiers changed from: private */
    public final native void nativeMuteMasterAudio(boolean z);

    private final native void nativeRestartActivityIndicator();

    /* access modifiers changed from: private */
    public static native void nativeSetLaunchURL(String str);

    private static native void nativeUnitySendMessage(String str, String str2, byte[] bArr);

    private void pauseJavaAndCallUnloadCallback() {
        runOnUiThread(new C0(this));
    }

    static native void permissionResponseToNative(long j, boolean z);

    private static void preloadJavaPlugins() {
        try {
            Class.forName("com.unity3d.JavaPluginPreloader");
        } catch (ClassNotFoundException unused) {
        } catch (LinkageError e) {
            C0055x.Log(6, "Java class preloading failed: " + e.getMessage());
        }
    }

    private void releasePointerCapture() {
        this.m_Cursor.a();
    }

    private void requestPointerCapture() {
        this.m_Cursor.b();
    }

    private void requestUserAuthorization(String str, long j) {
        Activity activity;
        if (str != null && !str.isEmpty() && (activity = this.mActivity) != null) {
            UnityPermissions.requestUserPermissions(activity, new String[]{str}, j != 0 ? new O0(this, j) : null);
        }
    }

    private boolean runningOnUIThread() {
        return Thread.currentThread() == this.m_UIThread;
    }

    private void setBackButtonLeavesApp(boolean z) {
        A a = this.m_OnBackPressedDispatcher;
        if (a == null) {
            return;
        }
        if (!z) {
            a.registerOnBackPressedCallback();
        } else {
            a.unregisterOnBackPressedCallback();
        }
    }

    private void setScreenBrightness(float f) {
        float max = Math.max(0.04f, f);
        if (this.m_Window != null && getScreenBrightness() != max) {
            runOnUiThread(new L0(this, max));
        }
    }

    private void swapViews(View view, View view2) {
        boolean z;
        ViewParent parent;
        if (!this.mState.b()) {
            setupUnityToBePaused();
            z = true;
        } else {
            z = false;
        }
        FrameLayout frameLayout = getFrameLayout();
        if (!(view == null || (parent = view.getParent()) == frameLayout)) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view);
            }
            frameLayout.addView(view);
            frameLayout.bringChildToFront(view);
            view.setVisibility(0);
        }
        if (view2 != null && view2.getParent() == frameLayout) {
            view2.setVisibility(8);
            frameLayout.removeView(view2);
        }
        if (z) {
            setupUnityToBeResumed();
        }
    }

    private static void unloadNative() {
        if (v1.d()) {
            if (NativeLoader.unload()) {
                v1.f();
                return;
            }
            throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
        }
    }

    public synchronized void addPermissionRequest(PermissionRequest permissionRequest) {
        Integer valueOf;
        if (this.m_PermissionRequests == null) {
            this.m_PermissionRequests = new HashMap();
        }
        int i = 1;
        while (true) {
            valueOf = Integer.valueOf(i);
            if (!this.m_PermissionRequests.containsKey(valueOf)) {
                break;
            }
            i++;
        }
        this.m_PermissionRequests.put(valueOf, permissionRequest);
        if (this.m_PermissionRequests.size() == 1) {
            String[] permissionNames = permissionRequest.getPermissionNames();
            valueOf.getClass();
            requestPermissionsFromActivity(permissionNames, i);
        }
    }

    /* access modifiers changed from: protected */
    public void addPhoneCallListener() {
        this.m_AddPhoneCallListener = true;
        this.m_TelephonyManager.listen(this.m_PhoneCallListener, 32);
    }

    public boolean addViewToPlayer(View view, boolean z) {
        View view2 = getView();
        swapViews(view, z ? view2 : null);
        FrameLayout frameLayout = getFrameLayout();
        boolean z2 = true;
        boolean z3 = view.getParent() == frameLayout;
        boolean z4 = z && view2.getParent() == null;
        boolean z5 = view2.getParent() == frameLayout;
        if (!z3 || (!z4 && !z5)) {
            z2 = false;
        }
        if (!z2) {
            if (!z3) {
                C0055x.Log(6, "addViewToPlayer: Failure adding view to hierarchy");
            }
            if (!z4 && !z5) {
                C0055x.Log(6, "addViewToPlayer: Failure removing old view from hierarchy");
            }
        }
        return z2;
    }

    /* access modifiers changed from: protected */
    public boolean canPauseUnity() {
        return this.mState.c() || !this.mState.b();
    }

    /* access modifiers changed from: protected */
    public boolean canResumeUnity() {
        Activity activity = this.mActivity;
        return this.mState.a(activity != null ? MultiWindowSupport.isInMultiWindowMode(activity) : false);
    }

    /* access modifiers changed from: package-private */
    public abstract void cleanupResourcesForDestroy();

    public void configurationChanged(Configuration configuration) {
        int diff = this.prevConfig.diff(configuration);
        if (!((diff & 256) == 0 && (diff & 1024) == 0 && (diff & 2048) == 0 && (diff & 128) == 0)) {
            nativeHidePreservedContent();
        }
        this.prevConfig = new Configuration(configuration);
        H1 h1 = this.mVideoPlayerProxy;
        if (h1 != null) {
            h1.a();
        }
        UnityAccessibilityDelegate unityAccessibilityDelegate = this.m_AccessibilityDelegate;
        if (unityAccessibilityDelegate != null) {
            unityAccessibilityDelegate.a(configuration);
        }
    }

    public void destroy() {
        Camera2Wrapper camera2Wrapper = this.m_Camera2Wrapper;
        if (camera2Wrapper != null) {
            camera2Wrapper.closeCamera2();
            this.m_Camera2Wrapper = null;
        }
        HFPStatus hFPStatus = this.m_HFPStatus;
        if (hFPStatus != null) {
            hFPStatus.b();
            this.m_HFPStatus = null;
        }
        FmodAndroidAudioManager fmodAndroidAudioManager = this.m_FmodAndroidAudioManager;
        if (fmodAndroidAudioManager != null) {
            fmodAndroidAudioManager.setActivity((Activity) null);
        }
        C0057y yVar = this.m_NetworkConnectivity;
        if (yVar != null) {
            yVar.a();
            this.m_NetworkConnectivity = null;
        }
        A a = this.m_OnBackPressedDispatcher;
        if (a != null) {
            a.unregisterOnBackPressedCallback();
            this.m_OnBackPressedDispatcher = null;
        }
        this.mQuitting = true;
        if (!this.mState.b()) {
            setupUnityToBePaused();
        }
        cleanupResourcesForDestroy();
        unloadNative();
    }

    /* access modifiers changed from: protected */
    public void disableLogger() {
        C0055x.a = true;
    }

    /* access modifiers changed from: package-private */
    public void disableStaticSplashScreen() {
        runOnUiThread(new F0(this));
    }

    /* access modifiers changed from: protected */
    public void executeMainThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.m_MainThreadJobs.poll();
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void finish() {
        Activity activity = this.mActivity;
        if (activity != null && !activity.isFinishing()) {
            this.mActivity.finish();
        }
    }

    /* access modifiers changed from: package-private */
    public Activity getActivity() {
        return this.mActivity;
    }

    /* access modifiers changed from: package-private */
    public ApplicationInfo getApplicationInfo() {
        return this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
    }

    /* access modifiers changed from: package-private */
    public boolean getAutoReportFullyDrawnEnabled() {
        try {
            return getApplicationInfo().metaData.getBoolean(AUTO_REPORT_FULLY_DRAWN_ENABLE_METADATA_NAME);
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getAutoSetGameStateEnabled() {
        try {
            return getApplicationInfo().metaData.getBoolean(AUTO_SET_GAME_STATE_ENABLE_METADATA_NAME);
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public String getClipboardText() {
        ClipData primaryClip = this.m_ClipboardManager.getPrimaryClip();
        return primaryClip != null ? primaryClip.getItemAt(0).coerceToText(this.mContext).toString() : "";
    }

    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public C0049u getContextType() {
        return this.mContextType;
    }

    public FrameLayout getFrameLayout() {
        return this.m_FrameLayout;
    }

    /* access modifiers changed from: protected */
    public String getKeyboardLayout() {
        InputMethodSubtype currentInputMethodSubtype = ((InputMethodManager) this.mContext.getSystemService("input_method")).getCurrentInputMethodSubtype();
        if (currentInputMethodSubtype == null) {
            return null;
        }
        String locale = currentInputMethodSubtype.getLocale();
        if (locale != null && !locale.equals("")) {
            return locale;
        }
        String mode = currentInputMethodSubtype.getMode();
        String extraValue = currentInputMethodSubtype.getExtraValue();
        return mode + " " + extraValue;
    }

    /* access modifiers changed from: protected */
    public String getLaunchURL() {
        Uri data;
        Activity activity = this.mActivity;
        if (activity == null || (data = activity.getIntent().getData()) == null) {
            return null;
        }
        return data.toString();
    }

    /* access modifiers changed from: protected */
    public int getNetworkConnectivity() {
        C0057y yVar = this.m_NetworkConnectivity;
        if (yVar != null) {
            return yVar.b();
        }
        this.m_NetworkConnectivity = PlatformSupport.NOUGAT_SUPPORT ? new d(this.mContext) : new C0057y(this.mContext);
        return this.m_NetworkConnectivity.b();
    }

    public String getNetworkProxySettings(String str) {
        String str2;
        String str3;
        if (str.startsWith("http:")) {
            str2 = "http.proxyHost";
            str3 = "http.proxyPort";
        } else {
            if (str.startsWith("https:")) {
                str2 = "https.proxyHost";
                str3 = "https.proxyPort";
            }
            return null;
        }
        String property = System.getProperties().getProperty(str2);
        if (property != null && !"".equals(property)) {
            StringBuilder sb = new StringBuilder(property);
            String property2 = System.getProperties().getProperty(str3);
            if (property2 != null && !"".equals(property2)) {
                sb.append(":");
                sb.append(property2);
            }
            String property3 = System.getProperties().getProperty("http.nonProxyHosts");
            if (property3 != null && !"".equals(property3)) {
                sb.append(10);
                sb.append(property3);
            }
            return sb.toString();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean getSplashEnabled() {
        try {
            return getApplicationInfo().metaData.getBoolean(SPLASH_ENABLE_METADATA_NAME);
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public int getSplashMode() {
        try {
            return getApplicationInfo().metaData.getInt(SPLASH_MODE_METADATA_NAME);
        } catch (Exception unused) {
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public String getState() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mState.toString() + "\n");
        sb.append(String.format("m_AudioVolumeHandler = %b", new Object[]{this.m_AudioVolumeHandler}) + "\n");
        sb.append(String.format("m_OrientationLockListener = %b", new Object[]{this.m_OrientationLockListener}) + "\n");
        return sb.toString();
    }

    public abstract SurfaceView getSurfaceView();

    /* access modifiers changed from: protected */
    public int getUaaLLaunchProcessType() {
        String processName = getProcessName();
        return (processName == null || processName.equals(this.mContext.getPackageName())) ? 0 : 1;
    }

    public abstract View getView();

    /* access modifiers changed from: package-private */
    public abstract boolean handleFocus(boolean z);

    /* access modifiers changed from: package-private */
    public abstract void hidePreservedContent();

    /* access modifiers changed from: protected */
    public void initialize(FrameLayout frameLayout) {
        this.m_FrameLayout = frameLayout;
        EarlyEnableFullScreenIfEnabled();
        Configuration configuration = getFrameLayout().getResources().getConfiguration();
        this.prevConfig = configuration;
        this.mNaturalOrientation = getNaturalOrientation(configuration.orientation);
        if (this.mActivity != null && getSplashEnabled()) {
            g gVar = new g(this.mContext, a.b(3)[getSplashMode()]);
            this.m_SplashScreen = gVar;
            this.m_FrameLayout.addView(gVar);
            this.m_FrameLayout.bringChildToFront(this.m_SplashScreen);
        }
        preloadJavaPlugins();
        String loadNative = loadNative(getUnityNativeLibraryPath(this.mContext));
        if (!v1.d()) {
            C0055x.Log(6, "Your hardware does not support this application.");
            AlertDialog.Builder positiveButton = new AlertDialog.Builder(this.mContext).setTitle("Failure to initialize!").setPositiveButton("OK", new D0(this));
            AlertDialog create = positiveButton.setMessage("Your hardware does not support this application.\n\n" + loadNative + "\n\n Press OK to quit.").create();
            create.setCancelable(false);
            create.show();
            return;
        }
        initJni(this.mContext, this.mContextType.a);
        this.mState.d(true);
        this.mQuitting = false;
        developmentPlayerInitialize();
        hideStatusBar();
        this.m_TelephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        this.m_ClipboardManager = (ClipboardManager) this.mContext.getSystemService("clipboard");
        this.m_Camera2Wrapper = new Camera2Wrapper(this.mContext);
        this.m_HFPStatus = new HFPStatus(this.mContext);
        this.m_Cursor = new C0052v0(this);
        FmodAndroidAudioManager instance = FmodAndroidAudioManager.getInstance();
        this.m_FmodAndroidAudioManager = instance;
        instance.setActivity(this.mActivity);
        this.m_OnBackPressedDispatcher = B.a(getContext(), 1, new E0(this));
        Activity activity = this.mActivity;
        if (activity != null) {
            this.m_Window = activity.getWindow();
        }
    }

    /* access modifiers changed from: protected */
    public boolean initializeGoogleAr() {
        if (this.m_ARCoreApi != null || this.mActivity == null || !getARCoreEnabled()) {
            return false;
        }
        GoogleARCoreApi googleARCoreApi = new GoogleARCoreApi();
        this.m_ARCoreApi = googleARCoreApi;
        googleARCoreApi.initializeARCore(this.mActivity);
        if (this.mState.b()) {
            return false;
        }
        this.m_ARCoreApi.resumeARCore();
        return false;
    }

    public boolean injectEvent(InputEvent inputEvent) {
        if (!v1.d()) {
            return false;
        }
        return nativeInjectEvent(inputEvent);
    }

    public void invokeOnMainThread(Runnable runnable) {
        if (v1.d()) {
            if (runningOnUIThread() || !runningOnMainThread()) {
                this.m_MainThreadJobs.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean isFinishing() {
        if (this.mQuitting) {
            return true;
        }
        Activity activity = this.mActivity;
        if (activity != null) {
            this.mQuitting = activity.isFinishing();
        }
        return this.mQuitting;
    }

    /* access modifiers changed from: package-private */
    public boolean isNativeInitialized() {
        return v1.d() && this.mState.a();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r0.getCallingPackage();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isUaaLUseCase() {
        /*
            r3 = this;
            android.app.Activity r0 = r3.mActivity
            r1 = 0
            if (r0 == 0) goto L_0x0018
            java.lang.String r0 = r0.getCallingPackage()
            if (r0 == 0) goto L_0x0018
            android.content.Context r2 = r3.mContext
            java.lang.String r2 = r2.getPackageName()
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0018
            r1 = 1
        L_0x0018:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityPlayer.isUaaLUseCase():boolean");
    }

    /* access modifiers changed from: protected */
    public void kill() {
        C0055x.Log(4, "Quitting process");
        Process.killProcess(Process.myPid());
    }

    /* access modifiers changed from: protected */
    public boolean loadLibrary(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (Exception | UnsatisfiedLinkError unused) {
            return false;
        }
    }

    public void newIntent(Intent intent) {
        setLaunchURL(intent.getData());
    }

    /* access modifiers changed from: package-private */
    public abstract void onOrientationChanged(int i, int i2);

    public void onPause() {
        MultiWindowSupport.saveMultiWindowMode(this.mActivity);
        if (!MultiWindowSupport.isInMultiWindowMode(this.mActivity)) {
            setupUnityToBePaused();
        }
    }

    public void onResume() {
        if (!MultiWindowSupport.isInMultiWindowMode(this.mActivity) || MultiWindowSupport.isMultiWindowModeChangedToTrue(this.mActivity)) {
            setupUnityToBeResumed();
        }
    }

    public void onStart() {
        if (MultiWindowSupport.isInMultiWindowMode(this.mActivity)) {
            setupUnityToBeResumed();
        }
    }

    public void onStop() {
        if (MultiWindowSupport.isInMultiWindowMode(this.mActivity)) {
            setupUnityToBePaused();
        }
    }

    public void onUnityPlayerQuitted() {
    }

    public void onUnityPlayerUnloaded() {
    }

    public void pause() {
        setupUnityToBePaused();
    }

    /* access modifiers changed from: package-private */
    public void pauseUnity() {
        this.mState.c(false);
        this.mState.e(true);
    }

    public synchronized void permissionResponse(Activity activity, int i, String[] strArr, int[] iArr) {
        int i2;
        if (this.m_PermissionRequests != null) {
            Integer valueOf = Integer.valueOf(i);
            PermissionRequest permissionRequest = (PermissionRequest) this.m_PermissionRequests.get(valueOf);
            if (permissionRequest != null) {
                this.m_PermissionRequests.remove(valueOf);
                String[] permissionNames = permissionRequest.getPermissionNames();
                int[] iArr2 = new int[permissionNames.length];
                for (int i3 = 0; i3 < strArr.length; i3++) {
                    String str = strArr[i3];
                    int i4 = 0;
                    while (true) {
                        if (i4 >= permissionNames.length) {
                            i4 = -1;
                            break;
                        } else if (str.equals(permissionNames[i4])) {
                            break;
                        } else {
                            i4++;
                        }
                    }
                    if (i4 < 0) {
                        C0055x.Log(6, "Permission not found in request: " + str);
                    } else {
                        if (iArr[i3] == 0) {
                            i2 = 1;
                        } else {
                            if (Build.VERSION.SDK_INT < 30) {
                                if (!UnityPermissions.shouldShowRequestPermissionRationale(activity, str)) {
                                    i2 = 3;
                                }
                            }
                            i2 = 2;
                        }
                        iArr2[i4] = i2;
                    }
                }
                invokeOnMainThread((Runnable) new G0(permissionRequest, permissionNames, iArr2));
                triggerNextPermissionRequest();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void postOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public void removeViewFromPlayer(View view) {
        View view2 = getView();
        swapViews(view2, view);
        boolean z = true;
        boolean z2 = view.getParent() == null;
        if (view2.getParent() != getFrameLayout()) {
            z = false;
        }
        if (!z2 || !z) {
            if (!z2) {
                C0055x.Log(6, "removeViewFromPlayer: Failure removing view from hierarchy");
            }
            if (!z) {
                C0055x.Log(6, "removeViewFromPlayer: Failure adding old view to hierarchy");
            }
        }
    }

    public void reportError(String str, String str2) {
        C0055x.Log(6, str + ": " + str2);
    }

    /* access modifiers changed from: package-private */
    public void reportFullyDrawn() {
        this.mActivity.reportFullyDrawn();
    }

    public void requestPermissionsFromActivity(String[] strArr, int i) {
        this.mActivity.requestPermissions(strArr, i);
    }

    public void resume() {
        setupUnityToBeResumed();
    }

    /* access modifiers changed from: package-private */
    public void resumeUnity() {
        this.mState.c(true);
    }

    /* access modifiers changed from: package-private */
    public void runOnAnonymousThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    /* access modifiers changed from: package-private */
    public void runOnUiThread(Runnable runnable) {
        Activity activity = this.mActivity;
        if (activity != null) {
            activity.runOnUiThread(runnable);
        } else if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.mHandler.post(runnable);
        } else {
            runnable.run();
        }
    }

    public abstract boolean runningOnMainThread();

    /* access modifiers changed from: protected */
    public void setAccessibilityDelegate(UnityAccessibilityDelegate unityAccessibilityDelegate) {
        this.m_AccessibilityDelegate = unityAccessibilityDelegate;
    }

    /* access modifiers changed from: protected */
    public void setClipboardText(String str) {
        this.m_ClipboardManager.setPrimaryClip(ClipData.newPlainText("Text", str));
    }

    /* access modifiers changed from: package-private */
    public void setLaunchURL(Uri uri) {
        invokeOnMainThread((Runnable) new H0(uri != null ? uri.toString() : null));
    }

    public abstract void setMainSurfaceViewAspectRatio(float f);

    /* access modifiers changed from: protected */
    public void setupUnityToBePaused() {
        GoogleARCoreApi googleARCoreApi = this.m_ARCoreApi;
        if (googleARCoreApi != null) {
            googleARCoreApi.pauseARCore();
        }
        H1 h1 = this.mVideoPlayerProxy;
        if (h1 != null) {
            h1.b();
        }
        AudioVolumeHandler audioVolumeHandler = this.m_AudioVolumeHandler;
        if (audioVolumeHandler != null) {
            audioVolumeHandler.a();
            this.m_AudioVolumeHandler = null;
        }
        OrientationLockListener orientationLockListener = this.m_OrientationLockListener;
        if (orientationLockListener != null) {
            orientationLockListener.a();
            this.m_OrientationLockListener = null;
        }
        A a = this.m_OnBackPressedDispatcher;
        if (a != null) {
            a.c = a.a != null;
            a.unregisterOnBackPressedCallback();
        }
        if (canPauseUnity()) {
            pauseUnity();
        }
    }

    /* access modifiers changed from: protected */
    public void setupUnityToBeResumed() {
        GoogleARCoreApi googleARCoreApi = this.m_ARCoreApi;
        if (googleARCoreApi != null) {
            googleARCoreApi.resumeARCore();
        }
        this.mState.e(false);
        H1 h1 = this.mVideoPlayerProxy;
        if (h1 != null) {
            h1.c();
        }
        if (canResumeUnity()) {
            resumeUnity();
        }
        if (v1.d()) {
            nativeRestartActivityIndicator();
        }
        if (this.m_AudioVolumeHandler == null) {
            this.m_AudioVolumeHandler = new AudioVolumeHandler(this.mContext);
        }
        if (this.m_OrientationLockListener == null && v1.d()) {
            this.m_OrientationLockListener = new OrientationLockListener(this.mContext);
        }
        A a = this.m_OnBackPressedDispatcher;
        if (a != null && a.c) {
            a.registerOnBackPressedCallback();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldReportFullyDrawn() {
        if (this.mActivity == null) {
            return false;
        }
        return getAutoReportFullyDrawnEnabled();
    }

    /* access modifiers changed from: package-private */
    public boolean shouldSetGameState() {
        return PlatformSupport.TIRAMISU_SUPPORT && this.mActivity != null && !isUaaLUseCase() && getAutoSetGameStateEnabled();
    }

    /* access modifiers changed from: protected */
    public boolean showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        if (this.mVideoPlayerProxy == null) {
            this.mVideoPlayerProxy = new H1(this);
        }
        boolean a = this.mVideoPlayerProxy.a(this.mContext, str, i, i2, i3, z, (long) i4, (long) i5, new I0(this));
        if (a) {
            runOnUiThread(new J0(this));
        }
        return a;
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        this.mState.d(false);
    }

    /* access modifiers changed from: protected */
    public boolean skipPermissionsDialog() {
        Activity activity = this.mActivity;
        if (activity != null) {
            return UnityPermissions.skipPermissionsDialog(activity);
        }
        return false;
    }

    public boolean startOrientationListener(int i) {
        String str;
        if (this.mOrientationListener != null) {
            str = "Orientation Listener already started.";
        } else {
            K0 k0 = new K0(this, this.mContext, i);
            this.mOrientationListener = k0;
            if (k0.canDetectOrientation()) {
                this.mOrientationListener.enable();
                return true;
            }
            str = "Orientation Listener cannot detect orientation.";
        }
        C0055x.Log(5, str);
        return false;
    }

    public boolean stopOrientationListener() {
        OrientationEventListener orientationEventListener = this.mOrientationListener;
        if (orientationEventListener == null) {
            C0055x.Log(5, "Orientation Listener was not started.");
            return false;
        }
        orientationEventListener.disable();
        this.mOrientationListener = null;
        return true;
    }

    /* access modifiers changed from: protected */
    public void toggleGyroscopeSensor(boolean z) {
        SensorManager sensorManager = (SensorManager) this.mContext.getSystemService("sensor");
        Sensor defaultSensor = sensorManager.getDefaultSensor(11);
        if (z) {
            sensorManager.registerListener(this.m_FakeListener, defaultSensor, 1);
        } else {
            sensorManager.unregisterListener(this.m_FakeListener);
        }
    }

    public synchronized void triggerNextPermissionRequest() {
        Iterator it;
        HashMap hashMap = this.m_PermissionRequests;
        if (!(hashMap == null || (it = hashMap.entrySet().iterator()) == null || !it.hasNext())) {
            Map.Entry entry = (Map.Entry) it.next();
            requestPermissionsFromActivity(((PermissionRequest) entry.getValue()).getPermissionNames(), ((Integer) entry.getKey()).intValue());
        }
    }

    public void unload() {
        nativeApplicationUnload();
    }

    public void windowFocusChanged(boolean z) {
        this.mState.b(z);
        if (handleFocus(z) && canResumeUnity()) {
            resumeUnity();
        }
    }
}
