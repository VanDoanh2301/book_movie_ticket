package com.example.bookmovieticket.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookmovieticket.R;
import com.example.bookmovieticket.api.RetrofitManager;
import com.example.bookmovieticket.model.Movie;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    private ImageView imgBack, imgBanner;
    private TextView txtName, txtDescription;
    private Button bntBook;
    Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        imgBack = findViewById(R.id.imageView4);
        imgBack.setOnClickListener(v -> finish());

        findView();
        getIntentData();
        customView();
        customBookButton();

    }

    private void customBookButton() {
        bntBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieActivity.this, RoomActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                i.putExtra("dataId", bundle);
                startActivity(i);

            }
        });

    }

    private void customView() {
        RetrofitManager.getRetrofit().getMovieById(id).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful() && response != null) {
                    Movie movie = response.body();
                    Picasso.get().load(movie.getBannerUrl()).into(imgBanner);
                    txtName.setText(movie.getMovieName());
                    txtDescription.setText(movie.getDescription());
                    txtName.setMovementMethod(new ScrollingMovementMethod());

                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void getIntentData() {
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("dataId");
        id = bundle.getLong("id");
    }

    private void findView() {
        imgBanner = findViewById(R.id.imageView3);
        txtName = findViewById(R.id.textView5);
        txtDescription = findViewById(R.id.textView8);
        bntBook = findViewById(R.id.button);

    }


}