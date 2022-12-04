package com.example.booker.persistence.treatment;

import android.util.Log;


import androidx.lifecycle.LiveData;

import com.example.booker.callback.Callback;

import com.example.booker.model.Treatment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

public class TreatmentDaoImpl extends LiveData<Treatment> implements TreatmentDao {

    private FirebaseFirestore db;
    private Callback callBack;
    onInter onInter;


    public TreatmentDaoImpl(Callback callBack, onInter onInter) {
        db = FirebaseFirestore.getInstance();
        this.callBack = callBack;
        this.onInter = onInter;

    }

    @Override
    public void addService(Treatment treatment) {
        db.collection("services").document(treatment.getTreatment_name())
                .set(treatment)
                .addOnSuccessListener(documentReference -> {
                    Log.i("Success message: ", "success");
                    callBack.getMessage("Service added");
                })
                .addOnFailureListener(e -> {
                    Log.i("Failure message: ", "failure");
                    callBack.getMessage(e.getMessage());
                });
    }

    @Override
    public ArrayList<Treatment> getServices() {
        ArrayList<Treatment> treatments = new ArrayList<>();

        db.collection("services")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        Treatment treatment =  ds.toObject(Treatment.class);
                        treatments.add(treatment);
                        onInter.services(treatments);
                    }
                });

        return treatments;
    }

    public interface onInter {
        void services(ArrayList<Treatment> treatments);
    }
}
