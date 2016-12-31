package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.interactor.CatalogInteractor;
import com.gjdev.hugo.gjant.interactor.impl.CatalogInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CatalogPresenter;
import com.gjdev.hugo.gjant.presenter.impl.CatalogPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class CatalogViewModule {
    @Provides
    public CatalogInteractor provideInteractor() {
        return new CatalogInteractorImpl();
    }

    @Provides
    public PresenterFactory<CatalogPresenter> providePresenterFactory(@NonNull final CatalogInteractor interactor) {
        return new PresenterFactory<CatalogPresenter>() {
            @NonNull
            @Override
            public CatalogPresenter create() {
                return new CatalogPresenterImpl(interactor);
            }
        };
    }
}
