package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.interactor.OrderFormInteractor;
import com.gjdev.hugo.gjant.interactor.impl.OrderFormInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.OrderFormPresenter;
import com.gjdev.hugo.gjant.presenter.impl.OrderFormPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class OrderFormViewModule {
    @Provides
    public OrderFormInteractor provideInteractor() {
        return new OrderFormInteractorImpl();
    }

    @Provides
    public PresenterFactory<OrderFormPresenter> providePresenterFactory(@NonNull final OrderFormInteractor interactor) {
        return new PresenterFactory<OrderFormPresenter>() {
            @NonNull
            @Override
            public OrderFormPresenter create() {
                return new OrderFormPresenterImpl(interactor);
            }
        };
    }
}
