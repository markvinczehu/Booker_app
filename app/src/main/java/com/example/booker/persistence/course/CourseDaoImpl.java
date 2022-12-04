package com.example.booker.persistence.course;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.booker.callback.Callback;
import com.example.booker.model.Course;
import com.example.booker.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseDaoImpl extends LiveData<Course> implements CourseDao{

    private FirebaseFirestore db;
    private Callback callBack;
    onInter onInter;
    private HashMap<String, User> userMap;

    public CourseDaoImpl(Callback callBack, onInter onInter) {
        db = FirebaseFirestore.getInstance();
        this.callBack = callBack;
        this.onInter = onInter;
        userMap = new HashMap<>();
    }

    @Override
    public void addCourse(Course course) {
        db.collection("courses")
                .document(course.getName()).set(course)
                .addOnSuccessListener(documentReference ->
                        callBack.getMessage("Course added")
                )
                .addOnFailureListener(
                        e -> Log.w(" ", "Error adding the document", e)
                );
    }

    @Override
    public void getCourses() {
        ArrayList<Course> courses = new ArrayList<>();

        db.collection("courses")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        Course course =  ds.toObject(Course.class);
                        courses.add(course);
                        onInter.courses(courses);
                    }
                });
    }

    @Override
    public void addStudentToCourse(String courseName, String userName) {
        setUser(userName);
        db.collection("courses").document(courseName)
                .update("students", userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                callBack.getMessage("Successfully updated");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callBack.getMessage(e.getMessage());
            }
        });
    }



    private void setUser(String username) {
        db.collection("users").document(username)
                .update("role", "apprentice");
         db.collection("users")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        User u =  ds.toObject(User.class);
                        if(u.getEmail().equals(username)) {
                            userMap.put(username, u);
                        }
                    }
                });
    }


    public interface onInter {
        void courses(ArrayList<Course> courses);
    }
}
