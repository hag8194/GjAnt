package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.interactor.ClientDetailInteractor;
import com.gjdev.hugo.gjant.interactor.impl.ClientDetailInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.ClientDetailPresenter;
import com.gjdev.hugo.gjant.presenter.impl.ClientDetailPresenterImpl;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;

@Module
public final class ClientDetailViewModule {
    @Provides
    public ClientDetailInteractor provideInteractor(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                                    InternalStorageHandler internalStorageHandler) {
        return new ClientDetailInteractorImpl(apiService, apiErrorHandler, internalStorageHandler);
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
