package com.example.bookmovieticket.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;


import com.example.bookmovieticket.R;
import com.example.bookmovieticket.api.RetrofitManager;
import com.example.bookmovieticket.model.User;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
RegisterActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPassword;
    private AppCompatButton btnRegister;

    private Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.name_edt);
        edtEmail = findViewById(R.id.address_edt);
        edtPassword = findViewById(R.id.date_edt);

        toolbar = findViewById(R.id.tool_register);
        toolbar.setNavigationOnClickListener(v -> finish());
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString();

                User patient = new User(name, password, email);

                if (TextUtils.isEmpty(name)) {
                    edtName.setError("Input text");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    edtEmail.setError("Input text");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    edtPassword.setError("Input text");
                    return;
                }
                createPatient(patient);


            }
        });


    }


    private void createPatient(User patient) {
        RetrofitManager.getRetrofit().register(patient).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("Email is variable")) {
                    Toast.makeText(RegisterActivity.this, "IdCard is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (response.body().equals("successfully")) {
                    Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Call fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}