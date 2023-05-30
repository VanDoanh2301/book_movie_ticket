package com.example.bookmovieticket.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.activity.MainActivity;
import com.example.bookmovieticket.activity.MovieActivity;
import com.example.bookmovieticket.adapter.ImageAdapter;
import com.example.bookmovieticket.adapter.MovieAdapterDetail;
import com.example.bookmovieticket.adapter.MovieAdapterMain;
import com.example.bookmovieticket.api.RetrofitManager;
import com.example.bookmovieticket.model.Content;
import com.example.bookmovieticket.model.Image;
import com.example.bookmovieticket.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private List<Image> images;

    private List<Movie> movies;
    private Timer timer;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;

    private TextView txtSeeAll;

    private EditText editSearch;

    private ImageAdapter adapterImage;
    private MovieAdapterMain adapterMovie;
    private RelativeLayout relaIn, relaOut;
    private RecyclerView rc, rc_gone;
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
        txtSeeAll = view.findViewById(R.id.textView4);
        circleIndicator= view.findViewById(R.id.circle_bar);
        editSearch = view.findViewById(R.id.edt_search);
        relaIn = view.findViewById(R.id.relative_in);
        relaOut = view.findViewById(R.id.relative_out);
        rc_gone = view.findViewById(R.id.rc_gone_movie_search);
        adapterMovie = new MovieAdapterMain(getContext());

        slideBar();
        configViewPager();
        configMovieView();
        clickItemRecyclerView();
        seeAll();
        editSearchView();


    }

    public void clearEditText() {
        if (editSearch != null) {
            editSearch.setText("");
        }
    }


    private void editSearchView() {
        editSearch.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                relaIn.setVisibility(View.GONE);
                relaOut.setVisibility(View.VISIBLE);
                searchMovie(editSearch.getText().toString());
                return true;
            }
            return false;
        });
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    relaIn.setVisibility(View.VISIBLE);
                    relaOut.setVisibility(View.GONE);
                } else {
                    relaIn.setVisibility(View.GONE);
                    relaOut.setVisibility(View.VISIBLE);
                }
                String query = s.toString();
                searchMovie(query);

            }
        });

    }

    private void searchMovie(String query) {

        movies.clear();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rc_gone.setLayoutManager(gridLayoutManager);
        RetrofitManager.getRetrofit().getMovieByName(query).enqueue(new Callback<Content>() {
            @Override
            public void onResponse(Call<Content> call, Response<Content> response) {
                Content content = response.body();
                movies = content.getContent();
                adapterMovie.setData(movies);
                rc_gone.setAdapter(adapterMovie);
            }

            @Override
            public void onFailure(Call<Content> call, Throwable t) {
                Toast.makeText(getContext(), "Erorr", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void seeAll() {
        txtSeeAll.setOnClickListener(v -> {
            ((MainActivity) getActivity()).gotoMovie();
        });
    }


    private void clickItemRecyclerView() {
        adapterMovie.setOnItemClickListener(position -> {
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
                    adapterMovie.setData(movies);
                    rc.setAdapter(adapterMovie);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

                Toast.makeText(getContext(), "Call api error", Toast.LENGTH_SHORT).show();
            }
        });

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
        images.add(new Image(R.drawable.img_1));
        images.add(new Image(R.drawable.img_2));
        images.add(new Image(R.drawable.img_3));
        images.add(new Image(R.drawable.img_1));
        images.add(new Image(R.drawable.img_2));
        images.add(new Image(R.drawable.img_3));
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