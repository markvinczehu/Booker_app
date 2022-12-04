package com.example.booker.repository.user;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booker.callback.Callback;
import com.example.booker.model.User;
import com.example.booker.persistence.user.UserDAO;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


public class UserRepositoryImpl implements UserRepository, Callback, UserDAO.onInter {
    private final UserDAO userDao;
    private final Application app;
    private static UserRepositoryImpl instance;
    private PropertyChangeSupport support;
    private MutableLiveData<ArrayList<User>> mutableLiveData;

    public UserRepositoryImpl(Application app) {
        this.app = app;
        userDao = new UserDAO(this, this);
        support = new PropertyChangeSupport(this);
        mutableLiveData = new MutableLiveData<>();
    }

    public static synchronized UserRepositoryImpl getInstance(Application app) {
        if(instance == null)
            instance = new UserRepositoryImpl(app);
        return instance;
    }

    @Override
    public LiveData<FirebaseUser> getCurrentUser() {
        return userDao;
    }

    @Override
    public void logout() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());

    }

    @Override
    public LiveData<ArrayList<User>> getAllUser() {
        userDao.getAllUsers();
        return mutableLiveData;
    }

    @Override
    public void deleteProfile() {
        userDao.deleteProfile();
    }

    @Override
    public void registerUser(User newUser) {
        userDao.insert(newUser);
    }

    @Override
    public void login(String username, String password) {
        userDao.login(username, password);
    }


    @Override
    public void getMessage(String message) {
        support.firePropertyChange("MessageFromFirebase", null, message);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removerPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }

    @Override
    public void users(ArrayList<User> users) {
        mutableLiveData.setValue(users);
    }
}
