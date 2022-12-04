package com.example.booker.ui.login;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.booker.repository.user.UserRepository;
import com.example.booker.repository.user.UserRepositoryImpl;
import com.google.firebase.auth.FirebaseUser;

import java.beans.PropertyChangeEvent;

public class LoginViewModel extends AndroidViewModel {

    private UserRepository userRepo;

    public LoginViewModel(Application app) {
        super(app);
        userRepo = UserRepositoryImpl.getInstance(app);
        userRepo.addPropertyChangeListener("MessageFromFirebase", this::message);
    }

    private void message(PropertyChangeEvent evt) {
        String message = (String) evt.getNewValue();
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }


    public void login(String username, String password){
        userRepo.login(username, password);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepo.getCurrentUser();
    }
}
