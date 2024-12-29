package com.unity3d.player;

import android.app.GameManager;
import android.content.Context;
import bitter.jnibridge.a$$ExternalSyntheticApiModelOutline0;

public class UnityGameManager {
    private static Object mGameManager;

    public static Object getGameManager(Context context) {
        String str;
        if (!PlatformSupport.SNOW_CONE_SUPPORT) {
            str = "getGameManager: API level not supported. API level 31 is required.";
        } else {
            Object obj = mGameManager;
            if (obj != null) {
                return obj;
            }
            if (context == null) {
                str = "UnityGame: Request for GameManager supplied with null context.";
            } else {
                Object systemService = context.getSystemService(a$$ExternalSyntheticApiModelOutline0.m$1());
                mGameManager = systemService;
                return systemService;
            }
        }
        C0055x.Log(6, str);
        return null;
    }

    public static int getGameMode(Context context) {
        String str;
        if (!PlatformSupport.SNOW_CONE_SUPPORT) {
            str = "getGameMode: API level not supported. API level 31 is required.";
        } else {
            GameManager m = a$$ExternalSyntheticApiModelOutline0.m(getGameManager(context));
            if (m != null) {
                return m.getGameMode();
            }
            str = "UnityGame: GameManager not available.";
        }
        C0055x.Log(6, str);
        return 0;
    }
}
