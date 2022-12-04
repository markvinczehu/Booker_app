package com.example.booker.repository.user;

import androidx.lifecycle.LiveData;

import com.example.booker.model.User;
import com.example.booker.property_change.PropertyChangeSubject;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public interface UserRepository extends PropertyChangeSubject {
    void registerUser(User newUser);
    void login(String username, String password);
    LiveData<FirebaseUser> getCurrentUser();

    void logout();

    LiveData<ArrayList<User>> getAllUser();

    void deleteProfile();
}
