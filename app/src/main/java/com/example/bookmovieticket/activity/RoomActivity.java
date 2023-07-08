package com.example.bookmovieticket.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.adapter.ButtonAdapter;
import com.example.bookmovieticket.adapter.RoomAdapter;
import com.example.bookmovieticket.api.RetrofitManager;
import com.example.bookmovieticket.model.Chair;
import com.example.bookmovieticket.model.MovieScheduleRequest;
import com.example.bookmovieticket.model.ShowTime;
import com.example.bookmovieticket.model.Ticket;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomActivity extends AppCompatActivity {

    private RecyclerView rc, rcTime;

    private RoomAdapter adapter;

    private ButtonAdapter buttonAdapter;

    private Button  btnBooking;
    private List<ShowTime> showTimes;

    private List<Chair> putChairs = new ArrayList<>();


    private List<Chair> chairs;
    private List<Ticket> tickets;

    private String emailUser, passwordUser,userName;
    private Long userId;
    Long id;
    String movieName, location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        findView();
        customView();
        authenLogin();
        getDataIntent();
        getDataShowTime();
        setClickChair();
        customBooking();




    }

    private void customChairPoision() {
        RetrofitManager.getRetrofit().getTicketAll().enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                tickets = response.body();
                for (int i =0; i< tickets.size(); i++) {
                    if (tickets.get(i).getLocation().equals(chairs.get(i).getLocation())) {
                        chairs.get(i).setSelected(true);
                        adapter.setData(chairs);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {

            }
        });

    }

    private void authenLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        emailUser = sharedPreferences.getString("email", "");
        passwordUser = sharedPreferences.getString("password", "");
        userId= sharedPreferences.getLong("userId", -1);
        userName = sharedPreferences.getString("name","");

        if (!userName.isEmpty() && !passwordUser.isEmpty()) {
            Toast.makeText(this, "Hello: " + userName, Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
    private void customBooking() {
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> locals = new ArrayList<>();
                for (Chair c : putChairs) {
                    String s = c.getLocation();
                    locals.add(s);

                }
                Intent i = new Intent(RoomActivity.this, BankActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("chairs", locals);
                bundle.putString("movie", movieName);
                i.putExtra("data", bundle);
                startActivity(i);
            }
        });
    }



    private void setClickChair() {
       adapter.setOnItemClickListener(new RoomAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(int position) {
                chairs.get(position).setSelected(!chairs.get(position).isSelected());
                adapter.setData(chairs);

                Chair chair = chairs.get(position);
                if (putChairs.contains(chair)) {
                    putChairs.remove(chair);
                } else {
                    putChairs.add(chair);
                }

                location = chair.getLocation();
           }
       });
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
        movieName = bundle.getString("movie");
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
        chairs = getListData();
        adapter.setData(chairs);
        rc.setAdapter(adapter);
    }

    private void findView() {
        rc = findViewById(R.id.recyclerView);
        rcTime = findViewById(R.id.rc_view_button_time);
        btnBooking = findViewById(R.id.btn_booking_movie);
    }
}