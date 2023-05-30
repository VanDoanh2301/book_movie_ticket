package com.example.bookmovieticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class ShowTime implements Serializable {
    @SerializedName("showTimeID")
    @Expose
    private Long showTimeID;
    @SerializedName("movieID")
    @Expose
    private Long movieID;
    @SerializedName("roomID")
    @Expose
    private Long roomID;
    @SerializedName("numTicketSold")
    @Expose
    private Long numTicketSold;
    @SerializedName("showDate")
    @Expose
    private Date showDate;
    @SerializedName("startTime")
    @Expose
    private Time startTime;
    @SerializedName("endTime")
    @Expose
    private Time endTime;

    public ShowTime() {
    }

    public ShowTime(Long showTimeID, Long movieID, Long roomID, Long numTicketSold, Date showDate, Time startTime, Time endTime) {
        this.showTimeID = showTimeID;
        this.movieID = movieID;
        this.roomID = roomID;
        this.numTicketSold = numTicketSold;
        this.showDate = showDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getShowTimeID() {
        return showTimeID;
    }

    public void setShowTimeID(Long showTimeID) {
        this.showTimeID = showTimeID;
    }

    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public Long getNumTicketSold() {
        return numTicketSold;
    }

    public void setNumTicketSold(Long numTicketSold) {
        this.numTicketSold = numTicketSold;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
