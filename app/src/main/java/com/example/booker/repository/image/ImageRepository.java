package com.example.booker.repository.image;

import androidx.lifecycle.LiveData;

import com.example.booker.model.Image;
import com.example.booker.property_change.PropertyChangeSubject;

import java.util.ArrayList;

public interface ImageRepository extends PropertyChangeSubject {
    void upload(Image img);
    public LiveData<ArrayList<String>> getAllImages();
}
