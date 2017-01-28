package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.interactor.OrdersInteractor;
import com.gjdev.hugo.gjant.interactor.impl.OrdersInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.OrdersPresenter;
import com.gjdev.hugo.gjant.presenter.impl.OrdersPresenterImpl;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;

@Module
public final class OrdersViewModule {
    @Provides
    public OrdersInteractor provideInteractor(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                              InternalStorageHandler internalStorageHandler) {
        return new OrdersInteractorImpl(apiService, apiErrorHandler, internalStorageHandler);
    }

    @Provides
    public PresenterFactory<OrdersPresenter> providePresenterFactory(@NonNull final OrdersInteractor interactor) {
        return new PresenterFactory<OrdersPresenter>() {
            @NonNull
            @Override
            public OrdersPresenter create() {
                return new OrdersPresenterImpl(interactor);
            }
        };
    }
}
