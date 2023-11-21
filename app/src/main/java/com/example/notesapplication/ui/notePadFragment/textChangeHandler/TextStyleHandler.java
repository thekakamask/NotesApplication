package com.example.notesapplication.ui.notePadFragment.textChangeHandler;

import android.graphics.Typeface;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class TextStyleHandler {


    public static void applyBold(SpannableString spannableString, int start, int end) {
        StyleSpan[] spans = spannableString.getSpans(start, end, StyleSpan.class);
        boolean isBold = false;
        for (StyleSpan span : spans) {
            if (span.getStyle() == Typeface.BOLD) {
                spannableString.removeSpan(span);
                isBold = true;
            }
        }

        if (!isBold) {
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    public static void applyItalic(SpannableString spannableString, int start, int end) {
        StyleSpan[] spans = spannableString.getSpans(start, end, StyleSpan.class);
        boolean isItalic = false;
        for (StyleSpan span : spans) {
            if (span.getStyle() == Typeface.ITALIC) {
                spannableString.removeSpan(span);
                isItalic = true;
            }
        }

        if (!isItalic) {
            spannableString.setSpan(new StyleSpan(Typeface.ITALIC), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    public static void applyUnderline(SpannableString spannableString, int start, int end) {
        UnderlineSpan[] spans = spannableString.getSpans(start, end, UnderlineSpan.class);
        boolean isUnderlined = false;
        for (UnderlineSpan span : spans) {
            spannableString.removeSpan(span);
            isUnderlined = true;
        }

        if (!isUnderlined) {
            spannableString.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

}
