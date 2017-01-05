package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.event.SelectProduct;
import com.gjdev.hugo.gjant.data.model.Product;
import com.gjdev.hugo.gjant.view.CatalogView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CatalogPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.CatalogViewModule;
import com.gjdev.hugo.gjant.injection.DaggerCatalogViewComponent;
import com.gjdev.hugo.gjant.view.impl.adapter.ProductListAdapter;
import org.greenrobot.eventbus.EventBus;

import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public final class CatalogFragment extends BaseFragment<CatalogPresenter, CatalogView> implements CatalogView {

    @Inject
    PresenterFactory<CatalogPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.product_list)
    RecyclerView mRecyclerViewProductList;

    public CatalogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_catalog, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerCatalogViewComponent.builder()
                .appComponent(parentComponent)
                .catalogViewModule(new CatalogViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<CatalogPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setupRecyclerView() {
        mRecyclerViewProductList.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        //GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerViewProductList.setLayoutManager(mLayoutManager);
    }

    public void setupAdapter(List<Product> products) {
        ProductListAdapter adapter = new ProductListAdapter(products);
        adapter.setOnItemClickListener(new ProductListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mPresenter.onClickProductItem(position);
            }
        });
        mRecyclerViewProductList.setAdapter(adapter);
    }

    @Override
    public void sendProductEvent(int id) {
        EventBus.getDefault().postSticky(new SelectProduct(id));
    }

    @Override
    public void startDetailProductActivity() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new ProductDetailFragment(), ProductDetailFragment.class.getName())
                .addToBackStack(null)
                .commit();
        /*startActivity(new Intent(getContext(), DetailProductActivity.class),
                ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle());*/
    }

    @Override
    public boolean isRecyclerViewActivated() {
        return mRecyclerViewProductList.isActivated();
    }
}
