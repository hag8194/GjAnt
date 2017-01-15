package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.event.CollapseAppBarLayout;
import com.gjdev.hugo.gjant.presenter.CreateOrderPresenter;
import com.gjdev.hugo.gjant.view.CreateOrderView;
import com.gjdev.hugo.gjant.interactor.CreateOrderInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public final class CreateOrderPresenterImpl extends BasePresenterImpl<CreateOrderView> implements CreateOrderPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final CreateOrderInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public CreateOrderPresenterImpl(@NonNull CreateOrderInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        EventBus.getDefault().register(this);

        mView.setupToolbar();
        mView.setupStepper();
        // Your code here. Your view is available using mView and will not be null until next onStop()
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollapseAppBarLayout(CollapseAppBarLayout collapseAppBarLayout) {
        mView.collapseAppBarLayout();
    }
}