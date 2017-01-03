package com.gjdev.hugo.gjant.interactor.impl;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.data.model.Product;
import com.gjdev.hugo.gjant.data.model.User;
import com.gjdev.hugo.gjant.interactor.CatalogInteractor;
import com.gjdev.hugo.gjant.presenter.CatalogPresenter;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class CatalogInteractorImpl implements CatalogInteractor {
    private ApiService mApiService;
    private ApiErrorHandler mApiErrorHandler;
    private InternalStorageHandler mInternalStorageHandler;

    @Inject
    public CatalogInteractorImpl(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                 InternalStorageHandler internalStorageHandler) {
        mApiService = apiService;
        mApiErrorHandler = apiErrorHandler;
        mInternalStorageHandler = internalStorageHandler;
    }

    @Override
    public void retrieveProducts(final CatalogPresenter catalogPresenter) {
        User user = (User)mInternalStorageHandler.readObject(R.string.user_data);

        Call<List<Product>> products = mApiService.products(user.getAccessToken());

        products.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products = response.body();
                catalogPresenter.onRetrieveProductListSuccess(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}