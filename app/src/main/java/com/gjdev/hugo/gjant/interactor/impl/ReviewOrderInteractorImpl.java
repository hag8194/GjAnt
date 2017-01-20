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

public final class ReviewOrderInteractorImpl implements ReviewOrderInteractor {;
    private InternalStorageHandler mInternalStorageHandler;
    private String [] mOrderTypeList;

    @Inject
    public ReviewOrderInteractorImpl(InternalStorageHandler mInternalStorageHandler, Context context) {
        this.mInternalStorageHandler = mInternalStorageHandler;
        mOrderTypeList = context.getResources().getStringArray(R.array.order_type);
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
    public void postEvent(int kindOfEvent, Object object) {

    }
}