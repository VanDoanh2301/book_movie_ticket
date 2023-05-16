package com.example.bookmovieticket.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.activity.MovieActivity;
import com.example.bookmovieticket.adapter.MovieAdapterDetail;
import com.example.bookmovieticket.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {



    private RecyclerView rc;
    private List<Movie> movies = getListMovie();
    private MovieAdapterDetail adapterDetail;
    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc = view.findViewById(R.id.rc_movie_detail);
        adapterDetail = new MovieAdapterDetail(getContext());

        configMovieView();
        clickItemRecyclerView();
    }

    private void clickItemRecyclerView() {
        adapterDetail.setOnItemClickListener(position -> {
            Movie movie = movies.get(position);
            Intent i = new Intent(getContext(), MovieActivity.class);
            startActivity(i);
        });
    }

    private void configMovieView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rc.setLayoutManager(gridLayoutManager);
        adapterDetail.setData(getListMovie());
        rc.setAdapter(adapterDetail);
    }

    private List<Movie> getListMovie() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        return movies;
    }


}