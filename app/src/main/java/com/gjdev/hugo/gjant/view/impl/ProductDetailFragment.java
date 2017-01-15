package com.gjdev.hugo.gjant.view.impl;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.model.Children;
import com.gjdev.hugo.gjant.data.api.model.Product;
import com.gjdev.hugo.gjant.data.api.model.ProductImages;
import com.gjdev.hugo.gjant.presenter.ProductDetailPresenter;
import com.gjdev.hugo.gjant.view.ProductDetailView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.ProductDetailViewModule;
import com.gjdev.hugo.gjant.injection.DaggerProductDetailViewComponent;
import com.gjdev.hugo.gjant.view.impl.adapter.ProductImagesAdapter;
import com.gjdev.hugo.gjant.view.impl.adapter.RelatedArticlesAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class ProductDetailFragment extends BaseFragment<ProductDetailPresenter, ProductDetailView> implements ProductDetailView {
    @Inject
    PresenterFactory<ProductDetailPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.product_name)
    TextView productName;

    @BindView(R.id.product_price)
    TextView productPrice;

    @BindView(R.id.product_quantity)
    NumberPicker productQuantity;

    @BindView(R.id.brand_name)
    TextView productBrandName;

    @BindView(R.id.product_description)
    TextView productDescription;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.imageView)
    ImageView productImage;

    @BindView(R.id.product_images)
    RecyclerView mProductImagesRecyclerView;

    @BindView(R.id.related_articles)
    RecyclerView mRelatedArticlesRecyclerView;

    private Animation fadeAnimation;

    @OnClick(R.id.add_to_cart)
    public void onClick() {
        mPresenter.onAddToCart();
    }

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_product_detail_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean selected = super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.cart:
                mPresenter.onSelectCartMenuItem();
                selected = true;
                break;
        }
        return selected;
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerProductDetailViewComponent.builder()
                .appComponent(parentComponent)
                .productDetailViewModule(new ProductDetailViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<ProductDetailPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setupRecyclerViews() {
        mProductImagesRecyclerView.setHasFixedSize(true);
        mRelatedArticlesRecyclerView.setHasFixedSize(true);

        mProductImagesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRelatedArticlesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void setupAdapters(List<ProductImages> productImagesList, List<Children> childrenList) {
        ProductImagesAdapter imagesAdapter = new ProductImagesAdapter(productImagesList);
        RelatedArticlesAdapter relatedArticlesAdapter = new RelatedArticlesAdapter(childrenList);
        mProductImagesRecyclerView.setAdapter(imagesAdapter);
        mRelatedArticlesRecyclerView.setAdapter(relatedArticlesAdapter);
    }

    @Override
    public void setupAnimation() {
        fadeAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
    }

    @Override
    public void setProductData(Product product) {
        Picasso.with(getContext())
                .load(product.get_links().getPoster().getHref())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(productImage);

        productName.setText(product.getName());
        productPrice.setText(String.valueOf(product.getPrice()));
        productBrandName.setText(product.getBrand().getName());

        productQuantity.setMaxValue(Integer.parseInt(product.getQuantity()));
        productQuantity.setMinValue(1);
    }

    @Override
    public void changeProductPoster(Drawable image) {
        productImage.startAnimation(fadeAnimation);
        productImage.setImageDrawable(image);
    }

    @Override
    public int getProductQuantity() {
        return productQuantity.getValue();
    }

    @Override
    public void startProductDetailFragment() {
        loadFragment(new ProductDetailFragment());
    }

    private void loadFragment(Fragment fragment){
        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
