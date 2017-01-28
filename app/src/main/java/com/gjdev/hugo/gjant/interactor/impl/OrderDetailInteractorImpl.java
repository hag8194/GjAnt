package com.gjdev.hugo.gjant.interactor.impl;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.data.api.event.orders.ErrorOrdersRetrieve;
import com.gjdev.hugo.gjant.data.api.event.orders.FailOrdersRetrieve;
import com.gjdev.hugo.gjant.data.api.event.orders.SuccessOrdersRetrieve;
import com.gjdev.hugo.gjant.data.api.model.ApiError;
import com.gjdev.hugo.gjant.data.api.model.Order;
import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.interactor.OrderDetailInteractor;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class OrderDetailInteractorImpl implements OrderDetailInteractor {
    private ApiService mApiService;
    private ApiErrorHandler mApiErrorHandler;
    private InternalStorageHandler mInternalStorageHandler;

    private Order order;

    @Inject
    public OrderDetailInteractorImpl(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                     InternalStorageHandler internalStorageHandler) {
        mApiService = apiService;
        mApiErrorHandler = apiErrorHandler;
        mInternalStorageHandler = internalStorageHandler;
    }

    @Override
    public void retrieveOrder() {
               /*User user = (User)mInternalStorageHandler.readObject(R.string.user_data);

        Call<Order> ordersCall = mApiService.orders(user.getAccessToken(), user.getEmployer().getId());

        if(ordersList == null || refresh) {
            ordersCall.enqueue(new Callback<List<Order>>() {
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    if(response.isSuccessful()) {
                        ordersList = response.body();
                        postEvent(SUCCESS_EVENT, null);
                    }
                    else
                        postEvent(ERROR_EVENT, mApiErrorHandler.parseError(response));
                }

                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    postEvent(FAILURE_EVENT, t);
                }
            });
        }
        else
            postEvent(SUCCESS_EVENT, null);*/
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {
        /*EventBus eventBus = EventBus.getDefault();
        switch (kindOfEvent) {
            case FAILURE_EVENT:
                eventBus.post(new FailOrderRetrieve((Throwable) object));
                break;
            case SUCCESS_EVENT:
                eventBus.post(new SuccessOrderRetrieve((Order)object));
                break;
            case ERROR_EVENT:
                eventBus.post(new ErrorOrderRetrieve((ApiError) object));
                break;
        }*/
    }
}