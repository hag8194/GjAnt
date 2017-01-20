package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.api.model.User;

public interface ReviewOrderInteractor extends BaseInteractor {

    User getUser();

    String getOrderType(int index);

    String getDate();

    void retrieveProductsInCart();

    String getTotal();
}