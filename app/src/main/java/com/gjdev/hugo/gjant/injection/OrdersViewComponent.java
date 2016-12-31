package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.OrdersFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = OrdersViewModule.class)
public interface OrdersViewComponent {
    void inject(OrdersFragment fragment);
}