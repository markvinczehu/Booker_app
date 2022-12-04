package com.example.booker.repository.course;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booker.callback.Callback;
import com.example.booker.model.Course;
import com.example.booker.persistence.course.CourseDao;
import com.example.booker.persistence.course.CourseDaoImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CourseRepositoryImpl implements CourseRepository, Callback, CourseDaoImpl.onInter {

    private CourseDao courseDao;
    private PropertyChangeSupport support;
    private MutableLiveData<ArrayList<Course>> mutableLiveData;

    public CourseRepositoryImpl(Application app) {
        courseDao = new CourseDaoImpl(this, this);
        support = new PropertyChangeSupport(this);
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public void addCourse(Course course) {
        courseDao.addCourse(course);
    }

    @Override
    public LiveData<ArrayList<Course>> getAllCourses() {
        getCourses();
        return mutableLiveData;
    }

    @Override
    public void addStudentToCourse(String courseName, String userName) {
        courseDao.addStudentToCourse(courseName, userName);
    }

    private void getCourses() {
        courseDao.getCourses();
    }

    @Override
    public void getMessage(String message) {
        support.firePropertyChange("MessageFromFirebase", null, message);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removerPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }

    @Override
    public void courses(ArrayList<Course> courses) {
        mutableLiveData.setValue(courses);
    }
}
