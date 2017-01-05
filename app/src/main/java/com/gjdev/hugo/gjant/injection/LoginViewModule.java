package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.UserService;
import com.gjdev.hugo.gjant.interactor.LoginInteractor;
import com.gjdev.hugo.gjant.interactor.impl.LoginInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.LoginPresenter;
import com.gjdev.hugo.gjant.presenter.impl.LoginPresenterImpl;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public final class LoginViewModule {
    @Provides
    public UserService provideUserService(Retrofit apiAdapter) {
        return apiAdapter.create(UserService.class);
    }

    @Provides
    public LoginInteractor provideInteractor(UserService userService, ApiErrorHandler apiErrorHandler,
                                             InternalStorageHandler internalStorageHandler) {
        return new LoginInteractorImpl(userService, apiErrorHandler, internalStorageHandler);
    }

    @Provides
    public PresenterFactory<LoginPresenter> providePresenterFactory(@NonNull final LoginInteractor interactor) {
        return new PresenterFactory<LoginPresenter>() {
            @NonNull
            @Override
            public LoginPresenter create() {
                return new LoginPresenterImpl(interactor);
            }
        };
    }
}
