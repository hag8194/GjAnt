package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.OrderFormFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = OrderFormViewModule.class)
public interface OrderFormViewComponent {
    void inject(OrderFormFragment fragment);
}