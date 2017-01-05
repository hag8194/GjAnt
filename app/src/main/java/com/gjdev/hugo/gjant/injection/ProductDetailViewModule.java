package com.gjdev.hugo.gjant.injection;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.interactor.ProductDetailInteractor;
import com.gjdev.hugo.gjant.interactor.impl.ProductDetailInteractorImpl;
import com.gjdev.hugo.gjant.presenter.ProductDetailPresenter;
import com.gjdev.hugo.gjant.presenter.impl.ProductDetailPresenterImpl;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import dagger.Module;
import dagger.Provides;

@Module
public final class ProductDetailViewModule {
    @Provides
    public ProductDetailInteractor provideInteractor(ApiService apiService, ApiErrorHandler apiErrorHandler, InternalStorageHandler internalStorageHandler) {
        return new ProductDetailInteractorImpl(apiService, apiErrorHandler, internalStorageHandler);
    }

    @Provides
    public PresenterFactory<ProductDetailPresenter> providePresenterFactory(@NonNull final ProductDetailInteractor interactor) {
        return new PresenterFactory<ProductDetailPresenter>() {
            @NonNull
            @Override
            public ProductDetailPresenter create() {
                return new ProductDetailPresenterImpl(interactor);
            }
        };
    }
}
