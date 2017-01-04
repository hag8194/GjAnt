package com.gjdev.hugo.gjant.injection;

import android.content.Context;
import android.content.SharedPreferences;

import com.gjdev.hugo.gjant.GjAntApplication;
import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    Context getAppContext();

    GjAntApplication getApp();

    SharedPreferences getSharedPreferences();

    InternalStorageHandler getStorageHandler();

    Picasso getPicasso();

    EventBus getBus();

    Retrofit getApiAdapter();

    ApiErrorHandler getApiErrorHandler();

    ApiService getApiService();
}