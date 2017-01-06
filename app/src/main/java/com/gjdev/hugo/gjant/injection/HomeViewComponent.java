package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.HomeFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = HomeViewModule.class)
public interface HomeViewComponent {
    void inject(HomeFragment fragment);
}