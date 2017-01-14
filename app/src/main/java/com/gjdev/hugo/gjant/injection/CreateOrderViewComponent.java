package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.CreateOrderActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = CreateOrderViewModule.class)
public interface CreateOrderViewComponent {
    void inject(CreateOrderActivity activity);
}