package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.ReviewOrderFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = ReviewOrderViewModule.class)
public interface ReviewOrderViewComponent {
    void inject(ReviewOrderFragment fragment);
}