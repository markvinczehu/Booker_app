package com.example.booker.ui.register_user;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;

import com.example.booker.model.User;
import com.example.booker.repository.user.UserRepository;
import com.example.booker.repository.user.UserRepositoryImpl;

import java.beans.PropertyChangeEvent;

public class RegisterUserViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public RegisterUserViewModel(Application app) {
        super(app);
        userRepository = UserRepositoryImpl.getInstance(app);
        userRepository.addPropertyChangeListener("RegisterUserMessage", this::addUser);
    }

    private void addUser(PropertyChangeEvent evt) {
        String message = (String) evt.getNewValue();
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    public void registerNewUser(String name, String address, String phone, String email, String password) {
        if(!name.trim().equals("") && !address.trim().equals("") && !phone.trim().equals("")) {
            User newUser = new User();
            newUser.setName(name.trim());
            newUser.setAddress(address.trim());
            newUser.setPhoneNumber(phone.trim());
            newUser.setEmail(email.trim());
            newUser.setPassword(password.trim());
            userRepository.registerUser(newUser);
        }
        else Toast.makeText(getApplication(), "Please fill out all the fields", Toast.LENGTH_SHORT).show();
    }
}
