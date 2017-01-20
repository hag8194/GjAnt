package com.gjdev.hugo.gjant.data.api;

import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import com.gjdev.hugo.gjant.data.api.model.Product;
import com.gjdev.hugo.gjant.data.api.model.response.CreatedResponseMessage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST("orders")
    Call<CreatedResponseMessage> createOrder(@Query("access-token") String accessToken,
                                             @Field("code") String code,
                                             @Field("description") String description,
                                             @Field("type") int type,
                                             @Field("clientwallet_id") int clientWalletId,
                                             @Field("products") String productsJson);
}
