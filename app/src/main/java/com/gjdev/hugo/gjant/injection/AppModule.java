package com.gjdev.hugo.gjant.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.GjAntApplication;
import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;
import com.squareup.otto.Bus;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public final class AppModule {
    @NonNull
    private final GjAntApplication mApp;

    @NonNull
    private final Bus mBus;

    public AppModule(@NonNull GjAntApplication app, @NonNull Bus bus) {
        mApp = app;
        mBus = bus;
    }

    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public GjAntApplication provideApp() {
        return mApp;
    }

    @Provides
    public SharedPreferences provideSharedPreferences(Context mApp) {
        return mApp.getSharedPreferences(mApp.getString(R.string.sharepreferences), Context.MODE_PRIVATE);
    }

    @Provides
    public InternalStorageHandler provideStorageHandler(Context mApp){
        return new InternalStorageHandler(mApp);
    }

    @Provides
    public Bus provideBus() {
        return mBus;
    }

    @Provides
    public Picasso providePicasso(Context mApp) {

        Picasso.Builder picassoBuilder = new Picasso.Builder(mApp);
        Picasso picasso = picassoBuilder.build();
        //picasso.setLoggingEnabled(true);
        try {
            Picasso.setSingletonInstance(picasso);
        } catch (IllegalStateException ignored) {}

        return picasso;
    }

}
