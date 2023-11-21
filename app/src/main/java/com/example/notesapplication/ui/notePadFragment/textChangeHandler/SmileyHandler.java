package com.example.notesapplication.ui.notePadFragment.textChangeHandler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

public class SmileyHandler {

    private Context context;
    private EditText editText;

    public SmileyHandler(Context context, EditText editText) {
        this.context = context;
        this.editText = editText;
    }

    public void insertSmiley(int smileyResourceId) {
        Drawable smileyDrawable = ContextCompat.getDrawable(context, smileyResourceId);

        if (smileyDrawable != null) {
            smileyDrawable.setBounds(0, 0, smileyDrawable.getIntrinsicWidth(), smileyDrawable.getIntrinsicHeight());
            SpannableString spannableString = new SpannableString(" ");
            ImageSpan imageSpan = new ImageSpan(smileyDrawable, ImageSpan.ALIGN_BOTTOM);
            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            int start = editText.getSelectionStart();
            editText.getText().insert(start, spannableString);
        }
    }

}
