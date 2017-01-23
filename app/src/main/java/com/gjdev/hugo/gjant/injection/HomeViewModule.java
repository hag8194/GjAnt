package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.interactor.HomeInteractor;
import com.gjdev.hugo.gjant.interactor.impl.HomeInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.HomePresenter;
import com.gjdev.hugo.gjant.presenter.impl.HomePresenterImpl;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;

@Module
public final class HomeViewModule {
    @Provides
    public HomeInteractor provideInteractor(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                            InternalStorageHandler internalStorageHandler) {
        return new HomeInteractorImpl(apiService, apiErrorHandler, internalStorageHandler);
    }

    @Provides
    public PresenterFactory<HomePresenter> providePresenterFactory(@NonNull final HomeInteractor interactor) {
        return new PresenterFactory<HomePresenter>() {
            @NonNull
            @Override
            public HomePresenter create() {
                return new HomePresenterImpl(interactor);
            }
        };
    }
}
