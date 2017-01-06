package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.interactor.ClientDetailInteractor;
import com.gjdev.hugo.gjant.interactor.impl.ClientDetailInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.ClientDetailPresenter;
import com.gjdev.hugo.gjant.presenter.impl.ClientDetailPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class ClientDetailViewModule {
    @Provides
    public ClientDetailInteractor provideInteractor() {
        return new ClientDetailInteractorImpl();
    }

    @Provides
    public PresenterFactory<ClientDetailPresenter> providePresenterFactory(@NonNull final ClientDetailInteractor interactor) {
        return new PresenterFactory<ClientDetailPresenter>() {
            @NonNull
            @Override
            public ClientDetailPresenter create() {
                return new ClientDetailPresenterImpl(interactor);
            }
        };
    }
}
