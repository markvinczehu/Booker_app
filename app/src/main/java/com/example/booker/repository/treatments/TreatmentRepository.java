package com.example.booker.repository.treatments;

import androidx.lifecycle.LiveData;

import com.example.booker.model.Treatment;
import com.example.booker.property_change.PropertyChangeSubject;

import java.util.ArrayList;

public interface TreatmentRepository extends PropertyChangeSubject {
    void addService(Treatment treatment);
    ArrayList<Treatment> getServices();
    LiveData<ArrayList<Treatment>> getAllServices();
}
