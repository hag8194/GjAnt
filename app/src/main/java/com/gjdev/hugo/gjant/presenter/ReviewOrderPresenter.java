package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.api.event.enterprise.ErrorEnterpriseRetrieve;
import com.gjdev.hugo.gjant.data.api.event.enterprise.FailEnterpriseRetrieve;
import com.gjdev.hugo.gjant.data.api.event.enterprise.SuccessEnterpriseRetrieve;
import com.gjdev.hugo.gjant.data.event.NotifyOrderParams;
import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.data.event.ValidOrderForm;
import com.gjdev.hugo.gjant.data.sql.event.SuccessCartProductsRetrieve;
import com.gjdev.hugo.gjant.view.ReviewOrderView;

public interface ReviewOrderPresenter extends BasePresenter<ReviewOrderView> {

    void onSuccessEnterpriseRetrieve(SuccessEnterpriseRetrieve retrieve);

    void onErrorEnterpriseRetrieve(ErrorEnterpriseRetrieve retrieve);

    void onFailEnterpriseRetrieve(FailEnterpriseRetrieve retrieve);

    void onSelectedClientWallet(SelectedClientWallet selectedClientWallet);

    void onValidOrderForm(ValidOrderForm validOrderForm);

    void onNotifyOrderParams(NotifyOrderParams notifyOrderParams);

    void onSuccessCartProductsRetrieve(SuccessCartProductsRetrieve retrieve);

}