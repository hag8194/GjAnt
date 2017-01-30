package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.api.model.Product;

import java.util.List;

public interface CatalogInteractor extends BaseInteractor {

    void retrieveProducts(boolean refresh);

    Product getProduct(int position);

    void postSelectedProduct(int id);

    List<Product> search(String query);

}