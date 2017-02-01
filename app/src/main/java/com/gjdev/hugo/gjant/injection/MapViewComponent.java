package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.MapActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MapViewModule.class)
public interface MapViewComponent {
    void inject(MapActivity activity);
}