package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.SearchResultsActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SearchResultsViewModule.class)
public interface SearchResultsViewComponent {
    void inject(SearchResultsActivity activity);
}