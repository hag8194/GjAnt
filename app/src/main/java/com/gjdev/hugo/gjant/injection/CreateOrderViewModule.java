package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.interactor.CreateOrderInteractor;
import com.gjdev.hugo.gjant.interactor.impl.CreateOrderInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CreateOrderPresenter;
import com.gjdev.hugo.gjant.presenter.impl.CreateOrderPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class CreateOrderViewModule {
    @Provides
    public CreateOrderInteractor provideInteractor() {
        return new CreateOrderInteractorImpl();
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
