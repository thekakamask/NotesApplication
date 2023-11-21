package com.example.notesapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.example.notesapplication.databinding.FragmentNotePadBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotePadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotePadFragment extends Fragment {

    private int lastCheckedId = -1;


    private FragmentNotePadBinding binding;
    public static NotePadFragment newInstance() {
        NotePadFragment fragment = new NotePadFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotePadBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupColors();
        setupListeners();



    }

    private void setupListeners() {
        binding.buttonBold.setOnClickListener(v -> TextToBold());
        binding.buttonItalic.setOnClickListener(v -> TextToItalic());
        binding.buttonUnderline.setOnClickListener(v -> TextToUnderline());

        binding.buttonHide.setOnClickListener(v -> HideMenu());

        binding.buttonSmileyBigSmile.setOnClickListener(v -> SmileyBigSmileToText());
        binding.buttonSmileySmile.setOnClickListener(v -> SmileySmileToText());
        binding.buttonSmileyWink.setOnClickListener(v -> SmileyWinkToText());


        TextWatcher textWatcher = getTextWatcher();
        binding.editTextInput.addTextChangedListener(textWatcher);

        binding.buttonColorWhite.setOnCheckedChangeListener(this::onRadioButtonCheckedChanged);
        binding.buttonColorRed.setOnCheckedChangeListener(this::onRadioButtonCheckedChanged);
        binding.buttonColorBlack.setOnCheckedChangeListener(this::onRadioButtonCheckedChanged);
        binding.buttonColorBlue.setOnCheckedChangeListener(this::onRadioButtonCheckedChanged);
    }

    private void SmileyWinkToText() {

        Drawable smileyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.smiley_wink);
        if (smileyDrawable != null) {
            smileyDrawable.setBounds(0, 0, smileyDrawable.getIntrinsicWidth(), smileyDrawable.getIntrinsicHeight());
            SpannableString spannableString = new SpannableString(" ");
            ImageSpan imageSpan = new ImageSpan(smileyDrawable, ImageSpan.ALIGN_BOTTOM);
            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            int start = binding.editTextInput.getSelectionStart();
            binding.editTextInput.getText().insert(start, spannableString);
        }
    }

    private void SmileySmileToText() {
        Drawable smileyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.smiley_smile);
        if (smileyDrawable != null) {
            smileyDrawable.setBounds(0, 0, smileyDrawable.getIntrinsicWidth(), smileyDrawable.getIntrinsicHeight());
            SpannableString spannableString = new SpannableString(" ");
            ImageSpan imageSpan = new ImageSpan(smileyDrawable, ImageSpan.ALIGN_BOTTOM);
            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            int start = binding.editTextInput.getSelectionStart();
            binding.editTextInput.getText().insert(start, spannableString);
        }
    }

    private void SmileyBigSmileToText() {
        Drawable smileyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.smiley_big_smile);
        if (smileyDrawable != null) {
            smileyDrawable.setBounds(0, 0, smileyDrawable.getIntrinsicWidth(), smileyDrawable.getIntrinsicHeight());
            SpannableString spannableString = new SpannableString(" ");
            ImageSpan imageSpan = new ImageSpan(smileyDrawable, ImageSpan.ALIGN_BOTTOM);
            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            int start = binding.editTextInput.getSelectionStart();
            binding.editTextInput.getText().insert(start, spannableString);
        }
    }

    private void HideMenu() {
        if (binding.menuLayout.getVisibility() == View.VISIBLE) {
            binding.menuLayout.setVisibility(View.GONE);
            binding.buttonHide.setText(R.string.show);
        } else {
            binding.menuLayout.setVisibility(View.VISIBLE);
            binding.buttonHide.setText(R.string.hide);
        }
    }

    private void TextToUnderline() {
        int start = binding.editTextInput.getSelectionStart();
        int end = binding.editTextInput.getSelectionEnd();
        SpannableString spannableString = new SpannableString(binding.textPreview.getText());

        UnderlineSpan[] spans = spannableString.getSpans(start, end, UnderlineSpan.class);
        boolean isUnderlined = false;
        for (UnderlineSpan span : spans) {
            spannableString.removeSpan(span);
            isUnderlined = true;
        }

        if (!isUnderlined) {
            spannableString.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        binding.textPreview.setText(spannableString);
    }

    private void TextToItalic() {
        int start = binding.editTextInput.getSelectionStart();
        int end = binding.editTextInput.getSelectionEnd();
        SpannableString spannableString = new SpannableString(binding.textPreview.getText());

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

        binding.textPreview.setText(spannableString);
    }

    private void TextToBold() {
        int start = binding.editTextInput.getSelectionStart();
        int end = binding.editTextInput.getSelectionEnd();
        SpannableString spannableString = new SpannableString(binding.textPreview.getText());

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

        binding.textPreview.setText(spannableString);
    }

    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //binding.textPreview.setText(binding.editTextInput.getText());
                SpannableStringBuilder spannableBuilder = new SpannableStringBuilder(binding.textPreview.getText());

                // Gérer l'ajout de texte
                if (count > before) {
                    CharSequence addedText = s.subSequence(start, start + count);
                    spannableBuilder.replace(start, start + before, addedText);
                }
                // Gérer la suppression de texte
                else if (count < before) {
                    spannableBuilder.delete(start, start + before);
                }

                binding.textPreview.setText(spannableBuilder);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    private void onRadioButtonCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            int color = Color.BLACK; // Valeur par défaut, au cas où aucun cas ne correspond

            int buttonId = buttonView.getId();
            if (buttonId == R.id.button_color_red) {
                color = Color.RED;
            } else if (buttonId == R.id.button_color_white) {
                color = Color.WHITE;
            } else if (buttonId == R.id.button_color_black) {
                color = Color.BLACK;
            } else if (buttonId == R.id.button_color_blue) {
                color = Color.BLUE;
            } else {
                return; // Aucun RadioButton correspondant trouvé
            }

            applyColorToTextSelection(color);

            // Décocher le RadioButton
            buttonView.setChecked(false);
        }
    }


    private void applyColorToTextSelection(int color) {
        int start = binding.editTextInput.getSelectionStart();
        int end = binding.editTextInput.getSelectionEnd();

        if (start < end) {
            SpannableString spannableString = new SpannableString(binding.textPreview.getText());
            ForegroundColorSpan[] spans = spannableString.getSpans(start, end, ForegroundColorSpan.class);
            for (ForegroundColorSpan span : spans) {
                if (spannableString.getSpanStart(span) >= start && spannableString.getSpanEnd(span) <= end) {
                    spannableString.removeSpan(span);
                }
            }

            spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            binding.textPreview.setText(spannableString);

        }
    }


    private void setupColors() {
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked}, // checked
                        new int[]{-android.R.attr.state_checked}, // unchecked
                },
                new int[]{
                        Color.YELLOW, // the color for the checked state
                        Color.BLACK,  // the color for the unchecked state
                }
        );

        binding.buttonColorBlack.setButtonTintList(colorStateList);
        binding.buttonColorBlue.setButtonTintList(colorStateList);
        binding.buttonColorRed.setButtonTintList(colorStateList);
        binding.buttonColorWhite.setButtonTintList(colorStateList);

        binding.buttonBold.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkBlue));
        binding.buttonItalic.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkBlue));
        binding.buttonUnderline.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkBlue));
        binding.buttonHide.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkBlue));

    }
}