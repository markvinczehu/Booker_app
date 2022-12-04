package com.example.booker.persistence.user;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booker.callback.Callback;
import com.example.booker.model.User;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

public class UserDAO extends LiveData<FirebaseUser> {
    private final FirebaseAuth  firebaseAuth;
    private final FirebaseAuth.AuthStateListener listener = firebaseAuth -> setValue(firebaseAuth.getCurrentUser());
    private FirebaseFirestore db;
    private Callback callBack;
    private MutableLiveData<FirebaseUser> firebaseUser;

    onInter onInter;

    public UserDAO(Callback callBack, onInter onInter) {
        db = FirebaseFirestore.getInstance();
        this.callBack = callBack;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = new MutableLiveData<>();
        this.onInter = onInter;
    }

    @Override
    protected void onActive() {
        super.onActive();
        FirebaseAuth.getInstance().addAuthStateListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        FirebaseAuth.getInstance().removeAuthStateListener(listener);
    }

    public void insert(User newUser) {

        newUser.setRole("guest");

        firebaseAuth.createUserWithEmailAndPassword(newUser.getEmail(), newUser.getPassword())
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        db.collection("users").document(newUser.getEmail()).set(newUser)
                                .addOnSuccessListener(documentReference -> {
                                    Log.i("Success message: ", "success");
                                    callBack.getMessage("Registration Completed");
                                })
                                .addOnFailureListener(e -> {
                                    Log.i("failure message: ", "failure");
                                    callBack.getMessage(task.getException().getMessage());
                                });
                    }
                    else {
                        callBack.getMessage(task.getException().getMessage());
                    }
                });
    }

    public void login(String username, String password) {
        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        onActive();
                        firebaseUser.postValue(firebaseAuth.getCurrentUser());
                    } else {
                        firebaseUser.postValue(null);
                        callBack.getMessage(task.getException().getMessage());
                    }
                });
    }

    public void getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        db.collection("users")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        User user =  ds.toObject(User.class);
                        users.add(user);
                        onInter.users(users);
                    }
                });
    }

    public void deleteProfile() {
        firebaseAuth.getCurrentUser().delete();
    }

    public void logout() {
        onInactive();
    }


    public interface onInter {
        void users(ArrayList<User> users);
    }
}
