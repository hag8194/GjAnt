package com.gjdev.hugo.gjant.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.util.RoundedTransformation;
import com.gjdev.hugo.gjant.view.ClientDetailView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.ClientDetailPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.ClientDetailViewModule;
import com.gjdev.hugo.gjant.injection.DaggerClientDetailViewComponent;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public final class ClientDetailFragment extends BaseFragment<ClientDetailPresenter, ClientDetailView> implements ClientDetailView {
    @Inject
    PresenterFactory<ClientDetailPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindViews({R.id.client_fullname, R.id.client_identification, R.id.client_phone1,
            R.id.client_phone2, R.id.client_address, R.id.client_limit_credit, R.id.client_created_at})
    List<TextView> clientData;

    private MainActivity mainActivity;
    private CollapsingToolbarLayout.LayoutParams mImageHeaderDefaultLayoutParams;
    private AppBarLayout.LayoutParams mCollapsingToolbarLayoutDefaultParams;

    public ClientDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_client_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mainActivity = (MainActivity)getActivity();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerClientDetailViewComponent.builder()
                .appComponent(parentComponent)
                .clientDetailViewModule(new ClientDetailViewModule())
                .build()
                .inject(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mCollapsingToolbarLayoutDefaultParams != null && mImageHeaderDefaultLayoutParams != null){
            mainActivity.mCollapsingToolbarLayout.setLayoutParams(mCollapsingToolbarLayoutDefaultParams);
            mainActivity.mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsingToolbarTextAppearance_Expanded);
            mainActivity.mImageHeader.setImageDrawable(null);
            mainActivity.mImageHeader.setLayoutParams(mImageHeaderDefaultLayoutParams);
        }
    }

    @NonNull
    @Override
    protected PresenterFactory<ClientDetailPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void setAppBarExpanded(boolean expanded) {
        mainActivity.mAppBarLayout.setExpanded(expanded, true);
    }

    @Override
    public void setTitle(String title) {
        mainActivity.mCollapsingToolbarLayout.setTitle(title);
    }

    @Override
    public void setupCollapsingToolbarLayout() {
        mCollapsingToolbarLayoutDefaultParams = (AppBarLayout.LayoutParams)mainActivity.mCollapsingToolbarLayout.getLayoutParams();
        AppBarLayout.LayoutParams layoutParams = new AppBarLayout.LayoutParams(mCollapsingToolbarLayoutDefaultParams.width, (int)getResources().getDimension(R.dimen.collapsing_layout_height));
        mainActivity.mCollapsingToolbarLayout.setLayoutParams(layoutParams);
        mainActivity.mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsingToolbarClientTextAppearance_Expanded);
    }

    @Override
    public void setImageHeader(String url) {
        ImageView imageView = mainActivity.mImageHeader;
        mImageHeaderDefaultLayoutParams = (CollapsingToolbarLayout.LayoutParams)imageView.getLayoutParams();

        CollapsingToolbarLayout.LayoutParams layoutParams = new CollapsingToolbarLayout.LayoutParams(mImageHeaderDefaultLayoutParams.width, mImageHeaderDefaultLayoutParams.height);

        layoutParams.setMargins(0, (int)getResources().getDimension(R.dimen.image_header_top_margin), 0, (int)getResources().getDimension(R.dimen.image_header_bottom_margin));
        imageView.setLayoutParams(layoutParams);

        Picasso.with(getContext()).load(url).transform(new RoundedTransformation()).into(mainActivity.mImageHeader);
    }

    @Override
    public void setClientData(String[] data) {
        for (int i = 0; i < clientData.size(); i++)
            clientData.get(i).setText(data[i]);
    }
}
