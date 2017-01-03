package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.interactor.MainInteractor;
import com.gjdev.hugo.gjant.interactor.impl.MainInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.MainPresenter;
import com.gjdev.hugo.gjant.presenter.impl.MainPresenterImpl;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public final class MainViewModule {

    @Provides
    public MainInteractor provideInteractor(InternalStorageHandler internalStorageHandler) {
        return new MainInteractorImpl(internalStorageHandler);
    }

    @Provides
    public PresenterFactory<MainPresenter> providePresenterFactory(@NonNull final MainInteractor interactor) {
        return new PresenterFactory<MainPresenter>() {
            @NonNull
            @Override
            public MainPresenter create() {
                return new MainPresenterImpl(interactor);
            }
        };
    }
}
