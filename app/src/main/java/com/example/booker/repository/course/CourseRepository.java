package com.example.booker.repository.course;

import androidx.lifecycle.LiveData;

import com.example.booker.model.Course;
import com.example.booker.property_change.PropertyChangeSubject;

import java.util.ArrayList;

public interface CourseRepository extends PropertyChangeSubject {
    void addCourse(Course course);

    LiveData<ArrayList<Course>> getAllCourses();

    void addStudentToCourse(String courseName, String userName);
}
