package com.unity3d.player;

import android.app.ApplicationExitInfo;
import bitter.jnibridge.a$$ExternalSyntheticApiModelOutline0;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

class ApplicationExitInfoWrapper extends ApplicationExitInfoBase {
    private ApplicationExitInfo mApplicationExitInfo;

    public ApplicationExitInfoWrapper(ApplicationExitInfo applicationExitInfo) {
        this.mApplicationExitInfo = applicationExitInfo;
    }

    protected static Object GetStaticFieldByReflection(Class cls, String str, Object obj, boolean z) {
        StringBuilder sb;
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (z) {
                declaredField.setAccessible(true);
            }
            return declaredField.get((Object) null);
        } catch (NoSuchFieldException e) {
            e = e;
            sb = new StringBuilder("ApplicationExitInfo: GetStaticFieldByReflection NoSuchFieldException ");
            sb.append(e);
            C0055x.Log(6, sb.toString());
            return obj;
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("ApplicationExitInfo: GetStaticFieldByReflection exception ");
            sb.append(e);
            C0055x.Log(6, sb.toString());
            return obj;
        }
    }

    private boolean checkSupport() {
        return this.mApplicationExitInfo != null && PlatformSupport.RED_VELVET_CAKE_SUPPORT;
    }

    private byte[] readAllBytes(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr, 0, 1024);
                if (read <= 0) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (IOException e) {
                C0055x.Log(6, "ApplicationExitInfo: readAllBytes exception " + e);
                throw e;
            }
        }
    }

    public int describeContents() {
        if (checkSupport()) {
            return this.mApplicationExitInfo.describeContents();
        }
        return 0;
    }

    public int getDefiningUid() {
        if (checkSupport()) {
            return this.mApplicationExitInfo.getDefiningUid();
        }
        return 0;
    }

    public String getDescription() {
        if (checkSupport()) {
            return a$$ExternalSyntheticApiModelOutline0.m$1(this.mApplicationExitInfo);
        }
        return null;
    }

    public int getImportance() {
        if (checkSupport()) {
            return this.mApplicationExitInfo.getImportance();
        }
        return 0;
    }

    public int getPackageUid() {
        if (checkSupport()) {
            return this.mApplicationExitInfo.getPackageUid();
        }
        return 0;
    }

    public int getPid() {
        if (checkSupport()) {
            return this.mApplicationExitInfo.getPid();
        }
        return 0;
    }

    public String getProcessName() {
        if (checkSupport()) {
            return a$$ExternalSyntheticApiModelOutline0.m(this.mApplicationExitInfo);
        }
        return null;
    }

    public byte[] getProcessStateSummary() {
        return checkSupport() ? a$$ExternalSyntheticApiModelOutline0.m(this.mApplicationExitInfo) : new byte[0];
    }

    public long getPss() {
        if (checkSupport()) {
            return a$$ExternalSyntheticApiModelOutline0.m$1(this.mApplicationExitInfo);
        }
        return 0;
    }

    public int getRealUid() {
        if (checkSupport()) {
            return a$$ExternalSyntheticApiModelOutline0.m(this.mApplicationExitInfo);
        }
        return 0;
    }

    public int getReason() {
        if (checkSupport()) {
            return a$$ExternalSyntheticApiModelOutline0.m$2(this.mApplicationExitInfo);
        }
        return 0;
    }

    public long getRss() {
        if (checkSupport()) {
            return a$$ExternalSyntheticApiModelOutline0.m(this.mApplicationExitInfo);
        }
        return 0;
    }

    public int getStatus() {
        if (checkSupport()) {
            return a$$ExternalSyntheticApiModelOutline0.m$1(this.mApplicationExitInfo);
        }
        return 0;
    }

    public long getTimestamp() {
        if (checkSupport()) {
            return a$$ExternalSyntheticApiModelOutline0.m$2(this.mApplicationExitInfo);
        }
        return 0;
    }

    public byte[] getTrace() {
        if (!checkSupport()) {
            return null;
        }
        try {
            return readAllBytes(a$$ExternalSyntheticApiModelOutline0.m(this.mApplicationExitInfo));
        } catch (IOException e) {
            C0055x.Log(6, "ApplicationExitInfo: getTrace exception " + e);
            return null;
        }
    }
}
