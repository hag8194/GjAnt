package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.interactor.SelectClientInteractor;
import com.gjdev.hugo.gjant.interactor.impl.SelectClientInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.SelectClientPresenter;
import com.gjdev.hugo.gjant.presenter.impl.SelectClientPresenterImpl;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;

@Module
public final class SelectClientViewModule {
    @Provides
    public SelectClientInteractor provideInteractor(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                                    InternalStorageHandler internalStorageHandler) {
        return new SelectClientInteractorImpl(apiService, apiErrorHandler, internalStorageHandler);
    }

    @Provides
    public PresenterFactory<SelectClientPresenter> providePresenterFactory(@NonNull final SelectClientInteractor interactor) {
        return new PresenterFactory<SelectClientPresenter>() {
            @NonNull
            @Override
            public SelectClientPresenter create() {
                return new SelectClientPresenterImpl(interactor);
            }
        };
    }
}
