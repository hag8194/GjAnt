package com.gjdev.hugo.gjant.view.impl;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.model.Employer;
import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.util.RoundedTransformation;
import com.gjdev.hugo.gjant.view.MainView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.MainPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.MainViewModule;
import com.gjdev.hugo.gjant.injection.DaggerMainViewComponent;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.image_header)
    ImageView mImageHeader;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.floating_action_button)
    FloatingActionButton mFloatingActionButton;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMainViewComponent.builder()
                .appComponent(parentComponent)
                .mainViewModule(new MainViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void setTextView(String test) {
        /*SharedPreferences sharedPreferences = appContext.getSharedPreferences(appContext.getString(R.string.sharepreferences),
                Context.MODE_PRIVATE);
        this.textView.setText(sharedPreferences.getString("access-token", "Nothing"));*/
        //textView.setText(test);
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(ButterKnife.findById(this, R.id.container), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setupDrawerToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.drawer_open, R.string.drawer_close);

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void resetImageHeader() {
        ViewGroup.LayoutParams layoutParams = mImageHeader.getLayoutParams();
        layoutParams.height = 120;
        mImageHeader.setLayoutParams(layoutParams);
        mImageHeader.setBackgroundResource(R.color.colorPrimary);
    }

    @Override
    public void resetFloatingActionButton() {
        if(mFloatingActionButton.getVisibility() == View.VISIBLE)
            mFloatingActionButton.setVisibility(View.GONE);
        mFloatingActionButton.setOnClickListener(null);
    }

    @Override
    public void setupNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                boolean selected = true;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        mPresenter.onHomeOptionSelected();
                        break;
                    case R.id.nav_catalog:
                        mPresenter.onCatalogOptionSelected();
                        break;
                    case R.id.nav_orders:
                        mPresenter.onOrdersOptionSelected();
                        break;
                    case R.id.nav_cart:
                        mPresenter.onCartOptionSelected();
                        break;
                    case R.id.nav_logout:
                        mPresenter.onLogoutOptionSelected();
                        break;
                    case R.id.nav_settings:
                        selected = false;
                        mPresenter.onSettingsOptionSelected();
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return selected;
            }
        });
    }

    @Override
    public void setupNavigationHeader(User user) {
        View headerView = mNavigationView.getHeaderView(0);
        Employer employer = user.getEmployer();

        ImageView avatar = ButterKnife.findById(headerView, R.id.header_avatar);
        TextView fullName = ButterKnife.findById(headerView, R.id.header_full_name);
        TextView createdAt = ButterKnife.findById(headerView, R.id.header_created_at);

        Picasso.with(this).load(user.getAvatar()).transform(new RoundedTransformation()).into(avatar);

        fullName.setText(employer.getName() + " " + employer.getLastname());
        createdAt.setText(SimpleDateFormat.getDateTimeInstance().format(employer.getCreated_at()));
    }

    @Override
    public void loadHomeFragment(boolean addToBackTrace) {
        HomeFragment fragment = new HomeFragment();
        loadFragment(fragment, addToBackTrace);
    }

    @Override
    public void loadCatalogFragment(boolean addToBackTrace) {
        CatalogFragment fragment = new CatalogFragment();
        loadFragment(fragment, addToBackTrace);
    }

    @Override
    public void loadOrdersFragment(boolean addToBackTrace) {
        OrdersFragment fragment = new OrdersFragment();
        loadFragment(fragment, addToBackTrace);
    }

    @Override
    public void loadCartFragment(boolean addToBackTrace) {
        CartFragment fragment = new CartFragment();
        loadFragment(fragment, addToBackTrace);
    }

    @Override
    public void loadProductDetailFragment(boolean addToBackStack) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        loadFragment(fragment, addToBackStack);
    }

    @Override
    public void loadCreateOrderFragment(boolean addToBackStack) {
        startActivity(new Intent(this, CreateOrderActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    public void loadClientDetailFragment(boolean addToBackStack) {
        ClientDetailFragment fragment = new ClientDetailFragment();
        loadFragment(fragment, addToBackStack);
    }

    @Override
    public void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getName());

        if(addToBackStack)
            fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }
}
