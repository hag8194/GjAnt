package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.SearchResultsView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.SearchResultsPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.SearchResultsViewModule;
import com.gjdev.hugo.gjant.injection.DaggerSearchResultsViewComponent;

import javax.inject.Inject;

public final class SearchResultsActivity extends BaseActivity<SearchResultsPresenter, SearchResultsView> implements SearchResultsView {
    @Inject
    PresenterFactory<SearchResultsPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerSearchResultsViewComponent.builder()
                .appComponent(parentComponent)
                .searchResultsViewModule(new SearchResultsViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<SearchResultsPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
