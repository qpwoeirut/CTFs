package com.google.androidgamesdk.gametextinput;

import android.text.Editable;
import android.view.inputmethod.EditorInfo;

public final class GameTextInput {
    private static final GameTextInput composingRegionKey;
    private static final Class selectionKey;

    public static final void copyEditorInfo(EditorInfo editorInfo, EditorInfo editorInfo2) {
        if (editorInfo != null && editorInfo2 != null) {
            if (editorInfo.hintText != null) {
                editorInfo2.hintText = editorInfo.hintText;
            }
            editorInfo2.inputType = editorInfo.inputType;
            editorInfo2.imeOptions = editorInfo.imeOptions;
            editorInfo2.label = editorInfo.label;
            editorInfo2.initialCapsMode = editorInfo.initialCapsMode;
            editorInfo2.privateImeOptions = editorInfo.privateImeOptions;
            if (editorInfo.packageName != null) {
                editorInfo2.packageName = editorInfo.packageName;
            }
            editorInfo2.fieldId = editorInfo.fieldId;
            if (editorInfo.fieldName != null) {
                editorInfo2.fieldName = editorInfo.fieldName;
            }
        }
    }

    public static final class Pair {
        int first;
        int second;

        Pair(int i, int i2) {
            this.first = i;
            this.second = i2;
        }
    }

    public static final Pair getSelection(Editable editable) {
        Class cls = selectionKey;
        return new Pair(editable.getSpanStart(cls), editable.getSpanEnd(cls));
    }

    public static final Pair getComposingRegion(Editable editable) {
        GameTextInput gameTextInput = composingRegionKey;
        return new Pair(editable.getSpanStart(gameTextInput), editable.getSpanEnd(gameTextInput));
    }

    public static final void setSelection(Editable editable, int i, int i2) {
        if (i > editable.length()) {
            i = editable.length();
        }
        if (i2 > editable.length()) {
            i2 = editable.length();
        }
        if (i > i2) {
            editable.setSpan(selectionKey, i2, i, 0);
        } else {
            editable.setSpan(selectionKey, i, i2, 0);
        }
    }

    public static final void setComposingRegion(Editable editable, int i, int i2) {
        if (i > editable.length()) {
            i = editable.length();
        }
        if (i2 > editable.length()) {
            i2 = editable.length();
        }
        if (i > i2) {
            editable.setSpan(composingRegionKey, i2, i, 256);
        } else {
            editable.setSpan(composingRegionKey, i, i2, 256);
        }
    }

    public static final void removeComposingRegion(Editable editable) {
        editable.removeSpan(composingRegionKey);
    }

    public static final GameTextInput getComposingRegionKey() {
        return composingRegionKey;
    }

    public static final Class getSelectionKey() {
        return selectionKey;
    }

    private GameTextInput() {
    }

    static {
        GameTextInput gameTextInput = new GameTextInput();
        composingRegionKey = gameTextInput;
        selectionKey = gameTextInput.getClass();
    }
}
