package com.gjdev.hugo.gjant.data.api;

import com.gjdev.hugo.gjant.data.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hugo on 27/12/2016.
 * Project: GjAnt2
 */

public interface ApiService
{
    @GET("products")
    Call<List<Product>> products(@Query("access-token") String accessToken);

    @GET("products/{id}")
    Call<Product> product(@Query("access-token") String accessToken);

}
