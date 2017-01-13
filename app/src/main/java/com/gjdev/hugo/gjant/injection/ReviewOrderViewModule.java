package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.interactor.ReviewOrderInteractor;
import com.gjdev.hugo.gjant.interactor.impl.ReviewOrderInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.ReviewOrderPresenter;
import com.gjdev.hugo.gjant.presenter.impl.ReviewOrderPresenterImpl;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;

@Module
public final class ReviewOrderViewModule {
    @Provides
    public ReviewOrderInteractor provideInteractor(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                                   InternalStorageHandler internalStorageHandler) {
        return new ReviewOrderInteractorImpl(apiService, apiErrorHandler, internalStorageHandler);
    }

    @Provides
    public PresenterFactory<ReviewOrderPresenter> providePresenterFactory(@NonNull final ReviewOrderInteractor interactor) {
        return new PresenterFactory<ReviewOrderPresenter>() {
            @NonNull
            @Override
            public ReviewOrderPresenter create() {
                return new ReviewOrderPresenterImpl(interactor);
            }
        };
    }
}
