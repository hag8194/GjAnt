package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.event.CollapseAppBarLayout;
import com.gjdev.hugo.gjant.view.CreateOrderView;

public interface CreateOrderPresenter extends BasePresenter<CreateOrderView> {
    void onCollapseAppBarLayout(CollapseAppBarLayout collapseAppBarLayout);
}