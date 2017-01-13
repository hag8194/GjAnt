package com.gjdev.hugo.gjant.data.api;

import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import com.gjdev.hugo.gjant.data.api.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hugo on 27/12/2016.
 * Project: GjAnt2
 */

public interface ApiService
{
    @GET("products?expand=tags")
    Call<List<Product>> products(@Query("access-token") String accessToken);

    @GET("products/{id}?expand=tags,productImages,children")
    Call<Product> product(@Path("id") int id, @Query("access-token") String accessToken);

    @GET("client-wallets/search")
    Call<List<ClientWallet>> clientWallet(@Query("access-token") String accessToken, @Query("employer_id") int employerId);

}
