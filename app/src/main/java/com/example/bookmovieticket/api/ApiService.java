package com.example.bookmovieticket.api;

import com.example.bookmovieticket.model.Content;
import com.example.bookmovieticket.model.Movie;
import com.example.bookmovieticket.model.MovieScheduleRequest;
import com.example.bookmovieticket.model.Ticket;
import com.example.bookmovieticket.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movies")
    Call<List<Movie>> getAllMovie();

    @GET("movies/{id}")
    Call<Movie> getMovieById(@Path("id") Long id);

    @GET("movies/page")
    Call<Content> getMovieByName(@Query("name") String name);

    //d
    @GET("schedules/movies/{id}")
    Call<MovieScheduleRequest> getShowTime(@Path("id") Long id);

    @POST("login")
    Call<User> loginUser(@Query("email") String email, @Query("password") String password);

    @POST("register")
    Call<String> register(@Body  User patient);

    @POST("newTicketTime")
    Call<String> createTicket(@Body Ticket ticket);

    @GET("getTicket")
    Call<List<Ticket>> getTicketAll();
    @GET("getTicket/{name}")
    Call<List<Ticket>> getAllTicket(@Path("name") String name);

    @GET("deleteTicket/{id}")
    Call<String> deleteTicket(@Path("id") Long id);
}
