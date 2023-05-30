package com.example.bookmovieticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieScheduleRequest implements Serializable {
    @SerializedName("movieDTO")
    @Expose
    private Movie movieDTO;
    @SerializedName("showTimeDetailsDTOs")
    @Expose
    private List<ShowTime> showTimeDetailsDTOs;

    public MovieScheduleRequest() {
    }

    public MovieScheduleRequest(Movie movieDTO, List<ShowTime> showTimeDetailsDTOs) {
        this.movieDTO = movieDTO;
        this.showTimeDetailsDTOs = showTimeDetailsDTOs;
    }

    public Movie getMovieDTO() {
        return movieDTO;
    }

    public void setMovieDTO(Movie movieDTO) {
        this.movieDTO = movieDTO;
    }

    public List<ShowTime> getShowTimeDetailsDTOs() {
        return showTimeDetailsDTOs;
    }

    public void setShowTimeDetailsDTOs(List<ShowTime> showTimeDetailsDTOs) {
        this.showTimeDetailsDTOs = showTimeDetailsDTOs;
    }
}
