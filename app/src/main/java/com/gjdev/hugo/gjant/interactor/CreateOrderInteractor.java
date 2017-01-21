package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.data.event.ValidOrderForm;

public interface CreateOrderInteractor extends BaseInteractor {

    void setSelectedClientWallet(SelectedClientWallet selectedClientWallet);

    void setValidOrderForm(ValidOrderForm validOrderForm);

    void createOrderCode();

    User getUser();

    void retrieveProductsInCart();

    void deleteProductsInCart();

    void postOrderParams();

    void createOrder();

}