package com.example.booker.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.booker.R;

import com.example.booker.ui.main_activity.MainActivity;
import com.example.booker.ui.register_user.RegisterUserActivity;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel viewModel;
    Button login;
    EditText username;
    EditText password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        initViews();
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                navigateToMainActivity();
            }
        });
    }

    private void initViews() {
        username = findViewById(R.id.input_username);
        password = findViewById(R.id.input_password);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.sign_up);

        login.setOnClickListener(v -> onLoginClicked());
        signUp.setOnClickListener(v -> onSignup());
    }

    private void onSignup() {
        startActivity(new Intent(this, RegisterUserActivity.class));
        finish();
    }

    private void onLoginClicked() {
        viewModel.login(username.getText().toString(), password.getText().toString());
        checkIfSignedIn();
    }

    private void navigateToMainActivity() {
       startActivity(new Intent(this, MainActivity.class));
       finish();
    }
}