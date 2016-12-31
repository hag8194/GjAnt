package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.CatalogFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = CatalogViewModule.class)
public interface CatalogViewComponent {
    void inject(CatalogFragment fragment);
}