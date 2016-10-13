package com.gjdev.gjant.data.api;

import com.gjdev.gjant.data.api.interceptor.ApiInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

  private static Retrofit adapter;

  private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

  public static Retrofit getInstance(String baseUrl) {

    httpClient.addInterceptor(ApiInterceptor.getInstance());

    adapter = new Retrofit.Builder()
        .client(httpClient.build())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

    return adapter;

  }

}
