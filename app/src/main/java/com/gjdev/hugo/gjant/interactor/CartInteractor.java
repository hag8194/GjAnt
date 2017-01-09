package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;

public interface CartInteractor extends BaseInteractor {

    void retrieveProductsInCart();

    SQLProduct getProduct(int position);

    public void postSelectedProduct(int id);
}