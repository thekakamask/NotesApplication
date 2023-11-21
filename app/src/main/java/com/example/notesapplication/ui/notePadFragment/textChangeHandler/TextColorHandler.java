package com.example.notesapplication.ui.notePadFragment.textChangeHandler;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.notesapplication.R;

public class TextColorHandler {

    public static int getColorFromButtonId(int buttonId) {
        if (buttonId == R.id.button_color_red) {
            return Color.RED;
        } else if (buttonId == R.id.button_color_white) {
            return Color.WHITE;
        } else if (buttonId == R.id.button_color_black) {
            return Color.BLACK;
        } else if (buttonId == R.id.button_color_blue) {
            return Color.BLUE;
        } else {
            return Color.BLACK; // Valeur par défaut
        }
    }

    public static void applyColorToTextSelection(SpannableString spannableString, int start, int end, int color) {
        ForegroundColorSpan[] spans = spannableString.getSpans(start, end, ForegroundColorSpan.class);
        for (ForegroundColorSpan span : spans) {
            if (spannableString.getSpanStart(span) >= start && spannableString.getSpanEnd(span) <= end) {
                spannableString.removeSpan(span);
            }
        }
        spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


}
