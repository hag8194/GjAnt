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
import com.gjdev.hugo.gjant.interactor.OrdersInteractor;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class OrdersInteractorImpl implements OrdersInteractor {
    private ApiService mApiService;
    private ApiErrorHandler mApiErrorHandler;
    private InternalStorageHandler mInternalStorageHandler;

    private List<Order> ordersList;

    @Inject
    public OrdersInteractorImpl(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                InternalStorageHandler internalStorageHandler) {
        mApiService = apiService;
        mApiErrorHandler = apiErrorHandler;
        mInternalStorageHandler = internalStorageHandler;
    }

    @Override
    public void retrieveOrders(boolean refresh) {
        User user = (User)mInternalStorageHandler.readObject(R.string.user_data);

        Call<List<Order>> productsCall = mApiService.orders(user.getAccessToken(), user.getEmployer().getId());

        if(ordersList == null || refresh) {
            productsCall.enqueue(new Callback<List<Order>>() {
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
            postEvent(SUCCESS_EVENT, null);
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {
        EventBus eventBus = EventBus.getDefault();
        switch (kindOfEvent) {
            case FAILURE_EVENT:
                eventBus.post(new FailOrdersRetrieve((Throwable) object));
                break;
            case SUCCESS_EVENT:
                eventBus.post(new SuccessOrdersRetrieve(ordersList));
                break;
            case ERROR_EVENT:
                eventBus.post(new ErrorOrdersRetrieve((ApiError) object));
                break;
        }
    }
}