package com.google.androidgamesdk.gametextinput;

import androidx.core.graphics.Insets;

public interface Listener {
    void onEditorAction(int i);

    void onImeInsetsChanged(Insets insets);

    void onSoftwareKeyboardVisibilityChanged(boolean z);

    void stateChanged(State state, boolean z);
}
