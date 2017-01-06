package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.interactor.ProfileInteractor;
import com.gjdev.hugo.gjant.interactor.impl.ProfileInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.ProfilePresenter;
import com.gjdev.hugo.gjant.presenter.impl.ProfilePresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class ProfileViewModule {
    @Provides
    public ProfileInteractor provideInteractor() {
        return new ProfileInteractorImpl();
    }

    @Provides
    public PresenterFactory<ProfilePresenter> providePresenterFactory(@NonNull final ProfileInteractor interactor) {
        return new PresenterFactory<ProfilePresenter>() {
            @NonNull
            @Override
            public ProfilePresenter create() {
                return new ProfilePresenterImpl(interactor);
            }
        };
    }
}
