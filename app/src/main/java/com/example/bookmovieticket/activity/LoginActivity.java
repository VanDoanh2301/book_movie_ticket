package com.example.bookmovieticket.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.api.RetrofitManager;
import com.example.bookmovieticket.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEdt, passEdt;
    private AppCompatButton btnLogin;
    private TextView btnSignup;
    private String email, password;
    private User patient;

    private String emailUser, passwordUser,userName;
    private Long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findIdView();
        customLogin();
        customRegister();

    }

    private void customRegister() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            }
        });
    }

    private void customLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEdt.getText().toString().trim();
                password = passEdt.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailEdt.setError("Please enter email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passEdt.setError("Please enter password");
                    return;
                }
                loginApp(email, password);
            }

        });
    }
    private void loginApp(String email, String password) {
        RetrofitManager.getRetrofit().loginUser(email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                patient = response.body();
                if(patient == null) {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    return;
                }
                userName = patient.getUsername();
                userId = patient.getId();
                if(patient == null) {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.putString("name", userName);
                    editor.putLong("userId", userId);
                    editor.apply();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @SuppressLint("WrongViewCast")
    private void findIdView() {
        emailEdt = findViewById(R.id.username);
        passEdt = findViewById(R.id.pass_dn);
        btnLogin = findViewById(R.id.btnSignIn);
      btnSignup = findViewById(R.id.btn_signup);
    }
}