package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.ClientDetailFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = ClientDetailViewModule.class)
public interface ClientDetailViewComponent {
    void inject(ClientDetailFragment fragment);
}