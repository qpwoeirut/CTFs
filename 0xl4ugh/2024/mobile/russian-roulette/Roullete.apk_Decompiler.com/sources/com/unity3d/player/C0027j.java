package com.unity3d.player;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.assetpacks.AssetPackException;
import com.google.android.play.core.assetpacks.AssetPackState;
import com.google.android.play.core.assetpacks.AssetPackStates;
import java.util.Map;

/* renamed from: com.unity3d.player.j  reason: case insensitive filesystem */
final class C0027j implements OnCompleteListener {
    private IAssetPackManagerStatusQueryCallback a;
    private UnityPlayer b;
    private String[] c;

    public C0027j(UnityPlayer unityPlayer, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback, String[] strArr) {
        this.b = unityPlayer;
        this.a = iAssetPackManagerStatusQueryCallback;
        this.c = strArr;
    }

    public final void onComplete(Task task) {
        int errorCode;
        if (this.a != null) {
            int i = 0;
            try {
                AssetPackStates assetPackStates = (AssetPackStates) task.getResult();
                Map packStates = assetPackStates.packStates();
                int size = packStates.size();
                String[] strArr = new String[size];
                int[] iArr = new int[size];
                int[] iArr2 = new int[size];
                for (AssetPackState assetPackState : packStates.values()) {
                    strArr[i] = assetPackState.name();
                    iArr[i] = assetPackState.status();
                    iArr2[i] = assetPackState.errorCode();
                    i++;
                }
                this.b.invokeOnMainThread((Runnable) new C0024i(this.a, assetPackStates.totalBytes(), strArr, iArr, iArr2));
            } catch (RuntimeExecutionException e) {
                e = e;
                String message = e.getMessage();
                String[] strArr2 = this.c;
                int length = strArr2.length;
                int i2 = 0;
                while (true) {
                    int i3 = -100;
                    if (i2 < length) {
                        String str = strArr2[i2];
                        if (message.contains(str)) {
                            UnityPlayer unityPlayer = this.b;
                            IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback = this.a;
                            String[] strArr3 = {str};
                            int[] iArr3 = {0};
                            int[] iArr4 = new int[1];
                            while (true) {
                                if (!(e instanceof AssetPackException)) {
                                    e = e.getCause();
                                    if (e == null) {
                                        break;
                                    }
                                } else {
                                    i3 = ((AssetPackException) e).getErrorCode();
                                    break;
                                }
                            }
                            iArr4[0] = i3;
                            unityPlayer.invokeOnMainThread((Runnable) new C0024i(iAssetPackManagerStatusQueryCallback, 0, strArr3, iArr3, iArr4));
                            return;
                        }
                        i2++;
                    } else {
                        String[] strArr4 = this.c;
                        int[] iArr5 = new int[strArr4.length];
                        int[] iArr6 = new int[strArr4.length];
                        int i4 = 0;
                        while (true) {
                            String[] strArr5 = this.c;
                            if (i4 < strArr5.length) {
                                iArr5[i4] = 0;
                                Throwable th = e;
                                while (true) {
                                    if (!(th instanceof AssetPackException)) {
                                        th = th.getCause();
                                        if (th == null) {
                                            errorCode = -100;
                                            break;
                                        }
                                    } else {
                                        errorCode = ((AssetPackException) th).getErrorCode();
                                        break;
                                    }
                                }
                                iArr6[i4] = errorCode;
                                i4++;
                            } else {
                                this.b.invokeOnMainThread((Runnable) new C0024i(this.a, 0, strArr5, iArr5, iArr6));
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
