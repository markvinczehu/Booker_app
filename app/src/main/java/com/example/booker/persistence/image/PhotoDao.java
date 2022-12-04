package com.example.booker.persistence.image;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.booker.callback.Callback;
import com.example.booker.model.Image;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhotoDao extends LiveData<String> {

    private StorageReference storageReference;;
    private FirebaseFirestore db;
    private Map<String, String> imageMap;
    private onInter onInter;
    private Callback callBack;

    public PhotoDao(onInter onInter, Callback callBack) {
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        db = FirebaseFirestore.getInstance();
        imageMap = new HashMap<>();
        this.onInter = onInter;
        this.callBack = callBack;
    }

    public void upload(Image image) {
        StorageReference fileStorage = storageReference.child(image.getName() + "." + image.getExtension());
        fileStorage.putFile(image.getUrl()).addOnSuccessListener(taskSnapshot -> {
            String newUpload = taskSnapshot.getTask().toString();
            imageMap.put("url", newUpload);

            db.collection("uploads").add(imageMap);
            Log.d("Successful", "Successful");
            callBack.getMessage("Successfully uploaded");
        }).addOnFailureListener(e -> {
            Log.d("Fail", "Fail");
            callBack.getMessage(e.getMessage());
        });
    }

    public void retrieve() {
        ArrayList<String> image = new ArrayList<>();
        db.collection("uploads")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        String img = ds.getString("url");
                        image.add(img);
                        onInter.images(image);
                    }
                });
    }

    public interface onInter {
        void images(ArrayList<String> images);
    }
}
