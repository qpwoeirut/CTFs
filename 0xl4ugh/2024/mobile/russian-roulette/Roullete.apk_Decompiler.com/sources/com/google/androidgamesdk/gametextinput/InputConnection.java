package com.google.androidgamesdk.gametextinput;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.graphics.Insets;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.androidgamesdk.gametextinput.GameTextInput;
import java.util.BitSet;
import kotlinx.coroutines.scheduling.WorkQueueKt;

public class InputConnection extends BaseInputConnection implements View.OnKeyListener {
    private static final int MAX_LENGTH_FOR_SINGLE_LINE_EDIT_TEXT = 5000;
    private static final String TAG = "gti.InputConnection";
    private static final int[] notInsertedKeyCodes = {23, 20, 19, 21, 22, 269, 268, 268, 270, 96, 97, 99, 100, LocationRequestCompat.QUALITY_BALANCED_POWER_ACCURACY, LocationRequestCompat.QUALITY_LOW_POWER, 103, 105, 106, 107, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 110, 130, TypedValues.TYPE_TARGET, 98, 67, 112, 114, 59, 60, 4, 24, 25, 164, 57, 58, 113, 131, 140, 141, 142, 132, 133, 134, 135, 136, 137, 138, 139, 124, 122, 123, 92, 93, 0, 84, 85, 86, 87, 88, 89, 90, 126, WorkQueueKt.MASK, 128, 129, 130, 222, 226, 257, 272, 273, 274, 275};
    private final BitSet dontInsertChars;
    private final InputMethodManager imm;
    private Listener listener;
    private final Editable mEditable;
    private boolean mSoftKeyboardActive;
    private final Settings settings;
    private final View targetView;

    private class SingeLineFilter implements InputFilter {
        private SingeLineFilter() {
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            StringBuilder sb = new StringBuilder(i2 - i);
            boolean z = true;
            for (int i5 = i; i5 < i2; i5++) {
                char charAt = charSequence.charAt(i5);
                if (charAt == 10) {
                    z = false;
                } else {
                    sb.append(charAt);
                }
            }
            if (z) {
                return null;
            }
            if (!(charSequence instanceof Spanned)) {
                return sb;
            }
            SpannableString spannableString = new SpannableString(sb);
            TextUtils.copySpansFrom((Spanned) charSequence, i, sb.length(), (Class) null, spannableString, 0);
            return spannableString;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InputConnection(Context context, View view, Settings settings2) {
        super(view, settings2.mEditorInfo.inputType != 0);
        Log.d(TAG, "InputConnection created");
        this.targetView = view;
        this.settings = settings2;
        Object systemService = context.getSystemService("input_method");
        if (systemService != null) {
            this.imm = (InputMethodManager) systemService;
            this.mEditable = new SpannableStringBuilder();
            this.dontInsertChars = new BitSet();
            for (int i : notInsertedKeyCodes) {
                this.dontInsertChars.set(i);
            }
            WindowCompat.setDecorFitsSystemWindows(((Activity) view.getContext()).getWindow(), false);
            view.setOnKeyListener(this);
            setEditorInfo(settings2.mEditorInfo);
            return;
        }
        throw new RuntimeException("Can't get IMM");
    }

    public void restartInput() {
        this.imm.restartInput(this.targetView);
    }

    public final boolean getSoftKeyboardActive() {
        return this.mSoftKeyboardActive;
    }

    public final void setSoftKeyboardActive(boolean z, int i) {
        if (z) {
            this.targetView.setFocusableInTouchMode(true);
            this.targetView.requestFocus();
            this.imm.showSoftInput(this.targetView, i);
            return;
        }
        this.imm.hideSoftInputFromWindow(this.targetView.getWindowToken(), i);
    }

    public final EditorInfo getEditorInfo() {
        return this.settings.mEditorInfo;
    }

    public final void setEditorInfo(EditorInfo editorInfo) {
        this.settings.mEditorInfo = editorInfo;
        if ((this.settings.mEditorInfo.inputType & 131072) == 0) {
            this.mEditable.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_LENGTH_FOR_SINGLE_LINE_EDIT_TEXT), new SingeLineFilter()});
            return;
        }
        this.mEditable.setFilters(new InputFilter[0]);
    }

    public final void setState(State state) {
        if (state != null) {
            Log.d(TAG, "setState: '" + state.text + "', selection=(" + state.selectionStart + "," + state.selectionEnd + "), composing region=(" + state.composingRegionStart + "," + state.composingRegionEnd + ")");
            this.mEditable.clear();
            this.mEditable.insert(0, state.text);
            setSelectionInternal(state.selectionStart, state.selectionEnd);
            setComposingRegionInternal(state.composingRegionStart, state.composingRegionEnd);
            informIMM();
        }
    }

    public final Listener getListener() {
        return this.listener;
    }

    public final InputConnection setListener(Listener listener2) {
        this.listener = listener2;
        return this;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return processKeyEvent(keyEvent);
    }

    public Editable getEditable() {
        Log.d(TAG, "getEditable ");
        return this.mEditable;
    }

    public boolean setSelection(int i, int i2) {
        Log.d(TAG, "setSelection: " + i + ":" + i2);
        setSelectionInternal(i, i2);
        return true;
    }

    public boolean setComposingText(CharSequence charSequence, int i) {
        int i2;
        Log.d(TAG, String.format("setComposingText='%s' newCursorPosition=%d", new Object[]{charSequence, Integer.valueOf(i)}));
        if (charSequence == null) {
            return false;
        }
        GameTextInput.Pair composingRegion = getComposingRegion();
        if (composingRegion.first == -1) {
            composingRegion = getSelection();
            if (composingRegion.first == -1) {
                composingRegion = new GameTextInput.Pair(0, 0);
            }
        }
        this.mEditable.delete(composingRegion.first, composingRegion.second);
        this.mEditable.insert(composingRegion.first, charSequence);
        setComposingRegion(composingRegion.first, composingRegion.first + charSequence.length());
        GameTextInput.Pair composingRegion2 = getComposingRegion();
        if (i > 0) {
            i2 = Math.min((composingRegion2.second + i) - 1, this.mEditable.length());
        } else {
            i2 = Math.max(0, composingRegion2.first + i);
        }
        setSelection(i2, i2);
        stateUpdated(false);
        return true;
    }

    public boolean setComposingRegion(int i, int i2) {
        Log.d(TAG, "setComposingRegion: " + i + ":" + i2);
        setComposingRegionInternal(i, i2);
        stateUpdated(false);
        return true;
    }

    public boolean finishComposingText() {
        Log.d(TAG, "finishComposingText");
        setComposingRegion(-1, -1);
        return true;
    }

    public boolean commitText(CharSequence charSequence, int i) {
        Log.d(TAG, "Commit: " + charSequence + ", new pos = " + i);
        setComposingText(charSequence, i);
        finishComposingText();
        informIMM();
        return true;
    }

    public boolean deleteSurroundingText(int i, int i2) {
        int i3;
        int i4;
        Log.d(TAG, "deleteSurroundingText: " + i + ":" + i2);
        GameTextInput.Pair selection = getSelection();
        int min = Math.min(selection.first, selection.second);
        int max = Math.max(selection.first, selection.second);
        if (min < max) {
            i4 = Math.max(0, min);
            i3 = Math.min(max, this.mEditable.length());
        } else {
            i4 = Math.max(0, min - i);
            i3 = Math.min(max + i2, this.mEditable.length());
        }
        Log.d(TAG, String.format("deleteSurroundingText: deleting text from %d to %d", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)}));
        this.mEditable.delete(i4, i3);
        stateUpdated(false);
        return true;
    }

    public boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        Log.d(TAG, "deleteSurroundingTextInCodePoints: " + i + ":" + i2);
        boolean deleteSurroundingTextInCodePoints = super.deleteSurroundingTextInCodePoints(i, i2);
        stateUpdated(false);
        return deleteSurroundingTextInCodePoints;
    }

    public boolean sendKeyEvent(KeyEvent keyEvent) {
        if (this.settings.mForwardKeyEvents && Build.VERSION.SDK_INT >= 24 && this.settings.mEditorInfo.inputType == 0 && keyEvent != null) {
            this.imm.dispatchKeyEventFromInputMethod(this.targetView, keyEvent);
        }
        return processKeyEvent(keyEvent);
    }

    public CharSequence getSelectedText(int i) {
        Log.d(TAG, "getSelectedText: " + i);
        return super.getSelectedText(i);
    }

    public CharSequence getTextAfterCursor(int i, int i2) {
        Log.d(TAG, "getTextAfterCursor: " + i + ":" + i2);
        if (i >= 0) {
            return super.getTextAfterCursor(i, i2);
        }
        Log.i(TAG, "getTextAfterCursor: returning null to due to an invalid length=" + i);
        return null;
    }

    public CharSequence getTextBeforeCursor(int i, int i2) {
        Log.d(TAG, "getTextBeforeCursor: " + i + ", flags=" + i2);
        if (i >= 0) {
            return super.getTextBeforeCursor(i, i2);
        }
        Log.i(TAG, "getTextBeforeCursor: returning null to due to an invalid length=" + i);
        return null;
    }

    public boolean requestCursorUpdates(int i) {
        Log.d(TAG, "Request cursor updates: " + i);
        return super.requestCursorUpdates(i);
    }

    public void closeConnection() {
        Log.d(TAG, "closeConnection");
        super.closeConnection();
    }

    private final void informIMM() {
        GameTextInput.Pair selection = getSelection();
        GameTextInput.Pair composingRegion = getComposingRegion();
        this.imm.updateSelection(this.targetView, selection.first, selection.second, composingRegion.first, composingRegion.second);
    }

    private final GameTextInput.Pair getSelection() {
        return GameTextInput.getSelection(this.mEditable);
    }

    private final GameTextInput.Pair getComposingRegion() {
        return GameTextInput.getComposingRegion(this.mEditable);
    }

    private final void setSelectionInternal(int i, int i2) {
        GameTextInput.setSelection(this.mEditable, i, i2);
    }

    private final void setComposingRegionInternal(int i, int i2) {
        int min = Math.min(i, i2);
        int max = Math.max(i, i2);
        if (min == -1) {
            GameTextInput.removeComposingRegion(this.mEditable);
            return;
        }
        GameTextInput.setComposingRegion(this.mEditable, Math.min(this.mEditable.length(), Math.max(0, min)), Math.min(this.mEditable.length(), Math.max(0, max)));
    }

    private boolean processKeyEvent(KeyEvent keyEvent) {
        boolean z;
        Log.d(TAG, String.format("processKeyEvent(key=%d) text=%s", new Object[]{Integer.valueOf(keyEvent.getKeyCode()), this.mEditable.toString()}));
        if ((this.settings.mEditorInfo.inputType & 131072) != 0 || (!(keyEvent.getKeyCode() == 66 || keyEvent.getKeyCode() == 160) || !keyEvent.hasNoModifiers())) {
            GameTextInput.Pair selection = getSelection();
            if (keyEvent == null) {
                return false;
            }
            if (keyEvent.getAction() != 0) {
                return true;
            }
            if (selection.first == -1) {
                selection.first = this.mEditable.length();
                selection.second = this.mEditable.length();
            }
            if (selection.first != selection.second) {
                Log.d(TAG, String.format("processKeyEvent: deleting selection", new Object[0]));
                this.mEditable.delete(selection.first, selection.second);
                z = true;
            } else if (keyEvent.getKeyCode() == 67 && selection.first > 0) {
                this.mEditable.delete(selection.first - 1, selection.first);
                stateUpdated(false);
                Log.d(TAG, String.format("processKeyEvent: exit after DEL, text=%s", new Object[]{this.mEditable.toString()}));
                return true;
            } else if (keyEvent.getKeyCode() != 112 || selection.first >= this.mEditable.length()) {
                z = false;
            } else {
                this.mEditable.delete(selection.first, selection.first + 1);
                stateUpdated(false);
                Log.d(TAG, String.format("processKeyEvent: exit after FORWARD_DEL, text=%s", new Object[]{this.mEditable.toString()}));
                return true;
            }
            if (!this.dontInsertChars.get(keyEvent.getKeyCode())) {
                String ch = Character.toString((char) keyEvent.getUnicodeChar());
                this.mEditable.insert(selection.first, ch);
                int length = this.mEditable.length();
                GameTextInput.Pair composingRegion = getComposingRegion();
                if (composingRegion.first == -1) {
                    composingRegion = getSelection();
                    if (composingRegion.first == -1) {
                        composingRegion = new GameTextInput.Pair(0, 0);
                    }
                }
                composingRegion.second = composingRegion.first + length;
                setComposingRegion(composingRegion.first, composingRegion.second);
                int length2 = selection.first + ch.length();
                setSelectionInternal(length2, length2);
                informIMM();
                restartInput();
                z = true;
            }
            if (z) {
                Log.d(TAG, String.format("processKeyEvent: exit, text=%s", new Object[]{this.mEditable.toString()}));
                stateUpdated(false);
            }
            return z;
        }
        performEditorAction(this.settings.mEditorInfo.actionId);
        return true;
    }

    private final void stateUpdated(boolean z) {
        GameTextInput.Pair selection = getSelection();
        GameTextInput.Pair composingRegion = getComposingRegion();
        State state = new State(this.mEditable.toString(), selection.first, selection.second, composingRegion.first, composingRegion.second);
        Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.stateChanged(state, z);
        }
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        Log.d(TAG, "onApplyWindowInsets" + isSoftwareKeyboardVisible());
        Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onImeInsetsChanged(windowInsetsCompat.getInsets(WindowInsetsCompat.Type.ime()));
        }
        boolean isSoftwareKeyboardVisible = isSoftwareKeyboardVisible();
        if (isSoftwareKeyboardVisible == this.mSoftKeyboardActive) {
            return windowInsetsCompat;
        }
        this.mSoftKeyboardActive = isSoftwareKeyboardVisible;
        if (!isSoftwareKeyboardVisible && Build.VERSION.SDK_INT >= 26) {
            this.targetView.clearFocus();
        }
        Listener listener3 = this.listener;
        if (listener3 != null) {
            listener3.onSoftwareKeyboardVisibilityChanged(isSoftwareKeyboardVisible);
        }
        return windowInsetsCompat;
    }

    public Insets getImeInsets() {
        View view = this.targetView;
        if (view == null) {
            return Insets.NONE;
        }
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
        if (rootWindowInsets == null) {
            return Insets.NONE;
        }
        return rootWindowInsets.getInsets(WindowInsetsCompat.Type.ime());
    }

    public boolean isSoftwareKeyboardVisible() {
        WindowInsetsCompat rootWindowInsets;
        View view = this.targetView;
        if (view == null || (rootWindowInsets = ViewCompat.getRootWindowInsets(view)) == null) {
            return false;
        }
        return rootWindowInsets.isVisible(WindowInsetsCompat.Type.ime());
    }

    public boolean performEditorAction(int i) {
        Listener listener2 = this.listener;
        if (listener2 == null) {
            return false;
        }
        listener2.onEditorAction(i);
        return true;
    }
}
