package com.example.booker.ui.add_treatment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.booker.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddTreatments extends Fragment {

    private AddTreatmentViewModel viewModel;
    private View view;
    private TextInputEditText serviceName;
    private TextInputEditText price;
    private TextInputEditText description;
    private Button save;
    private Button cancel;



   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_add_treatments, container, false);

        viewModel = new ViewModelProvider(this).get(AddTreatmentViewModel.class);

        initView();

        return view;
    }

    private void initView() {
       serviceName = view.findViewById(R.id.input_treatmentName);
       price = view.findViewById(R.id.input_price);
       description = view.findViewById(R.id.input_description);
       save = view.findViewById(R.id.button_save);
       cancel = view.findViewById(R.id.button_cancel);

       save.setOnClickListener(v -> onSave());
       cancel.setOnClickListener(v -> onCancel());
    }

    private void onCancel() {
       serviceName.setText("");
       price.setText("");
       description.setText("");

    }

    private void onSave() {
       viewModel.addService(serviceName.getText().toString(), Double.parseDouble(price.getText().toString()), description.getText().toString());
    }


}