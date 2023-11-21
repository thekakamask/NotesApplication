package com.example.notesapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
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
    }

    private void TextToUnderline() {
    }

    private void TextToItalic() {
    }

    private void TextToBold() {
    }

    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.textPreview.setText(binding.editTextInput.getText());


            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    private void onRadioButtonCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() == R.id.button_color_red) {
                // Logique spécifique pour le bouton rouge
            } else if (buttonView.getId() == R.id.button_color_black) {
                // Logique spécifique pour le bouton noir
            } else if (buttonView.getId() == R.id.button_color_blue) {
                // Logique spécifique pour le bouton bleu
            }
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

        binding.buttonBold.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkBlue));
        binding.buttonItalic.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkBlue));
        binding.buttonUnderline.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkBlue));
        binding.buttonHide.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkBlue));

    }
}