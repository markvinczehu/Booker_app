package com.example.booker.ui.add_treatment;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.booker.model.Treatment;
import com.example.booker.repository.treatments.TreatmentRepository;
import com.example.booker.repository.treatments.TreatmentRepositoryImpl;

import java.beans.PropertyChangeEvent;

public class AddTreatmentViewModel extends AndroidViewModel {

    private TreatmentRepository treatmentRepository;

    public AddTreatmentViewModel(@NonNull Application app) {
        super(app);
        treatmentRepository = new TreatmentRepositoryImpl();
        treatmentRepository.addPropertyChangeListener("MessageFromFirebase", this::message);
    }

    private void message(PropertyChangeEvent evt) {
        String message = (String) evt.getNewValue();
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    public void addService(String serviceName, double price, String description) {
        if(!serviceName.trim().equals("") && price != 0 && !description.trim().equals("")) {
            Treatment treatment = new Treatment();
            treatment.setTreatment_name(serviceName.trim());
            treatment.setPrice(price);
            treatment.setDescription(description.trim());
            treatmentRepository.addService(treatment);
        }
        else Toast.makeText(getApplication(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
    }
}
