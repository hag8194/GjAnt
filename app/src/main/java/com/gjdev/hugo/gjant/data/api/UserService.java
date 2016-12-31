package com.gjdev.hugo.gjant.data.api;

import com.gjdev.hugo.gjant.data.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    @FormUrlEncoded
    @POST("users/login?expand=employer")
    Call<User> login(@Field("username") String username, @Field("password") String password);
}
