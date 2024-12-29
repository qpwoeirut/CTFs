package com.google.androidgamesdk;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.androidgamesdk.gametextinput.GameTextInput;
import com.google.androidgamesdk.gametextinput.InputConnection;
import com.google.androidgamesdk.gametextinput.Listener;
import com.google.androidgamesdk.gametextinput.Settings;
import com.google.androidgamesdk.gametextinput.State;
import dalvik.system.BaseDexClassLoader;
import java.io.File;

public class GameActivity extends AppCompatActivity implements SurfaceHolder.Callback2, Listener, OnApplyWindowInsetsListener, ViewTreeObserver.OnGlobalLayoutListener {
    private static final String DEFAULT_NATIVE_LIB_NAME = "main";
    private static final String KEY_NATIVE_SAVED_STATE = "android:native_state";
    private static final String LOG_TAG = "GameActivity";
    public static final String META_DATA_LIB_NAME = "android.app.lib_name";
    protected int contentViewId;
    private EditorInfo imeEditorInfo;
    private SurfaceHolder mCurSurfaceHolder;
    protected boolean mDestroyed;
    protected int mLastContentHeight;
    protected int mLastContentWidth;
    protected int mLastContentX;
    protected int mLastContentY;
    protected final int[] mLocation = new int[2];
    private long mNativeHandle;
    protected InputEnabledSurfaceView mSurfaceView;

    /* access modifiers changed from: protected */
    public native String getDlError();

    /* access modifiers changed from: protected */
    public native long initializeNativeCode(String str, String str2, String str3, AssetManager assetManager, byte[] bArr, Configuration configuration);

    /* access modifiers changed from: protected */
    public native void onConfigurationChangedNative(long j, Configuration configuration);

    /* access modifiers changed from: protected */
    public native void onContentRectChangedNative(long j, int i, int i2, int i3, int i4);

    /* access modifiers changed from: protected */
    public native void onEditorActionNative(long j, int i);

    /* access modifiers changed from: protected */
    public native boolean onKeyDownNative(long j, KeyEvent keyEvent);

    /* access modifiers changed from: protected */
    public native boolean onKeyUpNative(long j, KeyEvent keyEvent);

    /* access modifiers changed from: protected */
    public native void onPauseNative(long j);

    /* access modifiers changed from: protected */
    public native void onResumeNative(long j);

    /* access modifiers changed from: protected */
    public native byte[] onSaveInstanceStateNative(long j);

    /* access modifiers changed from: protected */
    public native void onSoftwareKeyboardVisibilityChangedNative(long j, boolean z);

    /* access modifiers changed from: protected */
    public native void onStartNative(long j);

    /* access modifiers changed from: protected */
    public native void onStopNative(long j);

    /* access modifiers changed from: protected */
    public native void onSurfaceChangedNative(long j, Surface surface, int i, int i2, int i3);

    /* access modifiers changed from: protected */
    public native void onSurfaceCreatedNative(long j, Surface surface);

    /* access modifiers changed from: protected */
    public native void onSurfaceDestroyedNative(long j);

    /* access modifiers changed from: protected */
    public native void onSurfaceRedrawNeededNative(long j, Surface surface);

    /* access modifiers changed from: protected */
    public native void onTextInputEventNative(long j, State state);

    /* access modifiers changed from: protected */
    public native boolean onTouchEventNative(long j, MotionEvent motionEvent, int i, int i2, int i3, int i4, int i5, long j2, long j3, int i6, int i7, int i8, int i9, int i10, int i11, float f, float f2);

    /* access modifiers changed from: protected */
    public native void onTrimMemoryNative(long j, int i);

    /* access modifiers changed from: protected */
    public native void onWindowFocusChangedNative(long j, boolean z);

    /* access modifiers changed from: protected */
    public native void onWindowInsetsChangedNative(long j);

    /* access modifiers changed from: protected */
    public native void setInputConnectionNative(long j, InputConnection inputConnection);

    /* access modifiers changed from: protected */
    public native void terminateNativeCode(long j);

    /* access modifiers changed from: protected */
    public boolean processMotionEvent(MotionEvent motionEvent) {
        return onTouchEventNative(this.mNativeHandle, motionEvent, motionEvent.getPointerCount(), motionEvent.getHistorySize(), motionEvent.getDeviceId(), motionEvent.getSource(), motionEvent.getAction(), motionEvent.getEventTime(), motionEvent.getDownTime(), motionEvent.getFlags(), motionEvent.getMetaState(), motionEvent.getActionButton(), motionEvent.getButtonState(), Build.VERSION.SDK_INT >= 29 ? motionEvent.getClassification() : 0, motionEvent.getEdgeFlags(), motionEvent.getXPrecision(), motionEvent.getYPrecision());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (processMotionEvent(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (processMotionEvent(motionEvent)) {
            return true;
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (onKeyUpNative(this.mNativeHandle, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (onKeyDownNative(this.mNativeHandle, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void stateChanged(State state, boolean z) {
        onTextInputEventNative(this.mNativeHandle, state);
    }

    public void onGlobalLayout() {
        this.mSurfaceView.getLocationInWindow(this.mLocation);
        int width = this.mSurfaceView.getWidth();
        int height = this.mSurfaceView.getHeight();
        int[] iArr = this.mLocation;
        int i = iArr[0];
        if (i != this.mLastContentX || iArr[1] != this.mLastContentY || width != this.mLastContentWidth || height != this.mLastContentHeight) {
            this.mLastContentX = i;
            int i2 = iArr[1];
            this.mLastContentY = i2;
            this.mLastContentWidth = width;
            this.mLastContentHeight = height;
            if (!this.mDestroyed) {
                onContentRectChangedNative(this.mNativeHandle, i, i2, width, height);
            }
        }
    }

    public void setTextInputState(State state) {
        InputEnabledSurfaceView inputEnabledSurfaceView = this.mSurfaceView;
        if (inputEnabledSurfaceView != null) {
            if (inputEnabledSurfaceView.mInputConnection == null) {
                Log.w(LOG_TAG, "No input connection has been set yet");
            } else {
                this.mSurfaceView.mInputConnection.setState(state);
            }
        }
    }

    public long getGameActivityNativeHandle() {
        return this.mNativeHandle;
    }

    /* access modifiers changed from: protected */
    public InputEnabledSurfaceView createSurfaceView() {
        return new InputEnabledSurfaceView(this);
    }

    /* access modifiers changed from: protected */
    public void onCreateSurfaceView() {
        InputEnabledSurfaceView createSurfaceView = createSurfaceView();
        this.mSurfaceView = createSurfaceView;
        if (createSurfaceView != null) {
            FrameLayout frameLayout = new FrameLayout(this);
            int generateViewId = ViewCompat.generateViewId();
            this.contentViewId = generateViewId;
            frameLayout.setId(generateViewId);
            frameLayout.addView(this.mSurfaceView);
            setContentView((View) frameLayout);
            frameLayout.requestFocus();
            this.mSurfaceView.getHolder().addCallback(this);
            ViewCompat.setOnApplyWindowInsetsListener(this.mSurfaceView, this);
        }
    }

    /* access modifiers changed from: protected */
    public void onSetUpWindow() {
        getWindow().setFormat(4);
        getWindow().setSoftInputMode(16);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String string;
        onCreateSurfaceView();
        InputEnabledSurfaceView inputEnabledSurfaceView = this.mSurfaceView;
        if (inputEnabledSurfaceView != null) {
            inputEnabledSurfaceView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
        onSetUpWindow();
        String str = new String(DEFAULT_NATIVE_LIB_NAME);
        try {
            ActivityInfo activityInfo = getPackageManager().getActivityInfo(getIntent().getComponent(), 128);
            if (!(activityInfo.metaData == null || (string = activityInfo.metaData.getString(META_DATA_LIB_NAME)) == null)) {
                str = string;
            }
            String str2 = "lib" + str + ".so";
            Log.i(LOG_TAG, "Looking for library " + str2);
            BaseDexClassLoader baseDexClassLoader = (BaseDexClassLoader) getClassLoader();
            String findLibrary = baseDexClassLoader.findLibrary(str);
            if (findLibrary != null) {
                Log.i(LOG_TAG, "Found library " + str2 + ". Loading...");
                System.loadLibrary(str);
            } else if (str.equals(DEFAULT_NATIVE_LIB_NAME)) {
                Log.i(LOG_TAG, "Application should have loaded the native library " + str2 + " explicitly by now. ");
            } else {
                throw new IllegalArgumentException("unable to find native library " + str2 + " using classloader: " + baseDexClassLoader.toString());
            }
            long initializeNativeCode = initializeNativeCode(getAbsolutePath(getFilesDir()), getAbsolutePath(getObbDir()), getAbsolutePath(getExternalFilesDir((String) null)), getAssets(), bundle != null ? bundle.getByteArray(KEY_NATIVE_SAVED_STATE) : null, getResources().getConfiguration());
            this.mNativeHandle = initializeNativeCode;
            if (initializeNativeCode != 0) {
                InputEnabledSurfaceView inputEnabledSurfaceView2 = this.mSurfaceView;
                if (inputEnabledSurfaceView2 != null) {
                    setInputConnectionNative(initializeNativeCode, inputEnabledSurfaceView2.mInputConnection);
                }
                super.onCreate(bundle);
                return;
            }
            throw new UnsatisfiedLinkError("Unable to initialize native code \"" + findLibrary + "\": " + getDlError());
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Error getting activity info", e);
        }
    }

    private static String getAbsolutePath(File file) {
        if (file != null) {
            return file.getAbsolutePath();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mDestroyed = true;
        if (this.mCurSurfaceHolder != null) {
            onSurfaceDestroyedNative(this.mNativeHandle);
            this.mCurSurfaceHolder = null;
        }
        terminateNativeCode(this.mNativeHandle);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        onPauseNative(this.mNativeHandle);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        onResumeNative(this.mNativeHandle);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        byte[] onSaveInstanceStateNative = onSaveInstanceStateNative(this.mNativeHandle);
        if (onSaveInstanceStateNative != null) {
            bundle.putByteArray(KEY_NATIVE_SAVED_STATE, onSaveInstanceStateNative);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        onStartNative(this.mNativeHandle);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        onStopNative(this.mNativeHandle);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (!this.mDestroyed) {
            onConfigurationChangedNative(this.mNativeHandle, configuration);
        }
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (!this.mDestroyed) {
            onTrimMemoryNative(this.mNativeHandle, i);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!this.mDestroyed) {
            onWindowFocusChangedNative(this.mNativeHandle, z);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (!this.mDestroyed) {
            this.mCurSurfaceHolder = surfaceHolder;
            onSurfaceCreatedNative(this.mNativeHandle, surfaceHolder.getSurface());
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (!this.mDestroyed) {
            this.mCurSurfaceHolder = surfaceHolder;
            onSurfaceChangedNative(this.mNativeHandle, surfaceHolder.getSurface(), i, i2, i3);
        }
    }

    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
        if (!this.mDestroyed) {
            this.mCurSurfaceHolder = surfaceHolder;
            onSurfaceRedrawNeededNative(this.mNativeHandle, surfaceHolder.getSurface());
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mCurSurfaceHolder = null;
        if (!this.mDestroyed) {
            onSurfaceDestroyedNative(this.mNativeHandle);
        }
    }

    /* access modifiers changed from: package-private */
    public void setWindowFlags(int i, int i2) {
        getWindow().setFlags(i, i2);
    }

    /* access modifiers changed from: package-private */
    public void setWindowFormat(int i) {
        getWindow().setFormat(i);
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        this.mSurfaceView.mInputConnection.onApplyWindowInsets(view, windowInsetsCompat);
        onWindowInsetsChangedNative(this.mNativeHandle);
        view.onApplyWindowInsets(windowInsetsCompat.toWindowInsets());
        return windowInsetsCompat;
    }

    public Insets getWindowInsets(int i) {
        Insets insets;
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(this.mSurfaceView);
        if (rootWindowInsets == null || (insets = rootWindowInsets.getInsets(i)) == Insets.NONE) {
            return null;
        }
        return insets;
    }

    public Insets getWaterfallInsets() {
        DisplayCutoutCompat displayCutout;
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(this.mSurfaceView);
        if (rootWindowInsets == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null) {
            return null;
        }
        return displayCutout.getWaterfallInsets();
    }

    public void onImeInsetsChanged(Insets insets) {
        Log.v(LOG_TAG, "onImeInsetsChanged from Text Listener");
    }

    public void onSoftwareKeyboardVisibilityChanged(boolean z) {
        onSoftwareKeyboardVisibilityChangedNative(this.mNativeHandle, z);
    }

    public void onEditorAction(int i) {
        onEditorActionNative(this.mNativeHandle, i);
    }

    public EditorInfo getImeEditorInfo() {
        if (this.imeEditorInfo == null) {
            EditorInfo editorInfo = new EditorInfo();
            this.imeEditorInfo = editorInfo;
            editorInfo.inputType = 1;
            this.imeEditorInfo.actionId = 6;
            this.imeEditorInfo.imeOptions = 33554438;
        }
        return this.imeEditorInfo;
    }

    public void setImeEditorInfo(EditorInfo editorInfo) {
        this.imeEditorInfo = editorInfo;
        this.mSurfaceView.mInputConnection.setEditorInfo(editorInfo);
    }

    public void setImeEditorInfoFields(int i, int i2, int i3) {
        EditorInfo imeEditorInfo2 = getImeEditorInfo();
        imeEditorInfo2.inputType = i;
        imeEditorInfo2.actionId = i2;
        imeEditorInfo2.imeOptions = i3;
        this.mSurfaceView.mInputConnection.setEditorInfo(imeEditorInfo2);
    }

    protected class InputEnabledSurfaceView extends SurfaceView {
        InputConnection mInputConnection;

        public InputEnabledSurfaceView(GameActivity gameActivity) {
            super(gameActivity);
            EditorInfo imeEditorInfo = gameActivity.getImeEditorInfo();
            this.mInputConnection = new InputConnection(gameActivity, this, new Settings(imeEditorInfo, imeEditorInfo.inputType == 0)).setListener(gameActivity);
        }

        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            if (editorInfo != null) {
                GameTextInput.copyEditorInfo(this.mInputConnection.getEditorInfo(), editorInfo);
            }
            return this.mInputConnection;
        }

        public EditorInfo getEditorInfo() {
            return this.mInputConnection.getEditorInfo();
        }

        public void setEditorInfo(EditorInfo editorInfo) {
            this.mInputConnection.setEditorInfo(editorInfo);
        }
    }
}
