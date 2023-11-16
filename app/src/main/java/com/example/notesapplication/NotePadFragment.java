package com.example.notesapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
}