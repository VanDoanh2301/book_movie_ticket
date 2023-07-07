package com.example.bookmovieticket.model;

import java.io.Serializable;

public class Chair implements Serializable {
    private String location;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Chair() {
    }

    public Chair(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
