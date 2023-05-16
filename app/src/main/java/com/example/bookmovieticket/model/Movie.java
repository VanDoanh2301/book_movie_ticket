package com.example.bookmovieticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
    @SerializedName("movieID")
    @Expose
    private Long movieID;
    @SerializedName("movieName")
    @Expose
    private String movieName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("bannerUrl")
    @Expose
    private String bannerUrl;

    public Movie() {
    }

    public Movie(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Movie(String movieName, String bannerUrl) {
        this.movieName = movieName;
        this.bannerUrl = bannerUrl;
    }

    public Movie(Long movieID, String movieName, String description, String bannerUrl) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.description = description;
        this.bannerUrl = bannerUrl;
    }

    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}
