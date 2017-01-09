package com.gjdev.hugo.gjant.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.gjdev.hugo.gjant.GjAntApplication;
import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.sql.model.DaoMaster;
import com.gjdev.hugo.gjant.data.sql.model.DaoSession;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import org.greenrobot.greendao.database.Database;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {
    @NonNull
    private final GjAntApplication mApp;

    public AppModule(@NonNull GjAntApplication app) {
        mApp = app;
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
    public InternalStorageHandler provideStorageHandler(Context mApp) {
        return new InternalStorageHandler(mApp);
    }

    @Provides
    public Database provideDatabase(Context mApp){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mApp, "gjant-db");
        return helper.getWritableDb();
    }

    @Provides
    public DaoSession provideDaoSession(Database db) {
        return new DaoMaster(db).newSession();
    }
}
