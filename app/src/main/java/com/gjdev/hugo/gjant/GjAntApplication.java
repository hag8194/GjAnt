package com.gjdev.hugo.gjant;

import android.app.Application;
import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.AppModule;
import com.gjdev.hugo.gjant.injection.DaggerAppComponent;
import com.gjdev.hugo.gjant.injection.NetModule;
import com.gjdev.hugo.gjant.util.ApiConstants;
import com.squareup.picasso.Picasso;
import org.greenrobot.eventbus.EventBus;

public final class GjAntApplication extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(ApiConstants.BASE_URL))
                .build();
        setupPicasso();
        setupEventBus();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    private void setupPicasso() {
        Picasso.Builder picassoBuilder = new Picasso.Builder(this);
        Picasso picasso = picassoBuilder.build();
        //picasso.setLoggingEnabled(true);
        //picasso.setIndicatorsEnabled(true);

        try {
            Picasso.setSingletonInstance(picasso);
        } catch (IllegalStateException ignored) {}
    }

    private void setupEventBus() {
        EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                //.throwSubscriberException(BuildConfig.DEBUG)
                .installDefaultEventBus();
    }
}