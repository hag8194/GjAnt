package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.interactor.OrderDetailInteractor;
import com.gjdev.hugo.gjant.interactor.impl.OrderDetailInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.OrderDetailPresenter;
import com.gjdev.hugo.gjant.presenter.impl.OrderDetailPresenterImpl;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;

@Module
public final class OrderDetailViewModule {
    @Provides
    public OrderDetailInteractor provideInteractor(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                                   InternalStorageHandler internalStorageHandler) {
        return new OrderDetailInteractorImpl(apiService, apiErrorHandler, internalStorageHandler);
    }

    @Provides
    public PresenterFactory<OrderDetailPresenter> providePresenterFactory(@NonNull final OrderDetailInteractor interactor) {
        return new PresenterFactory<OrderDetailPresenter>() {
            @NonNull
            @Override
            public OrderDetailPresenter create() {
                return new OrderDetailPresenterImpl(interactor);
            }
        };
    }
}
