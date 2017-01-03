package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hugo on 26/12/2016.
 * Project: GjAnt2
 */
@Module
public class NetModule {
    private final String baseUrl;

    public NetModule(@NonNull String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    public okhttp3.Interceptor providesOkHttpInterceptor(){

        return new okhttp3.Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        //.container_main("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };
    }

    @Provides
    public HttpLoggingInterceptor providesLoggingInterceptor(){
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public OkHttpClient.Builder providesOkHttpClientBuilder(okhttp3.Interceptor interceptor, HttpLoggingInterceptor loggingInterceptor){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(interceptor);
        httpClient.addInterceptor(loggingInterceptor);

        return httpClient;
    }

    @Provides
    public Retrofit providesApiAdapter(OkHttpClient.Builder httpClient){
        return new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(this.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public ApiErrorHandler providesErrorApiHandler(Retrofit apiAdapter){
        return new ApiErrorHandler(apiAdapter);
    }

    @Provides
    public ApiService provideApiService(Retrofit apiAdapter){
        return apiAdapter.create(ApiService.class);
    }

}
