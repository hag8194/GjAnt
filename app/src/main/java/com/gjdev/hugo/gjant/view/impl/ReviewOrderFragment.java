package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;
import com.gjdev.hugo.gjant.view.ReviewOrderView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.ReviewOrderPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.ReviewOrderViewModule;
import com.gjdev.hugo.gjant.injection.DaggerReviewOrderViewComponent;
import com.gjdev.hugo.gjant.view.impl.adapter.CartListAdapter;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public final class ReviewOrderFragment extends BaseFragment<ReviewOrderPresenter, ReviewOrderView> implements ReviewOrderView, Step {
    @Inject
    PresenterFactory<ReviewOrderPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.order_code)
    TextView mOrderCode;

    @BindViews({R.id.order_type, R.id.order_date, R.id.order_description})
    List<TextView> mOrderData;

    @BindView(R.id.order_total)
    TextView mOrderTotal;

    @BindViews({R.id.enterprise_name, R.id.enterprise_rif, R.id.enterprise_phone, R.id.enterprise_address})
    List<TextView> mEnterpriseData;

    @BindViews({R.id.vendor_name, R.id.vendor_lastname, R.id.vendor_identification})
    List<TextView> mVendorData;

    @BindViews({R.id.client_fullname, R.id.client_identification, R.id.client_phone1, R.id.client_phone2, R.id.client_address})
    List<TextView> mClientData;

    @BindView(R.id.product_list)
    RecyclerView mRecyclerViewProductList;

    public ReviewOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review_order, container, false);
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
        DaggerReviewOrderViewComponent.builder()
                .appComponent(parentComponent)
                .reviewOrderViewModule(new ReviewOrderViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<ReviewOrderPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    private void setTextViews(List<TextView> textViews, String [] data) {
        for (int i = 0; i < textViews.size(); i++)
            textViews.get(i).setText(data[i]);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setOrderCode(String code) {
        mOrderCode.setText(code);
    }

    @Override
    public void setOrderData(String data[]) {
        setTextViews(mOrderData, data);
    }

    @Override
    public void setOrderTotal(String total) {
        mOrderTotal.setText(total);
    }

    @Override
    public void setEnterpriseData(String[] data) {
        setTextViews(mEnterpriseData, data);
    }

    @Override
    public void setVendorData(String data[]) {
        setTextViews(mVendorData, data);
    }

    @Override
    public void setClientData(String[] data) {
        setTextViews(mClientData, data);
    }

    @Override
    public void setupRecyclerView(List<SQLProduct> products) {
        mRecyclerViewProductList.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerViewProductList.setLayoutManager(mLayoutManager);
        mRecyclerViewProductList.setAdapter(new CartListAdapter(products));
    }
}
