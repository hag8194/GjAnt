package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.interactor.OrdersInteractor;
import com.gjdev.hugo.gjant.interactor.impl.OrdersInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.OrdersPresenter;
import com.gjdev.hugo.gjant.presenter.impl.OrdersPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class OrdersViewModule {
    @Provides
    public OrdersInteractor provideInteractor() {
        return new OrdersInteractorImpl();
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
