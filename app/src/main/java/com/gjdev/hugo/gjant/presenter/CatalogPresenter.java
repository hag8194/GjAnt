package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.event.ClickedProductListItem;
import com.gjdev.hugo.gjant.data.event.RefreshedList;
import com.gjdev.hugo.gjant.data.api.event.products.ErrorProductsRetrieve;
import com.gjdev.hugo.gjant.data.api.event.products.FailProductsRetrieve;
import com.gjdev.hugo.gjant.data.api.event.products.SuccessProductsRetrieve;
import com.gjdev.hugo.gjant.view.CatalogView;

public interface CatalogPresenter extends BasePresenter<CatalogView> {

    void onSuccessProductsRetrieve(SuccessProductsRetrieve productsRetrieve);

    void onErrorProductsRetrieve(ErrorProductsRetrieve productsRetrieve);

    void onFailProductsRetrieve(FailProductsRetrieve productsRetrieve);

    void onClickedProductListItem(ClickedProductListItem listItem);

    void onRefreshRequest();

    void onRefreshedList(RefreshedList refreshedList);

}