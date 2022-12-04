package com.example.booker.ui.treatments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.booker.model.Treatment;
import com.example.booker.repository.treatments.TreatmentRepository;
import com.example.booker.repository.treatments.TreatmentRepositoryImpl;

import java.util.ArrayList;

public class TreatmentsViewModel extends AndroidViewModel {

    private TreatmentRepository treatmentRepository;

    public TreatmentsViewModel(@NonNull Application app) {
        super(app);
        treatmentRepository = new TreatmentRepositoryImpl();
    }

    public LiveData<ArrayList<Treatment>> getAllServices() {
        return treatmentRepository.getAllServices();
    }
}
