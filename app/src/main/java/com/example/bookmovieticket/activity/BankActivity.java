package com.example.bookmovieticket.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.api.RetrofitManager;
import com.example.bookmovieticket.model.Chair;
import com.example.bookmovieticket.model.Ticket;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankActivity extends AppCompatActivity {

    private TextInputEditText txtName, txtMoney, txtNote, txtMovie;
    private Button btnBook, btnYes, btnNo;
    private String emailUser, passwordUser,userName, movieName;
    private ArrayList<String> putChairs;

    private ImageView img;
    private AlertDialog dialog;
    private Long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        authenLogin();
        getDataIntent();
        findView();
        customView();
        showDialog();


    }

    private void getDataIntent() {
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("data");
        movieName = bundle.getString("movie");
        putChairs = bundle.getStringArrayList("chairs");
    }
    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lựa chọn");
        View view = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        btnNo = view.findViewById(R.id.btn_no);
        btnYes = view.findViewById(R.id.btn_yes);
        btnNo.setOnClickListener(v -> dialog.dismiss());
        builder.setView(view);
        dialog = builder.create();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.MydialogAnimation;
        btnBook.setOnClickListener(v -> {
            dialog.show();
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> strings = new ArrayList<>();
                strings.add("7:00pm");
                strings.add("8:00pm");
                strings.add("9:00pm");
                strings.add("10:00pm");


                Random random = new Random();

                int randomIndex = random.nextInt(strings.size());

                String randomString = strings.get(randomIndex);
                for (String c : putChairs) {
                    Ticket ticket = new Ticket(userName, movieName, c, randomString);
                    newTicketHllo(ticket);
                }
                Intent i = new Intent(BankActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
    private void newTicketHllo(Ticket ticket) {
        RetrofitManager.getRetrofit().createTicket(ticket).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.body().equals("successfully")) {

                    Toast.makeText(BankActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void customView() {
        txtName.setText(userName);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("50000");
        strings.add("80000");
        strings.add("90000");
        strings.add("10000");

        Random random = new Random();
        int randomIndex = random.nextInt(strings.size());
        String randomString = strings.get(randomIndex);
        txtMoney.setText(randomString);
        txtMovie.setText(movieName);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void findView() {
        txtName = findViewById(R.id.txtInName);
        txtMoney = findViewById(R.id.txt_money);
        txtNote = findViewById(R.id.txt_note);
        btnBook = findViewById(R.id.btnBookTicket);
        txtMovie = findViewById(R.id.txtMovie);
        img = findViewById(R.id.icon_close_view);
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
}