package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.model.Product;
import com.gjdev.hugo.gjant.view.CatalogView;

import java.util.List;

public interface CatalogPresenter extends BasePresenter<CatalogView> {
    void onRetrieveProductListSuccess(List<Product> products);
    void onClickProductItem(int position);
}