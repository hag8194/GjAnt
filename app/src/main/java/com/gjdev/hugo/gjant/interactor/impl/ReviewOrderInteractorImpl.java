package com.gjdev.hugo.gjant.interactor.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.data.sql.event.SuccessCartProductsRetrieve;
import com.gjdev.hugo.gjant.data.sql.model.DaoSession;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;
import com.gjdev.hugo.gjant.interactor.ReviewOrderInteractor;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class ReviewOrderInteractorImpl implements ReviewOrderInteractor {
    private ApiService mApiService;
    private ApiErrorHandler mApiErrorHandler;
    private InternalStorageHandler mInternalStorageHandler;
    private DaoSession mDaoSession;
    private Context mContext;

    private String [] mOrderTypeList;
    private List<SQLProduct> mProductList;

    @Inject
    public ReviewOrderInteractorImpl(ApiService mApiService, ApiErrorHandler mApiErrorHandler,
                                     InternalStorageHandler mInternalStorageHandler, DaoSession daoSession, Context context) {
        this.mApiService = mApiService;
        this.mApiErrorHandler = mApiErrorHandler;
        this.mInternalStorageHandler = mInternalStorageHandler;
        this.mContext = context;
        this.mDaoSession = daoSession;

        mOrderTypeList = mContext.getResources().getStringArray(R.array.order_type);
    }

    @Override
    public User getUser() {
        return (User) mInternalStorageHandler.readObject(R.string.user_data);
    }

    @Override
    public String getOrderType(int index) {
        return mOrderTypeList[index];
    }

    @Override
    public String getDate() {
        DateFormat format = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        return format.format(new Date());
    }

    @Override
    public void retrieveProductsInCart() {
        if(mProductList == null) {
            mProductList = mDaoSession.getSQLProductDao().queryBuilder().list();
            mDaoSession.getDatabase().close();
            postEvent(SUCCESS_EVENT, null);
        }
        else
            postEvent(SUCCESS_EVENT, null);
    }

    @Override
    public String getTotal() {
        double acum = 0.0;
        int quantity;

        for (SQLProduct product : mProductList) {
            quantity = product.getQuantity();
            acum += quantity * product.getPrice();
        }

        return String.valueOf(acum);
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {
        EventBus eventBus = EventBus.getDefault();
        switch (kindOfEvent){
            case SUCCESS_EVENT:
                eventBus.post(new SuccessCartProductsRetrieve(mProductList));
                break;
        }
    }
}