package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;

public interface CartInteractor extends BaseInteractor {

    void retrieveProductsInCart();

    SQLProduct getProduct(int position);

    void postSelectedProduct(int id);

    void removeCartItem(SQLProduct product);

    boolean isCartEmpty();
}