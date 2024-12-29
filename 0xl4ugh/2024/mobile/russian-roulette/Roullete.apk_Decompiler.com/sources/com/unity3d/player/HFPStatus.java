package com.unity3d.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;

public class HFPStatus {
    private Context a;
    private BroadcastReceiver b = null;
    /* access modifiers changed from: private */
    public boolean c = false;
    /* access modifiers changed from: private */
    public AudioManager d = null;
    private boolean e = false;
    /* access modifiers changed from: private */
    public int f = 1;

    public HFPStatus(Context context) {
        this.a = context;
        this.d = (AudioManager) context.getSystemService("audio");
        initHFPStatusJni();
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.e) {
            this.e = false;
            this.d.stopBluetoothSco();
        }
    }

    private final native void deinitHFPStatusJni();

    private final native void initHFPStatusJni();

    public final void b() {
        clearHFPStat();
        deinitHFPStatusJni();
    }

    /* access modifiers changed from: protected */
    public void clearHFPStat() {
        BroadcastReceiver broadcastReceiver = this.b;
        if (broadcastReceiver != null) {
            this.a.unregisterReceiver(broadcastReceiver);
            this.b = null;
        }
        this.f = 1;
        a();
    }

    /* access modifiers changed from: protected */
    public boolean getHFPStat() {
        return this.f == 2;
    }

    /* access modifiers changed from: protected */
    public void requestHFPStat() {
        clearHFPStat();
        AnonymousClass1 r0 = new BroadcastReceiver() {
            public final void onReceive(Context context, Intent intent) {
                if (intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1) == 1) {
                    HFPStatus hFPStatus = HFPStatus.this;
                    hFPStatus.f = 2;
                    hFPStatus.a();
                    HFPStatus hFPStatus2 = HFPStatus.this;
                    if (hFPStatus2.c) {
                        hFPStatus2.d.setMode(3);
                    }
                }
            }
        };
        this.b = r0;
        this.a.registerReceiver(r0, new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED"));
        try {
            this.e = true;
            this.d.startBluetoothSco();
        } catch (NullPointerException unused) {
            C0055x.Log(5, "startBluetoothSco() failed. no bluetooth device connected.");
        }
    }

    /* access modifiers changed from: protected */
    public void setHFPRecordingStat(boolean z) {
        this.c = z;
        if (!z) {
            this.d.setMode(0);
        }
    }
}
