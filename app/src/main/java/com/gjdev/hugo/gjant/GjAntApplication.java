package com.gjdev.hugo.gjant;

import android.app.Application;
import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.AppModule;
import com.gjdev.hugo.gjant.injection.DaggerAppComponent;
import com.gjdev.hugo.gjant.injection.NetModule;
import com.gjdev.hugo.gjant.util.ApiConstants;

import org.greenrobot.eventbus.EventBus;

public final class GjAntApplication extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, EventBus.getDefault()))
                .netModule(new NetModule(ApiConstants.BASE_URL))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}