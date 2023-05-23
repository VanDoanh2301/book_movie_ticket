package com.example.bookmovieticket.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.net.ssl.SSLContext;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitManager {
    private final  static  String BASE_URL="http://10.0.2.2:8080/api/";
    private static Retrofit retrofit = null;

    public static  ApiService getRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("HH:mm:ss")
                .setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ApiService.class);
    }
}