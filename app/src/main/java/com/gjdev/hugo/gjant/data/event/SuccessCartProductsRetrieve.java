package com.gjdev.hugo.gjant.data.event;

import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;

import java.util.List;

/**
 * Created by Hugo on 04/01/2017.
 */

public class SuccessCartProductsRetrieve {
    private List<SQLProduct> productList;

    public SuccessCartProductsRetrieve(List<SQLProduct> productList) {
        this.productList = productList;
    }

    public List<SQLProduct> getProducts() {
        return productList;
    }
}
