package com.unity3d.player;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;

/* renamed from: com.unity3d.player.p  reason: case insensitive filesystem */
final class C0039p extends CameraCaptureSession.StateCallback {
    final /* synthetic */ C0047t a;

    C0039p(C0047t tVar) {
        this.a = tVar;
    }

    public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        C0055x.Log(6, "Camera2: CaptureSession configuration failed.");
    }

    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
        String str;
        C0047t tVar = this.a;
        if (tVar.b != null) {
            synchronized (tVar.s) {
                C0047t tVar2 = this.a;
                tVar2.r = cameraCaptureSession;
                try {
                    tVar2.q = tVar2.b.createCaptureRequest(1);
                    C0047t tVar3 = this.a;
                    tVar3.q.addTarget(tVar3.v);
                    C0047t tVar4 = this.a;
                    tVar4.q.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, tVar4.n);
                    this.a.g();
                } catch (CameraAccessException e) {
                    str = "Camera2: CameraAccessException " + e;
                } catch (IllegalStateException e2) {
                    str = "Camera2: IllegalStateException " + e2;
                }
            }
        }
        return;
        C0055x.Log(6, str);
    }
}
