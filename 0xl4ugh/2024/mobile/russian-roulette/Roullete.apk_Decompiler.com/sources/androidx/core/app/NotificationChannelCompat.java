package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;
import androidx.core.util.Preconditions;

public class NotificationChannelCompat {
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
    private static final int DEFAULT_LIGHT_COLOR = 0;
    private static final boolean DEFAULT_SHOW_BADGE = true;
    AudioAttributes mAudioAttributes;
    private boolean mBypassDnd;
    private boolean mCanBubble;
    String mConversationId;
    String mDescription;
    String mGroupId;
    final String mId;
    int mImportance;
    private boolean mImportantConversation;
    int mLightColor;
    boolean mLights;
    private int mLockscreenVisibility;
    CharSequence mName;
    String mParentId;
    boolean mShowBadge;
    Uri mSound;
    boolean mVibrationEnabled;
    long[] mVibrationPattern;

    public static class Builder {
        private final NotificationChannelCompat mChannel;

        public Builder(String str, int i) {
            this.mChannel = new NotificationChannelCompat(str, i);
        }

        public Builder setName(CharSequence charSequence) {
            this.mChannel.mName = charSequence;
            return this;
        }

        public Builder setImportance(int i) {
            this.mChannel.mImportance = i;
            return this;
        }

        public Builder setDescription(String str) {
            this.mChannel.mDescription = str;
            return this;
        }

        public Builder setGroup(String str) {
            this.mChannel.mGroupId = str;
            return this;
        }

        public Builder setShowBadge(boolean z) {
            this.mChannel.mShowBadge = z;
            return this;
        }

        public Builder setSound(Uri uri, AudioAttributes audioAttributes) {
            this.mChannel.mSound = uri;
            this.mChannel.mAudioAttributes = audioAttributes;
            return this;
        }

        public Builder setLightsEnabled(boolean z) {
            this.mChannel.mLights = z;
            return this;
        }

        public Builder setLightColor(int i) {
            this.mChannel.mLightColor = i;
            return this;
        }

        public Builder setVibrationEnabled(boolean z) {
            this.mChannel.mVibrationEnabled = z;
            return this;
        }

        public Builder setVibrationPattern(long[] jArr) {
            this.mChannel.mVibrationEnabled = (jArr == null || jArr.length <= 0) ? false : NotificationChannelCompat.DEFAULT_SHOW_BADGE;
            this.mChannel.mVibrationPattern = jArr;
            return this;
        }

        public Builder setConversationId(String str, String str2) {
            if (Build.VERSION.SDK_INT >= 30) {
                this.mChannel.mParentId = str;
                this.mChannel.mConversationId = str2;
            }
            return this;
        }

        public NotificationChannelCompat build() {
            return this.mChannel;
        }
    }

    NotificationChannelCompat(String str, int i) {
        this.mShowBadge = DEFAULT_SHOW_BADGE;
        this.mSound = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.mLightColor = 0;
        this.mId = (String) Preconditions.checkNotNull(str);
        this.mImportance = i;
        this.mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
    }

    NotificationChannelCompat(NotificationChannel notificationChannel) {
        this(ComponentDialog$$ExternalSyntheticApiModelOutline0.m(notificationChannel), ComponentDialog$$ExternalSyntheticApiModelOutline0.m$1(notificationChannel));
        this.mName = ComponentDialog$$ExternalSyntheticApiModelOutline0.m(notificationChannel);
        this.mDescription = ComponentDialog$$ExternalSyntheticApiModelOutline0.m$3(notificationChannel);
        this.mGroupId = ComponentDialog$$ExternalSyntheticApiModelOutline0.m$4(notificationChannel);
        this.mShowBadge = ComponentDialog$$ExternalSyntheticApiModelOutline0.m$4(notificationChannel);
        this.mSound = ComponentDialog$$ExternalSyntheticApiModelOutline0.m(notificationChannel);
        this.mAudioAttributes = ComponentDialog$$ExternalSyntheticApiModelOutline0.m(notificationChannel);
        this.mLights = notificationChannel.shouldShowLights();
        this.mLightColor = ComponentDialog$$ExternalSyntheticApiModelOutline0.m$2(notificationChannel);
        this.mVibrationEnabled = ComponentDialog$$ExternalSyntheticApiModelOutline0.m(notificationChannel);
        this.mVibrationPattern = ComponentDialog$$ExternalSyntheticApiModelOutline0.m(notificationChannel);
        if (Build.VERSION.SDK_INT >= 30) {
            this.mParentId = ComponentDialog$$ExternalSyntheticApiModelOutline0.m$1(notificationChannel);
            this.mConversationId = ComponentDialog$$ExternalSyntheticApiModelOutline0.m$2(notificationChannel);
        }
        this.mBypassDnd = ComponentDialog$$ExternalSyntheticApiModelOutline0.m$1(notificationChannel);
        this.mLockscreenVisibility = ComponentDialog$$ExternalSyntheticApiModelOutline0.m(notificationChannel);
        if (Build.VERSION.SDK_INT >= 29) {
            this.mCanBubble = ComponentDialog$$ExternalSyntheticApiModelOutline0.m$2(notificationChannel);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImportantConversation = ComponentDialog$$ExternalSyntheticApiModelOutline0.m$3(notificationChannel);
        }
    }

    /* access modifiers changed from: package-private */
    public NotificationChannel getNotificationChannel() {
        String str;
        String str2;
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        ComponentDialog$$ExternalSyntheticApiModelOutline0.m();
        NotificationChannel m = ComponentDialog$$ExternalSyntheticApiModelOutline0.m(this.mId, this.mName, this.mImportance);
        m.setDescription(this.mDescription);
        m.setGroup(this.mGroupId);
        m.setShowBadge(this.mShowBadge);
        m.setSound(this.mSound, this.mAudioAttributes);
        m.enableLights(this.mLights);
        m.setLightColor(this.mLightColor);
        m.setVibrationPattern(this.mVibrationPattern);
        m.enableVibration(this.mVibrationEnabled);
        if (!(Build.VERSION.SDK_INT < 30 || (str = this.mParentId) == null || (str2 = this.mConversationId) == null)) {
            m.setConversationId(str, str2);
        }
        return m;
    }

    public Builder toBuilder() {
        return new Builder(this.mId, this.mImportance).setName(this.mName).setDescription(this.mDescription).setGroup(this.mGroupId).setShowBadge(this.mShowBadge).setSound(this.mSound, this.mAudioAttributes).setLightsEnabled(this.mLights).setLightColor(this.mLightColor).setVibrationEnabled(this.mVibrationEnabled).setVibrationPattern(this.mVibrationPattern).setConversationId(this.mParentId, this.mConversationId);
    }

    public String getId() {
        return this.mId;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getImportance() {
        return this.mImportance;
    }

    public Uri getSound() {
        return this.mSound;
    }

    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public boolean shouldShowLights() {
        return this.mLights;
    }

    public int getLightColor() {
        return this.mLightColor;
    }

    public boolean shouldVibrate() {
        return this.mVibrationEnabled;
    }

    public long[] getVibrationPattern() {
        return this.mVibrationPattern;
    }

    public boolean canShowBadge() {
        return this.mShowBadge;
    }

    public String getGroup() {
        return this.mGroupId;
    }

    public String getParentChannelId() {
        return this.mParentId;
    }

    public String getConversationId() {
        return this.mConversationId;
    }

    public boolean canBypassDnd() {
        return this.mBypassDnd;
    }

    public int getLockscreenVisibility() {
        return this.mLockscreenVisibility;
    }

    public boolean canBubble() {
        return this.mCanBubble;
    }

    public boolean isImportantConversation() {
        return this.mImportantConversation;
    }
}
