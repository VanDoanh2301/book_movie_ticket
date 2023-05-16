package com.example.bookmovieticket.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.activity.MovieActivity;
import com.example.bookmovieticket.adapter.ImageAdapter;
import com.example.bookmovieticket.adapter.MovieAdapterDetail;
import com.example.bookmovieticket.adapter.MovieAdapterMain;
import com.example.bookmovieticket.model.Image;
import com.example.bookmovieticket.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {

    private List<Image> images;

    private List<Movie> movies = getListMovie();
    private Timer timer;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;

    private ImageAdapter adapterImage;
    private MovieAdapterDetail adapterMovie;
    private RecyclerView rc;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc = view.findViewById(R.id.recent_recycler);
        viewPager=view.findViewById(R.id.view_pager);
        circleIndicator= view.findViewById(R.id.circle_bar);

        slideBar();
        configViewPager();
        configMovieView();
        clickItemRecyclerView();
    }

    private void clickItemRecyclerView() {
        adapterMovie.setOnItemClickListener(position -> {
            Movie movie = movies.get(position);
            Intent i = new Intent(getContext(), MovieActivity.class);
            startActivity(i);
        });
    }

    private void configMovieView() {
        adapterMovie = new MovieAdapterDetail(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rc.setLayoutManager(gridLayoutManager);
        adapterMovie.setData(getListMovie());
        rc.setAdapter(adapterMovie);

    }

    private List<Movie> getListMovie() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        movies.add(new Movie("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
        return movies;
    }

    private void configViewPager() {
        images = getListImage();
        adapterImage = new ImageAdapter(getContext(), images);
        viewPager.setAdapter(adapterImage);
        circleIndicator.setViewPager(viewPager);
        adapterImage.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }



    private List<Image> getListImage() {
        List<Image> images = new ArrayList<>();
        images.add(new Image(R.drawable.profile));
        images.add(new Image(R.drawable.profile));
        images.add(new Image(R.drawable.img));
        images.add(new Image(R.drawable.profile));
        images.add(new Image(R.drawable.img));
        images.add(new Image(R.drawable.profile));
        return images;

    }
    private void slideBar() {
        images = getListImage();
        if(timer ==null) {
            timer =new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int total = images.size()-1;
                        if(currentItem<total) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });

            }
        },200, 3000);
    }
}