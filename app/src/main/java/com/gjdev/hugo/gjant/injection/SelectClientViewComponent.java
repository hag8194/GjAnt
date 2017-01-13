package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.SelectClientFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = SelectClientViewModule.class)
public interface SelectClientViewComponent {
    void inject(SelectClientFragment fragment);
}