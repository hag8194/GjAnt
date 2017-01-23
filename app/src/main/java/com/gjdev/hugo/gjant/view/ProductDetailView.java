package com.gjdev.hugo.gjant.view;

import android.graphics.drawable.Drawable;
import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.api.model.Children;
import com.gjdev.hugo.gjant.data.api.model.Product;
import com.gjdev.hugo.gjant.data.api.model.ProductImages;

import java.util.List;

@UiThread
public interface ProductDetailView {

    void setTitle(String title);

    void setAppBarExpanded(boolean expanded);

    void showSnackbar(String message);

    void showProgressBar();

    void hideProgressBar();

    void setupRecyclerViews();

    void setupAdapters(List<ProductImages> productImagesList, List<Children> childrenList);

    void setupFloatingActionButton();

    void setupAnimation();

    void setProductData(Product product);

    void changeProductPoster(Drawable image);

    int getProductQuantity();

    void startProductDetailFragment();

}