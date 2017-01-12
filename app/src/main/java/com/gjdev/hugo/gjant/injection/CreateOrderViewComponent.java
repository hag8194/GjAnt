package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.CreateOrderFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = CreateOrderViewModule.class)
public interface CreateOrderViewComponent {
    void inject(CreateOrderFragment fragment);
}