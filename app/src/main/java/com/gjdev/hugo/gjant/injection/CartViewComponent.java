package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.CartFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = CartViewModule.class)
public interface CartViewComponent {
    void inject(CartFragment fragment);
}