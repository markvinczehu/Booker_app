package com.example.booker.ui.view_courses;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.booker.model.Course;
import com.example.booker.repository.course.CourseRepository;
import com.example.booker.repository.course.CourseRepositoryImpl;

import java.util.ArrayList;

public class ViewCourseViewModel extends AndroidViewModel {

    private CourseRepository courseRepository;

    public ViewCourseViewModel(@NonNull Application app) {
        super(app);
        courseRepository = new CourseRepositoryImpl(app);
    }

    public LiveData<ArrayList<Course>> getAllCourses() {
        return courseRepository.getAllCourses();
    }
}
