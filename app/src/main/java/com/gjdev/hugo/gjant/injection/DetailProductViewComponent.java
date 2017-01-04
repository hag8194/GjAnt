package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.DetailProductFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = DetailProductViewModule.class)
public interface DetailProductViewComponent {
    void inject(DetailProductFragment fragment);
}