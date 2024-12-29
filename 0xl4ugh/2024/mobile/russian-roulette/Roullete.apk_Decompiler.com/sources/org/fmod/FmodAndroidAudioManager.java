package org.fmod;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;

public class FmodAndroidAudioManager {
    private static FmodAndroidAudioManager f;
    private volatile Activity a = null;
    private volatile AudioManager b = null;
    private BroadcastReceiver c = null;
    private boolean d = false;
    /* access modifiers changed from: private */
    public boolean e = false;

    private FmodAndroidAudioManager() {
    }

    public static FmodAndroidAudioManager getInstance() {
        if (f == null) {
            f = new FmodAndroidAudioManager();
        }
        return f;
    }

    public int getBluetoothInputDeviceId() {
        for (AudioDeviceInfo audioDeviceInfo : this.b.getDevices(1)) {
            if (audioDeviceInfo.getType() == 7) {
                return audioDeviceInfo.getId();
            }
        }
        return -1;
    }

    public boolean isBluetoothInputDeviceAvailable() {
        for (AudioDeviceInfo type : this.b.getDevices(1)) {
            if (type.getType() == 7) {
                return true;
            }
        }
        return false;
    }

    public boolean isBluetoothScoConnected() {
        return this.e;
    }

    public boolean isBuiltinInputDeviceAvailable() {
        for (AudioDeviceInfo type : this.b.getDevices(1)) {
            if (type.getType() == 15) {
                return true;
            }
        }
        return false;
    }

    public boolean isBuiltinSpeakerDevice(int i) {
        for (AudioDeviceInfo audioDeviceInfo : this.b.getDevices(2)) {
            if (audioDeviceInfo.getId() == i && audioDeviceInfo.getType() == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isInputSampleRateAvailable(int i) {
        for (AudioDeviceInfo audioDeviceInfo : this.b.getDevices(1)) {
            if ((this.d && audioDeviceInfo.getType() == 7) || (!this.d && audioDeviceInfo.getType() == 15)) {
                for (int i2 : audioDeviceInfo.getSampleRates()) {
                    if (i2 == i) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.media.AudioManager} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setActivity(android.app.Activity r4) {
        /*
            r3 = this;
            android.app.Activity r0 = r3.a
            if (r0 == r4) goto L_0x0028
            android.content.BroadcastReceiver r0 = r3.c
            r1 = 0
            if (r0 == 0) goto L_0x0015
            android.app.Activity r0 = r3.a
            android.content.BroadcastReceiver r2 = r3.c
            r0.unregisterReceiver(r2)
            r3.c = r1
            r0 = 0
            r3.e = r0
        L_0x0015:
            r3.a = r4
            android.app.Activity r4 = r3.a
            if (r4 == 0) goto L_0x0026
            android.app.Activity r4 = r3.a
            java.lang.String r0 = "audio"
            java.lang.Object r4 = r4.getSystemService(r0)
            r1 = r4
            android.media.AudioManager r1 = (android.media.AudioManager) r1
        L_0x0026:
            r3.b = r1
        L_0x0028:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fmod.FmodAndroidAudioManager.setActivity(android.app.Activity):void");
    }

    public void setUseBluetooth(boolean z) {
        this.d = z;
    }

    public void startBluetoothSco() {
        if (this.c == null) {
            this.c = new BroadcastReceiver() {
                public final void onReceive(Context context, Intent intent) {
                    boolean z;
                    FmodAndroidAudioManager fmodAndroidAudioManager;
                    int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                    if (intExtra != 0) {
                        z = true;
                        if (intExtra == 1) {
                            fmodAndroidAudioManager = FmodAndroidAudioManager.this;
                        } else {
                            return;
                        }
                    } else {
                        fmodAndroidAudioManager = FmodAndroidAudioManager.this;
                        z = false;
                    }
                    boolean unused = fmodAndroidAudioManager.e = z;
                }
            };
            this.a.registerReceiver(this.c, new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED"));
        }
        this.b.startBluetoothSco();
    }

    public void stopBluetoothSco() {
        this.b.stopBluetoothSco();
    }
}
