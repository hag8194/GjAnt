package com.gjdev.hugo.gjant.view;

import android.graphics.drawable.Drawable;
import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.model.Children;
import com.gjdev.hugo.gjant.data.model.Product;
import com.gjdev.hugo.gjant.data.model.ProductImages;

import java.util.List;

@UiThread
public interface ProductDetailView {
    void showSnackbar(String message);

    void showProgressBar();

    void hideProgressBar();

    void setupRecyclerViews();

    void setupAdapters(List<ProductImages> productImagesList, List<Children> childrenList);

    void setProductData(Product product);

    void changeProductPoster(Drawable image);
}