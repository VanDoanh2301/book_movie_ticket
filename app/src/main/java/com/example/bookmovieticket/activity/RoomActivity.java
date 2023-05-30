package com.example.bookmovieticket.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.adapter.ButtonAdapter;
import com.example.bookmovieticket.adapter.RoomAdapter;
import com.example.bookmovieticket.api.RetrofitManager;
import com.example.bookmovieticket.model.Chair;
import com.example.bookmovieticket.model.MovieScheduleRequest;
import com.example.bookmovieticket.model.ShowTime;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomActivity extends AppCompatActivity {

    private RecyclerView rc, rcTime;

    private RoomAdapter adapter;

    private ButtonAdapter buttonAdapter;

    private List<ShowTime> showTimes;


    private List<Chair> chairs;
    Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        findView();
        customView();
        getDataIntent();
        getDataShowTime();

    }

    private void getDataShowTime() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rcTime.setLayoutManager(gridLayoutManager);
        buttonAdapter = new ButtonAdapter(this);
        RetrofitManager.getRetrofit().getShowTime(id).enqueue(new Callback<MovieScheduleRequest>() {
            @Override
            public void onResponse(Call<MovieScheduleRequest> call, Response<MovieScheduleRequest> response) {
                MovieScheduleRequest request = response.body();
                List<ShowTime> shows = request.getShowTimeDetailsDTOs();
                buttonAdapter.setData(shows);
                rcTime.setAdapter(buttonAdapter);
            }

            @Override
            public void onFailure(Call<MovieScheduleRequest> call, Throwable t) {
                Toast.makeText(RoomActivity.this, "Call error", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void getDataIntent() {
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("dataId");
        id = bundle.getLong("id");
    }


    private List<Chair> getListData() {
        List<Chair> listChair = new ArrayList<>();
        for (char row = 'A'; row <= 'H'; row++) {
            for (int column = 1; column <= 8; column++) {
                String location = String.valueOf(row)  + column;
                Chair chair = new Chair(location);
                listChair.add(chair);
            }
        }

        return listChair;
    }


    private void customView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 8);
        rc.setLayoutManager(gridLayoutManager);
        adapter = new RoomAdapter(this);
        adapter.setData(getListData());
        rc.setAdapter(adapter);
    }

    private void findView() {
        rc = findViewById(R.id.recyclerView);
        rcTime = findViewById(R.id.rc_view_button_time);
    }
}