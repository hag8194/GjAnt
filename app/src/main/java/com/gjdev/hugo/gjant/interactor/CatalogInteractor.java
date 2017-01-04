package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.model.Product;
import com.gjdev.hugo.gjant.presenter.CatalogPresenter;

public interface CatalogInteractor extends BaseInteractor {
    void retrieveProducts(final CatalogPresenter catalogPresenter);
    Product getProduct(int position);
}