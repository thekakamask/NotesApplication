package com.example.notesapplication.ui.notePadFragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.notesapplication.R;
import com.example.notesapplication.databinding.FragmentNotePadBinding;
import com.example.notesapplication.ui.notePadFragment.textChangeHandler.SmileyHandler;
import com.example.notesapplication.ui.notePadFragment.textChangeHandler.TextColorHandler;
import com.example.notesapplication.ui.notePadFragment.textChangeHandler.TextStyleHandler;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotePadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotePadFragment extends Fragment {

    private SmileyHandler smileyHandler;


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

        smileyHandler = new SmileyHandler(getContext(), binding.editTextInput);



    }

    private void setupListeners() {

        binding.buttonSmileyBigSmile.setOnClickListener(v -> smileyHandler.insertSmiley(R.drawable.smiley_big_smile));
        binding.buttonSmileySmile.setOnClickListener(v -> smileyHandler.insertSmiley(R.drawable.smiley_smile));
        binding.buttonSmileyWink.setOnClickListener(v -> smileyHandler.insertSmiley(R.drawable.smiley_wink));


        binding.buttonBold.setOnClickListener(v -> {
            int start = binding.editTextInput.getSelectionStart();
            int end = binding.editTextInput.getSelectionEnd();
            SpannableString spannableString = new SpannableString(binding.textPreview.getText());
            TextStyleHandler.applyBold(spannableString, start, end);
            binding.textPreview.setText(spannableString);
        });

        binding.buttonItalic.setOnClickListener(v -> {
            int start = binding.editTextInput.getSelectionStart();
            int end = binding.editTextInput.getSelectionEnd();
            SpannableString spannableString = new SpannableString(binding.textPreview.getText());
            TextStyleHandler.applyItalic(spannableString, start, end);
            binding.textPreview.setText(spannableString);
        });

        binding.buttonUnderline.setOnClickListener(v -> {
            int start = binding.editTextInput.getSelectionStart();
            int end = binding.editTextInput.getSelectionEnd();
            SpannableString spannableString = new SpannableString(binding.textPreview.getText());
            TextStyleHandler.applyUnderline(spannableString, start, end);
            binding.textPreview.setText(spannableString);
        });




        TextWatcher textWatcher = getTextWatcher();
        binding.editTextInput.addTextChangedListener(textWatcher);


        binding.buttonColorWhite.setOnCheckedChangeListener(this::onRadioButtonCheckedChanged);
        binding.buttonColorRed.setOnCheckedChangeListener(this::onRadioButtonCheckedChanged);
        binding.buttonColorBlack.setOnCheckedChangeListener(this::onRadioButtonCheckedChanged);
        binding.buttonColorBlue.setOnCheckedChangeListener(this::onRadioButtonCheckedChanged);

        binding.buttonHide.setOnClickListener(v -> HideMenu());
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
            int color = TextColorHandler.getColorFromButtonId(buttonView.getId());
            applyColorToTextSelection(color);
            buttonView.setChecked(false);
        }
    }

    private void applyColorToTextSelection(int color) {
        int start = binding.editTextInput.getSelectionStart();
        int end = binding.editTextInput.getSelectionEnd();
        SpannableString spannableString = new SpannableString(binding.textPreview.getText());
        TextColorHandler.applyColorToTextSelection(spannableString, start, end, color);
        binding.textPreview.setText(spannableString);
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

    private void HideMenu() {
        if (binding.menuLayout.getVisibility() == View.VISIBLE) {
            binding.menuLayout.setVisibility(View.GONE);
            binding.buttonHide.setText(R.string.show);
        } else {
            binding.menuLayout.setVisibility(View.VISIBLE);
            binding.buttonHide.setText(R.string.hide);
        }
    }
}