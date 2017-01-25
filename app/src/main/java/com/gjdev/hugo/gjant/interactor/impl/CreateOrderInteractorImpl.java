package com.gjdev.hugo.gjant.interactor.impl;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.data.api.event.createorder.SuccessCreateOrder;
import com.gjdev.hugo.gjant.data.api.event.products.ErrorProductsRetrieve;
import com.gjdev.hugo.gjant.data.api.event.products.FailProductsRetrieve;
import com.gjdev.hugo.gjant.data.api.model.ApiError;
import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.data.api.model.response.CreatedResponseMessage;
import com.gjdev.hugo.gjant.data.event.NotifyOrderParams;
import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.data.event.ValidOrderForm;
import com.gjdev.hugo.gjant.data.sql.event.SuccessCartProductsRetrieve;
import com.gjdev.hugo.gjant.data.sql.model.DaoSession;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;
import com.gjdev.hugo.gjant.interactor.CreateOrderInteractor;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.sql.Timestamp;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class CreateOrderInteractorImpl implements CreateOrderInteractor {
    private ApiService mApiService;
    private ApiErrorHandler mApiErrorHandler;
    private InternalStorageHandler mInternalStorageHandler;
    private DaoSession mDaoSession;

    private String mOrderCode;
    private SelectedClientWallet mSelectedClientWallet;
    private ValidOrderForm mValidOrderForm;
    private List<SQLProduct> mProductList;

    @Inject
    public CreateOrderInteractorImpl(ApiService mApiService, ApiErrorHandler mApiErrorHandler,
                                     InternalStorageHandler mInternalStorageHandler, DaoSession daoSession) {
        this.mApiService = mApiService;
        this.mApiErrorHandler = mApiErrorHandler;
        this.mInternalStorageHandler = mInternalStorageHandler;
        this.mDaoSession = daoSession;
    }

    @Override
    public void setSelectedClientWallet(SelectedClientWallet selectedClientWallet) {
        mSelectedClientWallet = selectedClientWallet;
    }

    @Override
    public void setValidOrderForm(ValidOrderForm validOrderForm) {
        mValidOrderForm = validOrderForm;
    }

    @Override
    public void createOrderCode() {
        mOrderCode = "ORD" + mSelectedClientWallet.getClientWallet().getId() + new Timestamp(System.currentTimeMillis()).getTime();
    }

    private String getTotal() {
        double acum = 0.0;
        int quantity;

        for (SQLProduct product : mProductList) {
            quantity = product.getQuantity();
            acum += quantity * product.getPrice();
        }

        return String.valueOf(acum);
    }

    @Override
    public User getUser() {
        return (User) mInternalStorageHandler.readObject(R.string.user_data);
    }

    @Override
    public void retrieveProductsInCart() {
        if(mProductList == null) {
            mProductList = mDaoSession.getSQLProductDao().queryBuilder().list();
            postEvent(SUCCESS_EVENT, null);
        }
        else
            postEvent(SUCCESS_EVENT, null);
    }

    @Override
    public void deleteProductsInCart() {
        mDaoSession.getSQLProductDao().deleteAll();
    }

    @Override
    public void postOrderParams() {
        EventBus.getDefault().post(new NotifyOrderParams(mOrderCode, getTotal()));
    }

    @Override
    public void createOrder() {
        Call<CreatedResponseMessage> responseMessageCall = mApiService.createOrder(getUser().getAccessToken(),
                mOrderCode,
                mValidOrderForm.getDescription(),
                mValidOrderForm.getTypePosition() - 1,
                mSelectedClientWallet.getClientWallet().getId(),
                new Gson().toJson(mProductList));

        responseMessageCall.enqueue(new Callback<CreatedResponseMessage>() {
            @Override
            public void onResponse(Call<CreatedResponseMessage> call, Response<CreatedResponseMessage> response) {
                if(response.isSuccessful() && response.code() == 201){
                    postEvent(SUCCESS_EVENT, response.body());
                }
                else
                    postEvent(ERROR_EVENT, mApiErrorHandler.parseError(response));
            }

            @Override
            public void onFailure(Call<CreatedResponseMessage> call, Throwable t) {
                postEvent(FAILURE_EVENT, t);
            }
        });
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {
        EventBus eventBus = EventBus.getDefault();
        switch (kindOfEvent){
            case FAILURE_EVENT:
                eventBus.post(new FailProductsRetrieve((Throwable) object));
                break;
            case SUCCESS_EVENT:
                if(object == null)
                    eventBus.post(new SuccessCartProductsRetrieve(mProductList));
                else
                    eventBus.postSticky(new SuccessCreateOrder((CreatedResponseMessage) object));
                break;
            case ERROR_EVENT:
                eventBus.post(new ErrorProductsRetrieve((ApiError) object));
                break;
        }
    }
}