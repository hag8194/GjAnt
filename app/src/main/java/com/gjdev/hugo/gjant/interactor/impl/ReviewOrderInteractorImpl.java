package com.gjdev.hugo.gjant.interactor.impl;

import android.content.Context;


import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.data.api.event.enterprise.ErrorEnterpriseRetrieve;
import com.gjdev.hugo.gjant.data.api.event.enterprise.FailEnterpriseRetrieve;
import com.gjdev.hugo.gjant.data.api.event.enterprise.SuccessEnterpriseRetrieve;
import com.gjdev.hugo.gjant.data.api.model.ApiError;
import com.gjdev.hugo.gjant.data.api.model.Enterprise;
import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.interactor.ReviewOrderInteractor;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class ReviewOrderInteractorImpl implements ReviewOrderInteractor {
    private ApiService mApiService;
    private ApiErrorHandler mApiErrorHandler;
    private InternalStorageHandler mInternalStorageHandler;
    private String [] mOrderTypeList;

    private Enterprise enterprise;

    @Inject
    public ReviewOrderInteractorImpl(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                     InternalStorageHandler internalStorageHandler, Context context) {
        mApiService = apiService;
        mApiErrorHandler = apiErrorHandler;
        mInternalStorageHandler = internalStorageHandler;
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
    public void retrieveEnterprise() {
        User user = (User)mInternalStorageHandler.readObject(R.string.user_data);

        Call<Enterprise> enterpriseCall = mApiService.enterprise(user.getAccessToken());

        if(enterprise == null) {
            enterpriseCall.enqueue(new Callback<Enterprise>() {
                @Override
                public void onResponse(Call<Enterprise> call, Response<Enterprise> response) {
                    if(response.isSuccessful()) {
                        enterprise = response.body();
                        postEvent(SUCCESS_EVENT, enterprise);
                    }
                    else
                        postEvent(ERROR_EVENT, mApiErrorHandler.parseError(response));
                }

                @Override
                public void onFailure(Call<Enterprise> call, Throwable t) {
                    postEvent(FAILURE_EVENT, t);
                }
            });
        }
        else
            postEvent(SUCCESS_EVENT, enterprise);
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {
        EventBus eventBus = EventBus.getDefault();
        switch (kindOfEvent) {
            case FAILURE_EVENT:
                eventBus.post(new FailEnterpriseRetrieve((Throwable) object));
                break;
            case SUCCESS_EVENT:
                eventBus.post(new SuccessEnterpriseRetrieve((Enterprise) object));
                break;
            case ERROR_EVENT:
                eventBus.post(new ErrorEnterpriseRetrieve((ApiError) object));
                break;
        }
    }
}