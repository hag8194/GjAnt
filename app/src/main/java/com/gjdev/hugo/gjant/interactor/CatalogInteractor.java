package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.model.Product;

public interface CatalogInteractor extends BaseInteractor {

    void retrieveProducts(boolean refresh);

    Product getProduct(int position);

    void postSelectedProduct(int id);

}