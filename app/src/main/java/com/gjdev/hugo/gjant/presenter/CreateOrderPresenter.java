package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.api.event.createorder.ErrorCreateOrder;
import com.gjdev.hugo.gjant.data.api.event.createorder.FailCreateOrder;
import com.gjdev.hugo.gjant.data.api.event.createorder.SuccessCreateOrder;
import com.gjdev.hugo.gjant.data.event.CollapseAppBarLayout;
import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.data.event.ValidOrderForm;
import com.gjdev.hugo.gjant.data.sql.event.SuccessCartProductsRetrieve;
import com.gjdev.hugo.gjant.view.CreateOrderView;

public interface CreateOrderPresenter extends BasePresenter<CreateOrderView> {

    void onCollapseAppBarLayout(CollapseAppBarLayout collapseAppBarLayout);

    void onSelectedClientWallet(SelectedClientWallet selectedClientWallet);

    void onValidOrderForm(ValidOrderForm validOrderForm);

    void onSuccessCartProductsRetrieve(SuccessCartProductsRetrieve retrieve);

    void onSelectedReviewOrderStep();

    void onCompletedSteps();

    void onSuccessCreateOrder(SuccessCreateOrder successCreateOrder);

    void onErrorCreateOrder(ErrorCreateOrder errorCreateOrder);

    void onFailCreateOrder(FailCreateOrder failCreateOrder);

}