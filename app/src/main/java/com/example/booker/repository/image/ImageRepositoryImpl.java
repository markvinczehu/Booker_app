package com.example.booker.repository.image;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booker.callback.Callback;
import com.example.booker.model.Image;
import com.example.booker.persistence.image.PhotoDao;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ImageRepositoryImpl implements ImageRepository, PhotoDao.onInter, Callback {

    private PhotoDao dao;
    private PropertyChangeSupport support;
    private MutableLiveData<ArrayList<String>> mutableLiveData;

    public ImageRepositoryImpl() {
        dao = new PhotoDao(this, this);
        support = new PropertyChangeSupport(this);
        mutableLiveData = new MutableLiveData<>();

    }

    @Override
    public void images(ArrayList<String> images) {
        mutableLiveData.setValue(images);
    }

    private void retrieveImages() {
        dao.retrieve();
    }

    @Override
    public LiveData<ArrayList<String>> getAllImages() {
        retrieveImages();
        return mutableLiveData;
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
    public void upload(Image img) {
        dao.upload(img);
    }



    @Override
    public void getMessage(String message) {
        support.firePropertyChange("MessageFromFirebase", null, message);
    }
}
