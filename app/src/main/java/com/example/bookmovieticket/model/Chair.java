package com.example.bookmovieticket.model;

import java.io.Serializable;

public class Chair implements Serializable {
    private String location;

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
