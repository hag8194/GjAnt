package com.gjdev.hugo.gjant.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.data.sql.model.DaoSession;
import com.gjdev.hugo.gjant.interactor.CreateOrderInteractor;
import com.gjdev.hugo.gjant.interactor.impl.CreateOrderInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CreateOrderPresenter;
import com.gjdev.hugo.gjant.presenter.impl.CreateOrderPresenterImpl;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;

@Module
public final class CreateOrderViewModule {
    @Provides
    public CreateOrderInteractor provideInteractor(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                                   InternalStorageHandler internalStorageHandler, DaoSession daoSession) {
        return new CreateOrderInteractorImpl(apiService, apiErrorHandler, internalStorageHandler, daoSession);
    }

    @Provides
    public PresenterFactory<CreateOrderPresenter> providePresenterFactory(@NonNull final CreateOrderInteractor interactor) {
        return new PresenterFactory<CreateOrderPresenter>() {
            @NonNull
            @Override
            public CreateOrderPresenter create() {
                return new CreateOrderPresenterImpl(interactor);
            }
        };
    }
}
