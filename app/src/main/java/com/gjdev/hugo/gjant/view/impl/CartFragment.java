package com.gjdev.hugo.gjant.view.impl;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;
import com.gjdev.hugo.gjant.util.ItemSpaceDrawing;
import com.gjdev.hugo.gjant.view.CartView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.CartPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.CartViewModule;
import com.gjdev.hugo.gjant.injection.DaggerCartViewComponent;
import com.gjdev.hugo.gjant.view.impl.adapter.CartListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class CartFragment extends BaseFragment<CartPresenter, CartView> implements CartView {
    @Inject
    PresenterFactory<CartPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.product_list)
    RecyclerView mRecyclerView;

    private CartListAdapter adapter;
    private MainActivity mainActivity;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
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
        DaggerCartViewComponent.builder()
                .appComponent(parentComponent)
                .cartViewModule(new CartViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<CartPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void setTitle(int resString) {
        mainActivity.mCollapsingToolbarLayout.setTitle(getString(resString));
    }

    @Override
    public void setAppBarExpanded(boolean expanded) {
        mainActivity.mAppBarLayout.setExpanded(expanded, true);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setupRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setupAdapter(List<SQLProduct> products) {
        adapter = new CartListAdapter(products);
        mRecyclerView.setAdapter(adapter);
        setupItemTouchHelper();
        setUpAnimationDecoratorHelper();
    }

    @Override
    public void setupFloatingActionButton() {
        FloatingActionButton floatingActionButton = mainActivity.mFloatingActionButton;

        if(floatingActionButton.getVisibility() == View.GONE)
            floatingActionButton.setVisibility(View.VISIBLE);

        floatingActionButton.setImageResource(R.drawable.ic_add_white_24dp);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickCreateOrderButton();
            }
        });
    }

    @Override
    public void notifyDataChanged() {
        adapter.notifyDataSetChanged();
    }


    @Override
    public void startProductDetailFragment() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new ProductDetailFragment(), ProductDetailFragment.class.getName())
                .addToBackStack(null)
                .commit();
    }

    /**
     * This is the standard support library way of implementing "swipe to delete" feature. You can do custom drawing in onChildDraw method
     * but whatever you draw will disappear once the swipe is over, and while the items are animating to their new position the recycler view
     * background will be visible. That is rarely an desired effect.
     */
    private void setupItemTouchHelper(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // we want to cache these and not allocate anything repeatedly in the onChildDraw method
            Drawable background;
            Drawable xMark;
            int xMarkMargin;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(ContextCompat.getColor(getContext(), R.color.colorAccent));
                xMark = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_white_24dp);
                xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                xMarkMargin = (int) getContext().getResources().getDimension(R.dimen.ic_clear_margin);
                initiated = true;
            }

            // not important, we don't want drag & drop
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                CartListAdapter adapter = (CartListAdapter)recyclerView.getAdapter();
                if (adapter.isPendingRemoval(position)) {
                    return 0;
                }

                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int swipedPosition = viewHolder.getAdapterPosition();
                CartListAdapter adapter = (CartListAdapter)mRecyclerView.getAdapter();
                adapter.pendingRemoval(swipedPosition);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;

                // not sure why, but this method get's called for viewholder that are already swiped away
                if (viewHolder.getAdapterPosition() == -1) {
                    // not interested in those
                    return;
                }

                if (!initiated) {
                    init();
                }

                // draw red background
                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(c);

                // draw x mark
                int itemHeight = itemView.getBottom() - itemView.getTop();
                int intrinsicWidth = xMark.getIntrinsicWidth();
                int intrinsicHeight = xMark.getIntrinsicWidth();

                int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
                int xMarkRight = itemView.getRight() - xMarkMargin;
                int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight)/2;
                int xMarkBottom = xMarkTop + intrinsicHeight;
                xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

                xMark.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * We're gonna setup another ItemDecorator that will draw the red background in the empty space while the items are animating to thier new positions
     * after an item is removed.
     */
    private void setUpAnimationDecoratorHelper() {
        mRecyclerView.addItemDecoration(new ItemSpaceDrawing(getContext()));
    }

}
