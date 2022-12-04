package com.example.booker.ui.settings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.booker.repository.user.UserRepository;
import com.example.booker.repository.user.UserRepositoryImpl;

public class SettingsViewModel extends AndroidViewModel {

    private UserRepository repo;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        repo = new UserRepositoryImpl(application);
    }

    public void deleteProfile() {
        repo.deleteProfile();
        repo.logout();
    }
}
