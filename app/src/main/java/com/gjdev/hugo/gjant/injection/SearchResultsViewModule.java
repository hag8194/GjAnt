package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.interactor.SearchResultsInteractor;
import com.gjdev.hugo.gjant.interactor.impl.SearchResultsInteractorImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.SearchResultsPresenter;
import com.gjdev.hugo.gjant.presenter.impl.SearchResultsPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class SearchResultsViewModule {
    @Provides
    public SearchResultsInteractor provideInteractor() {
        return new SearchResultsInteractorImpl();
    }

    @Provides
    public PresenterFactory<SearchResultsPresenter> providePresenterFactory(@NonNull final SearchResultsInteractor interactor) {
        return new PresenterFactory<SearchResultsPresenter>() {
            @NonNull
            @Override
            public SearchResultsPresenter create() {
                return new SearchResultsPresenterImpl(interactor);
            }
        };
    }
}
