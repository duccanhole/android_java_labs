package com.example.myapplication.ui.lab1_2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    @GET("feed")
    Call<RssFeed> getRssFeed();
}
