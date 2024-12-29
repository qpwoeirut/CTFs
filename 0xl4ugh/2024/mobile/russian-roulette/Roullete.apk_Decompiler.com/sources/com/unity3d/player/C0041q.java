package com.unity3d.player;

import android.hardware.camera2.CameraDevice;

/* renamed from: com.unity3d.player.q  reason: case insensitive filesystem */
final class C0041q extends CameraDevice.StateCallback {
    final /* synthetic */ C0047t a;

    C0041q(C0047t tVar) {
        this.a = tVar;
    }

    public final void onClosed(CameraDevice cameraDevice) {
        C0047t.D.release();
    }

    public final void onDisconnected(CameraDevice cameraDevice) {
        C0055x.Log(5, "Camera2: CameraDevice disconnected.");
        this.a.a(cameraDevice);
        C0047t.D.release();
    }

    public final void onError(CameraDevice cameraDevice, int i) {
        C0055x.Log(6, "Camera2: Error opeining CameraDevice " + i);
        this.a.a(cameraDevice);
        C0047t.D.release();
    }

    public final void onOpened(CameraDevice cameraDevice) {
        this.a.b = cameraDevice;
        C0047t.D.release();
    }
}
