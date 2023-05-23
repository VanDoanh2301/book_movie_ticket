package com.example.bookmovieticket.api;

import com.example.bookmovieticket.model.Content;
import com.example.bookmovieticket.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movies")
    Call<List<Movie>> getAllMovie();

    @GET("movies/{id}")
    Call<Movie> getMovieById(@Path("id") Long id);

    @GET("movies/page")
    Call<Content> getMovieByName(@Query("name") String name);

}
