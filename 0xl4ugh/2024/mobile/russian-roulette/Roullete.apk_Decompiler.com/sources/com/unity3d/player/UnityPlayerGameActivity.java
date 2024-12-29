package com.unity3d.player;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.google.androidgamesdk.GameActivity;

public class UnityPlayerGameActivity extends GameActivity implements IUnityPlayerLifecycleEvents, IUnityPermissionRequestSupport, IUnityPlayerSupport {
    protected UnityPlayerForGameActivity mUnityPlayer;

    public void onUnityPlayerQuitted() {
    }

    public void onUnityPlayerUnloaded() {
    }

    /* access modifiers changed from: protected */
    public String updateUnityCommandLineArguments(String str) {
        return str;
    }

    static {
        System.loadLibrary("game");
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 30) {
            getWindow().setDecorFitsSystemWindows(true);
        }
    }

    public UnityPlayerForGameActivity getUnityPlayerConnection() {
        return this.mUnityPlayer;
    }

    private void applyInsetListener(SurfaceView surfaceView) {
        surfaceView.getViewTreeObserver().addOnGlobalLayoutListener(new UnityPlayerGameActivity$$ExternalSyntheticLambda1(this, surfaceView));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyInsetListener$0(SurfaceView surfaceView) {
        onApplyWindowInsets(surfaceView, ViewCompat.getRootWindowInsets(getWindow().getDecorView()));
    }

    /* access modifiers changed from: protected */
    public void onCreateSurfaceView() {
        super.onCreateSurfaceView();
        applyInsetListener(this.mSurfaceView);
        this.mSurfaceView.setId(UnityPlayerForGameActivity.getUnityViewIdentifier(this));
        getIntent().putExtra("unity", updateUnityCommandLineArguments(getIntent().getStringExtra("unity")));
        this.mUnityPlayer = new UnityPlayerForGameActivity(this, (FrameLayout) findViewById(this.contentViewId), this.mSurfaceView, this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mUnityPlayer.destroy();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.mUnityPlayer.onStop();
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        this.mUnityPlayer.onStart();
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.mUnityPlayer.onPause();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.mUnityPlayer.onResume();
        super.onResume();
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mUnityPlayer.configurationChanged(configuration);
        super.onConfigurationChanged(configuration);
    }

    public void onWindowFocusChanged(boolean z) {
        this.mUnityPlayer.windowFocusChanged(z);
        super.onWindowFocusChanged(z);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.mUnityPlayer.newIntent(intent);
    }

    public void requestPermissions(PermissionRequest permissionRequest) {
        this.mUnityPlayer.addPermissionRequest(permissionRequest);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.mUnityPlayer.permissionResponse(this, i, strArr, iArr);
    }
}
