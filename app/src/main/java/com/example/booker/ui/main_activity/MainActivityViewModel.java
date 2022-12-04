package com.example.booker.ui.main_activity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.booker.repository.user.UserRepository;
import com.example.booker.repository.user.UserRepositoryImpl;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityViewModel extends AndroidViewModel {

    private UserRepository userRepo;

    public MainActivityViewModel(@NonNull Application app) {
        super(app);
        userRepo = new UserRepositoryImpl(app);
    }

    public void logout() {
        userRepo.logout();
    }

    public LiveData<FirebaseUser> getUsername() {
        return userRepo.getCurrentUser();
    }
}
