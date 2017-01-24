package com.gjdev.hugo.gjant.data.event;

import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;

/**
 * Created by Hugo on 23/01/2017.
 */

public class RemoveCartItem {
    private SQLProduct product;

    public RemoveCartItem(SQLProduct product) {
        this.product = product;
    }

    public SQLProduct getProduct() {
        return product;
    }
}
