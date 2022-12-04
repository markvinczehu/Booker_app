package com.example.booker.ui.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.booker.R;


public class Settings extends Fragment {

    View view;
    SettingsViewModel viewModel;
    private Button settings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_settings, container, false);

        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        settings = view.findViewById(R.id.delete);

        settings.setOnClickListener(v -> onClicked());

        return view;
    }

    private void onClicked() {
        viewModel.deleteProfile();
    }
}