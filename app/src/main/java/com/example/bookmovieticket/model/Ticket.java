package com.example.bookmovieticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ticket implements Serializable {
    @SerializedName("ticketID")
    @Expose
    private Long ticketID;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("movie")
    @Expose
    private String movie;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("showTimeID")
    @Expose
    private String  showTimeID;

    public Ticket(String userName, String movie, String location, String showTimeID) {
        this.userName = userName;
        this.movie = movie;
        this.location = location;
        this.showTimeID = showTimeID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShowTimeID() {
        return showTimeID;
    }

    public void setShowTimeID(String showTimeID) {
        this.showTimeID = showTimeID;
    }

    public Long getTicketID() {
        return ticketID;
    }

    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
    }
}
