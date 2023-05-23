package com.example.bookmovieticket.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.activity.MainActivity;
import com.example.bookmovieticket.activity.MovieActivity;
import com.example.bookmovieticket.adapter.MovieAdapterDetail;
import com.example.bookmovieticket.api.RetrofitManager;
import com.example.bookmovieticket.model.Content;
import com.example.bookmovieticket.model.Movie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {



    private RecyclerView rc;
    private List<Movie> movies ;
    private MovieAdapterDetail adapterDetail;
    private  Button btn10,btn11,btn12,btn16,btn14,btn15, btn17;
    private SearchView searchView;

    private ImageView imgIconClose;
    private TextView txtTime;
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
        txtTime = view.findViewById(R.id.txtTime);
        adapterDetail = new MovieAdapterDetail(getContext());
        imgIconClose = view.findViewById(R.id.icon_close_dt);
        searchView = view.findViewById(R.id.search_view);
        btn10 = view.findViewById(R.id.button10);
        btn11 = view.findViewById(R.id.button11);
        btn12 = view.findViewById(R.id.button12);
        btn14 = view.findViewById(R.id.button14);
        btn15 = view.findViewById(R.id.button15);
        btn16 = view.findViewById(R.id.button16);
        btn17 = view.findViewById(R.id.button17);


        customButtonTime();
        configMovieView();
        clickItemRecyclerView();
        closeFrag();
        serchMovie();
    }

    private void serchMovie() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewMovie(query);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                searchViewMovie(newText);
                return false;
            }
        });
    }
    private void searchViewMovie(String query) {
        movies.clear();
        RetrofitManager.getRetrofit().getMovieByName(query).enqueue(new Callback<Content>() {
            @Override
            public void onResponse(Call<Content> call, Response<Content> response) {
                Content content = response.body();
                movies = content.getContent();
                adapterDetail.setData(movies);
                rc.setAdapter(adapterDetail);
            }

            @Override
            public void onFailure(Call<Content> call, Throwable t) {
                Toast.makeText(getContext(), "No service", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void closeFrag() {
        imgIconClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).gotoHome();
            }
        });
    }

    private void customButtonTime() {
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("EEEE,dd/MM/yyyy"));
            txtTime.setText(formattedDate);
        }


        for (int i = 0; i < 6; i++) {
            LocalDate day = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                day = currentDate.plusDays(i);
            }
            String formattedDay = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formattedDay = day.format(DateTimeFormatter.ofPattern("EEEE"));
            }

            if (i == 0) {
                btn10.setText(formattedDay);
            } else if (i == 1) {
                btn11.setText(formattedDay);
            } else if (i == 2) {
                btn12.setText(formattedDay);
            }
            else if (i == 3) {
                btn14.setText(formattedDay);
            }else if (i == 4) {
                btn15.setText(formattedDay);
            }else if (i == 5) {
                btn16.setText(formattedDay);
            }
        }
    }

    private void clickItemRecyclerView() {
        adapterDetail.setOnItemClickListener(position -> {
            Movie movie = movies.get(position);
            Long id = movie.getMovieID();
            Bundle bundle = new Bundle();
            bundle.putLong("id", id);
            Intent i = new Intent(getContext(), MovieActivity.class);
            i.putExtra("dataId", bundle);
            startActivity(i);
        });
    }

    private void configMovieView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rc.setLayoutManager(gridLayoutManager);
        RetrofitManager.getRetrofit().getAllMovie().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful() && response != null) {
                    movies = response.body();
                    adapterDetail.setData(movies);
                    rc.setAdapter(adapterDetail);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });

    }

//    private List<Movie> getListMovie() {
//        List<Movie> movies = new ArrayList<>();
//        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
//        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
//        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
//        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
//        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
//        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
//        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
//        movies.add(new Movie("Yasuo vip pro","https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg"));
//        return movies;
//    }


}