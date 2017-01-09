package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.sql.model.DaoSession;
import com.gjdev.hugo.gjant.interactor.CartInteractor;
import com.gjdev.hugo.gjant.interactor.impl.CartInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CartPresenter;
import com.gjdev.hugo.gjant.presenter.impl.CartPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class CartViewModule {
    @Provides
    public CartInteractor provideInteractor(DaoSession daoSession) {
        return new CartInteractorImpl(daoSession);
    }

    @Provides
    public PresenterFactory<CartPresenter> providePresenterFactory(@NonNull final CartInteractor interactor) {
        return new PresenterFactory<CartPresenter>() {
            @NonNull
            @Override
            public CartPresenter create() {
                return new CartPresenterImpl(interactor);
            }
        };
    }
}
