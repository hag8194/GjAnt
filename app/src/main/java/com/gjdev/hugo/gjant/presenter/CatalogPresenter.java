package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.event.products.ErrorProductsRetrieve;
import com.gjdev.hugo.gjant.data.event.products.FailProductsRetrieve;
import com.gjdev.hugo.gjant.data.event.products.SuccessProductsRetrieve;
import com.gjdev.hugo.gjant.view.CatalogView;

public interface CatalogPresenter extends BasePresenter<CatalogView> {

    void onSuccessProductsRetrieve(SuccessProductsRetrieve productsRetrieve);

    void onErrorProductsRetrieve(ErrorProductsRetrieve productsRetrieve);

    void onFailProductsRetrieve(FailProductsRetrieve productsRetrieve);

    void onClickProductItem(int position);

}