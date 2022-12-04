package com.example.booker.persistence.treatment;

import com.example.booker.model.Treatment;

import java.util.ArrayList;

public interface TreatmentDao {
    void addService(Treatment treatment);

    ArrayList<Treatment> getServices();
}
