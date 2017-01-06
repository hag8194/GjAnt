package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.OrderDetailFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = OrderDetailViewModule.class)
public interface OrderDetailViewComponent {
    void inject(OrderDetailFragment fragment);
}