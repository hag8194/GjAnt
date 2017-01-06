package com.gjdev.hugo.gjant.interactor.impl;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.data.api.event.product.ErrorProductRetrieve;
import com.gjdev.hugo.gjant.data.api.event.product.FailProductRetrieve;
import com.gjdev.hugo.gjant.data.api.event.product.SuccessProductRetrieve;
import com.gjdev.hugo.gjant.data.model.ApiError;
import com.gjdev.hugo.gjant.data.model.Product;
import com.gjdev.hugo.gjant.data.model.User;
import com.gjdev.hugo.gjant.interactor.ProductDetailInteractor;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class ProductDetailInteractorImpl implements ProductDetailInteractor {
    private ApiService mApiService;
    private ApiErrorHandler mApiErrorHandler;
    private InternalStorageHandler mInternalStorageHandler;
    private Product product;

    @Inject
    public ProductDetailInteractorImpl(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                       InternalStorageHandler internalStorageHandler) {
        mApiService = apiService;
        mApiErrorHandler = apiErrorHandler;
        mInternalStorageHandler = internalStorageHandler;
    }

    @Override
    public void retrieveProductDetail(int id) {
        User user = (User)mInternalStorageHandler.readObject(R.string.user_data);

        Call<Product> productCall = mApiService.product(id, user.getAccessToken());

        if(product == null){
            productCall.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if(response.isSuccessful()) {
                        product = response.body();
                        postEvent(SUCCESS_EVENT, product);
                    }
                    else
                        postEvent(ERROR_EVENT, mApiErrorHandler.parseError(response));
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    postEvent(FAILURE_EVENT, t);
                }
            });
        }
        else
            postEvent(SUCCESS_EVENT, product);
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {
        EventBus eventBus = EventBus.getDefault();
        switch (kindOfEvent) {
            case FAILURE_EVENT:
                eventBus.post(new FailProductRetrieve((Throwable) object));
                break;
            case SUCCESS_EVENT:
                eventBus.post(new SuccessProductRetrieve((Product) object));
                break;
            case ERROR_EVENT:
                eventBus.post(new ErrorProductRetrieve((ApiError) object));
                break;
        }
    }
}