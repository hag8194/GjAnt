package com.gjdev.hugo.gjant.injection;

import com.gjdev.hugo.gjant.view.impl.ProductDetailFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = ProductDetailViewModule.class)
public interface ProductDetailViewComponent {
    void inject(ProductDetailFragment fragment);
}