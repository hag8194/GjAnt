package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = LoginViewModule.class)
public interface LoginViewComponent {
    void inject(LoginActivity activity);
}