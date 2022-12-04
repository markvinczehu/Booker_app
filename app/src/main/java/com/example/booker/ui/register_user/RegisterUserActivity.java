package com.example.booker.ui.register_user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.booker.R;
import com.example.booker.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegisterUserActivity extends AppCompatActivity {

    RegisterUserViewModel viewModel;
    TextInputEditText name;
    TextInputEditText address;
    TextInputEditText phone;
    TextInputEditText email;
    TextInputEditText password;
    Button save;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        viewModel = new ViewModelProvider(this).get(RegisterUserViewModel.class);
        initViews();
    }

    private void initViews() {
        name = findViewById(R.id.input_name);
        address  = findViewById(R.id.input_address);
        phone = findViewById(R.id.input_phoneNumber);
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        save = findViewById(R.id.button_save);
        cancel = findViewById(R.id.button_cancel);

        save.setOnClickListener(v -> onSaveClicked());
        cancel.setOnClickListener(v -> onCancelClicked());
    }

    private void onCancelClicked() {
        name.setText("");
        address.setText("");
        phone.setText("");
        email.setText("");
        password.setText("");
        navigateToLogin();
    }

    private void onSaveClicked() {
        viewModel.registerNewUser(Objects.requireNonNull(name.getText()).toString(), Objects.requireNonNull(address.getText()).toString(),
                Objects.requireNonNull(phone.getText()).toString(), Objects.requireNonNull(email.getText()).toString(), Objects.requireNonNull(password.getText()).toString());
        navigateToLogin();
    }

    private void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}