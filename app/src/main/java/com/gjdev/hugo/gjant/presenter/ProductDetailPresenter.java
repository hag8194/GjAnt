package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.api.event.product.ErrorProductRetrieve;
import com.gjdev.hugo.gjant.data.api.event.product.FailProductRetrieve;
import com.gjdev.hugo.gjant.data.event.ClickedRelatedArticleListItem;
import com.gjdev.hugo.gjant.data.event.NotifyProductCartStatus;
import com.gjdev.hugo.gjant.data.event.SelectedProduct;
import com.gjdev.hugo.gjant.data.event.SelectedProductImage;
import com.gjdev.hugo.gjant.data.api.event.product.SuccessProductRetrieve;
import com.gjdev.hugo.gjant.view.ProductDetailView;

public interface ProductDetailPresenter extends BasePresenter<ProductDetailView> {

    void onSelectedProduct(SelectedProduct selectedProduct);

    void onSuccessProductRetrieve(SuccessProductRetrieve retrieve);

    void onErrorProductRetrieve(ErrorProductRetrieve retrieve);

    void onFailProductRetrieve(FailProductRetrieve retrieve);

    void onSelectProductImage(SelectedProductImage productImage);

    void onClickedRelatedArticleListItem(ClickedRelatedArticleListItem listItem);

    void onAddToCart();

    void onNotifyProductCartStatus(NotifyProductCartStatus productStatus);

    void onSelectCartMenuItem();

}