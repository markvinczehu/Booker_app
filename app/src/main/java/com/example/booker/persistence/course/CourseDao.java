package com.example.booker.persistence.course;

import com.example.booker.model.Course;

public interface CourseDao {
    void addCourse(Course course);

    void getCourses();

    void addStudentToCourse(String courseName, String userName);
}
