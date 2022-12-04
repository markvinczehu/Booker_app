package com.example.booker.property_change;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject {
    void addPropertyChangeListener(String name, PropertyChangeListener listener);
    void removerPropertyChangeListener(String name, PropertyChangeListener listener);
}
