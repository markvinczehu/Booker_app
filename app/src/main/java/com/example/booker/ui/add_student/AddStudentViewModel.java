package com.example.booker.ui.add_student;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.booker.model.Course;
import com.example.booker.model.User;
import com.example.booker.repository.course.CourseRepository;
import com.example.booker.repository.course.CourseRepositoryImpl;
import com.example.booker.repository.user.UserRepository;
import com.example.booker.repository.user.UserRepositoryImpl;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class AddStudentViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private CourseRepository courseRepository;

    public AddStudentViewModel(@NonNull Application app) {
        super(app);
        userRepository = new UserRepositoryImpl(app);
        courseRepository = new CourseRepositoryImpl(app);
        courseRepository.addPropertyChangeListener("MessageFromFirebase", this::message);
    }

    private void message(PropertyChangeEvent evt) {
        String message = (String) evt.getNewValue();
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }


    public LiveData<ArrayList<Course>> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public LiveData<ArrayList<User>> getAllUsers() {
        return userRepository.getAllUser();
    }

    public void addStudentToCourse(String courseName, String userName) {
        if(!courseName.trim().equals("") && !userName.trim().equals("")) {
            courseRepository.addStudentToCourse(courseName.trim(), userName.trim());
        }
        else Toast.makeText(getApplication(), "Please select all", Toast.LENGTH_SHORT).show();
    }
}
