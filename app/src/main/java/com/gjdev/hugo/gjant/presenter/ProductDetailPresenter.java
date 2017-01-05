package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.event.SelectProductImage;
import com.gjdev.hugo.gjant.data.event.product.SuccessProductRetrieve;
import com.gjdev.hugo.gjant.view.ProductDetailView;

public interface ProductDetailPresenter extends BasePresenter<ProductDetailView> {
    void onReceiveProductEvent(int id);
    void onSuccessProductRetrieve(SuccessProductRetrieve retrieve);
    void onSelectProductImage(SelectProductImage productImage);
}