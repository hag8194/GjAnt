package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.interactor.DetailProductInteractor;
import com.gjdev.hugo.gjant.interactor.impl.DetailProductInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.DetailProductPresenter;
import com.gjdev.hugo.gjant.presenter.impl.DetailProductPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class DetailProductViewModule {
    @Provides
    public DetailProductInteractor provideInteractor() {
        return new DetailProductInteractorImpl();
    }

    @Provides
    public PresenterFactory<DetailProductPresenter> providePresenterFactory(@NonNull final DetailProductInteractor interactor) {
        return new PresenterFactory<DetailProductPresenter>() {
            @NonNull
            @Override
            public DetailProductPresenter create() {
                return new DetailProductPresenterImpl(interactor);
            }
        };
    }
}
