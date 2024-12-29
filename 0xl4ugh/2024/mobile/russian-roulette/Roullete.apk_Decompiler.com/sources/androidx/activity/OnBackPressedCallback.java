package androidx.activity;

import androidx.core.util.Consumer;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class OnBackPressedCallback {
    private CopyOnWriteArrayList<Cancellable> mCancellables = new CopyOnWriteArrayList<>();
    private boolean mEnabled;
    private Consumer<Boolean> mEnabledConsumer;

    public abstract void handleOnBackPressed();

    public OnBackPressedCallback(boolean z) {
        this.mEnabled = z;
    }

    public final void setEnabled(boolean z) {
        this.mEnabled = z;
        Consumer<Boolean> consumer = this.mEnabledConsumer;
        if (consumer != null) {
            consumer.accept(Boolean.valueOf(z));
        }
    }

    public final boolean isEnabled() {
        return this.mEnabled;
    }

    public final void remove() {
        Iterator<Cancellable> it = this.mCancellables.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public void addCancellable(Cancellable cancellable) {
        this.mCancellables.add(cancellable);
    }

    /* access modifiers changed from: package-private */
    public void removeCancellable(Cancellable cancellable) {
        this.mCancellables.remove(cancellable);
    }

    /* access modifiers changed from: package-private */
    public void setIsEnabledConsumer(Consumer<Boolean> consumer) {
        this.mEnabledConsumer = consumer;
    }
}
