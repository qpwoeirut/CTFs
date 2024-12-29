package com.unity3d.player;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.assetpacks.AssetPackException;
import com.google.android.play.core.assetpacks.AssetPackState;
import com.google.android.play.core.assetpacks.AssetPackStates;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;

/* renamed from: com.unity3d.player.h  reason: case insensitive filesystem */
final class C0021h implements OnCompleteListener {
    private IAssetPackManagerDownloadStatusCallback a;
    private UnityPlayer b;
    private String[] c;

    public C0021h(UnityPlayer unityPlayer, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback, String[] strArr) {
        this.b = unityPlayer;
        this.a = iAssetPackManagerDownloadStatusCallback;
        this.c = strArr;
    }

    public final void onComplete(Task task) {
        int errorCode;
        try {
            AssetPackStates assetPackStates = (AssetPackStates) task.getResult();
            Map packStates = assetPackStates.packStates();
            if (packStates.size() != 0) {
                Vector vector = new Vector();
                for (AssetPackState assetPackState : packStates.values()) {
                    if (assetPackState.errorCode() != 0 || assetPackState.status() == 4 || assetPackState.status() == 5 || assetPackState.status() == 0) {
                        String name = assetPackState.name();
                        int status = assetPackState.status();
                        int errorCode2 = assetPackState.errorCode();
                        long j = assetPackStates.totalBytes();
                        this.b.invokeOnMainThread((Runnable) new C0009d(Collections.singleton(this.a), name, status, j, status == 4 ? j : 0, 0, errorCode2));
                    } else {
                        vector.add(assetPackState.name());
                    }
                }
                if (vector.size() > 0) {
                    C0029k r15 = C0029k.e;
                    UnityPlayer unityPlayer = this.b;
                    IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback = this.a;
                    r15.getClass();
                    synchronized (C0029k.e) {
                        Object r4 = r15.d;
                        if (r4 == null) {
                            C0012e eVar = new C0012e(r15, unityPlayer, iAssetPackManagerDownloadStatusCallback);
                            r15.b.registerListener(eVar);
                            r15.d = eVar;
                        } else {
                            ((C0012e) r4).a(iAssetPackManagerDownloadStatusCallback);
                        }
                        r15.c.addAll(vector);
                        r15.b.fetch(vector);
                    }
                }
            }
        } catch (RuntimeExecutionException e) {
            e = e;
            String[] strArr = this.c;
            if (strArr.length == 1) {
                String str = strArr[0];
                while (true) {
                    if (!(e instanceof AssetPackException)) {
                        e = e.getCause();
                        if (e == null) {
                            errorCode = -100;
                            break;
                        }
                    } else {
                        errorCode = ((AssetPackException) e).getErrorCode();
                        break;
                    }
                }
                this.b.invokeOnMainThread((Runnable) new C0009d(Collections.singleton(this.a), str, 0, 0, 0, 0, errorCode));
                return;
            }
            C0029k r152 = C0029k.e;
            IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback2 = this.a;
            r152.getClass();
            for (String str2 : strArr) {
                r152.b.getPackStates(Collections.singletonList(str2)).addOnCompleteListener(new C0021h(r152.a, iAssetPackManagerDownloadStatusCallback2, new String[]{str2}));
            }
        }
    }
}
