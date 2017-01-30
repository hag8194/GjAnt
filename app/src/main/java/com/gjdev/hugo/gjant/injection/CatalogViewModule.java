package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.data.api.UserService;
import com.gjdev.hugo.gjant.data.sql.model.DaoSession;
import com.gjdev.hugo.gjant.interactor.CatalogInteractor;
import com.gjdev.hugo.gjant.interactor.impl.CatalogInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CatalogPresenter;
import com.gjdev.hugo.gjant.presenter.impl.CatalogPresenterImpl;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public final class CatalogViewModule {
    @Provides
    public CatalogInteractor provideInteractor(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                               InternalStorageHandler internalStorageHandler) {
        return new CatalogInteractorImpl(apiService, apiErrorHandler, internalStorageHandler);
    }

    @Provides
    public PresenterFactory<CatalogPresenter> providePresenterFactory(@NonNull final CatalogInteractor interactor) {
        return new PresenterFactory<CatalogPresenter>() {
            @NonNull
            @Override
            public CatalogPresenter create() {
                return new CatalogPresenterImpl(interactor);
            }
        };
    }
}
