package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.interactor.MapInteractor;
import com.gjdev.hugo.gjant.interactor.impl.MapInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.MapPresenter;
import com.gjdev.hugo.gjant.presenter.impl.MapPresenterImpl;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;

@Module
public final class MapViewModule {
    @Provides
    public MapInteractor provideInteractor(InternalStorageHandler internalStorageHandler) {
        return new MapInteractorImpl(internalStorageHandler);
    }

    @Provides
    public PresenterFactory<MapPresenter> providePresenterFactory(@NonNull final MapInteractor interactor) {
        return new PresenterFactory<MapPresenter>() {
            @NonNull
            @Override
            public MapPresenter create() {
                return new MapPresenterImpl(interactor);
            }
        };
    }
}
