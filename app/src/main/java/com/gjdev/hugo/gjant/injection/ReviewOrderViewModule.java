package com.gjdev.hugo.gjant.injection;

import android.content.Context;
import android.support.annotation.NonNull;
import com.gjdev.hugo.gjant.interactor.ReviewOrderInteractor;
import com.gjdev.hugo.gjant.interactor.impl.ReviewOrderInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.ReviewOrderPresenter;
import com.gjdev.hugo.gjant.presenter.impl.ReviewOrderPresenterImpl;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;

@Module
public final class ReviewOrderViewModule {
    @Provides
    public ReviewOrderInteractor provideInteractor(InternalStorageHandler internalStorageHandler, Context context) {
        return new ReviewOrderInteractorImpl(internalStorageHandler, context);
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
