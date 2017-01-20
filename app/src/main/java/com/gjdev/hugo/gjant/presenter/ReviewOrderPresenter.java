package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.event.NotifyOrderParams;
import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.data.event.ValidOrderForm;
import com.gjdev.hugo.gjant.data.sql.event.SuccessCartProductsRetrieve;
import com.gjdev.hugo.gjant.view.ReviewOrderView;

public interface ReviewOrderPresenter extends BasePresenter<ReviewOrderView> {

    void onSelectedClientWallet(SelectedClientWallet selectedClientWallet);

    void onValidOrderForm(ValidOrderForm validOrderForm);

    void onNotifyOrderParams(NotifyOrderParams notifyOrderParams);

    void onSuccessCartProductsRetrieve(SuccessCartProductsRetrieve retrieve);

}