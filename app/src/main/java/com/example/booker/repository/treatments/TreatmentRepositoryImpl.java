package com.example.booker.repository.treatments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booker.callback.Callback;
import com.example.booker.model.Treatment;
import com.example.booker.persistence.treatment.TreatmentDao;
import com.example.booker.persistence.treatment.TreatmentDaoImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TreatmentRepositoryImpl implements TreatmentRepository, Callback, TreatmentDaoImpl.onInter {

    private PropertyChangeSupport support;
    private TreatmentDao treatmentDao;
    private MutableLiveData<ArrayList<Treatment>> mutableLiveData;

    public TreatmentRepositoryImpl() {
        support = new PropertyChangeSupport(this);
        treatmentDao = new TreatmentDaoImpl(this, this);
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public LiveData<ArrayList<Treatment>> getAllServices() {
        getServices();
        return mutableLiveData;
    }

    @Override
    public void addService(Treatment treatment) {
        treatmentDao.addService(treatment);
    }

    @Override
    public ArrayList<Treatment> getServices() {
        return treatmentDao.getServices();
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
    public void getMessage(String message) {
        support.firePropertyChange("MessageFromFirebase", null, message);
    }

    @Override
    public void services(ArrayList<Treatment> treatments) {
        mutableLiveData.setValue(treatments);
    }
}
