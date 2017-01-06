package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.ProfileFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = ProfileViewModule.class)
public interface ProfileViewComponent {
    void inject(ProfileFragment fragment);
}