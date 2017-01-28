package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.api.event.orders.ErrorOrdersRetrieve;
import com.gjdev.hugo.gjant.data.api.event.orders.FailOrdersRetrieve;
import com.gjdev.hugo.gjant.data.api.event.orders.SuccessOrdersRetrieve;
import com.gjdev.hugo.gjant.view.OrdersView;

public interface OrdersPresenter extends BasePresenter<OrdersView> {

    void onSuccessOrdersRetrieve(SuccessOrdersRetrieve retrieve);

    void onErrorOrdersRetrieve(ErrorOrdersRetrieve retrieve);

    void onFailOrdersRetrieve(FailOrdersRetrieve retrieve);

    void onRefreshRequest();
}