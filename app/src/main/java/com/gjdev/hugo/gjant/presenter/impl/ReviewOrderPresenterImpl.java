package com.gjdev.hugo.gjant.presenter.impl;

import android.support.annotation.NonNull;

import com.gjdev.hugo.gjant.data.api.event.enterprise.ErrorEnterpriseRetrieve;
import com.gjdev.hugo.gjant.data.api.event.enterprise.FailEnterpriseRetrieve;
import com.gjdev.hugo.gjant.data.api.event.enterprise.SuccessEnterpriseRetrieve;
import com.gjdev.hugo.gjant.data.api.model.Client;
import com.gjdev.hugo.gjant.data.api.model.Enterprise;
import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.data.event.NotifyOrderParams;
import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.data.event.ValidOrderForm;
import com.gjdev.hugo.gjant.data.sql.event.SuccessCartProductsRetrieve;
import com.gjdev.hugo.gjant.presenter.ReviewOrderPresenter;
import com.gjdev.hugo.gjant.util.Messages;
import com.gjdev.hugo.gjant.view.ReviewOrderView;
import com.gjdev.hugo.gjant.interactor.ReviewOrderInteractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

public final class ReviewOrderPresenterImpl extends BasePresenterImpl<ReviewOrderView> implements ReviewOrderPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ReviewOrderInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public ReviewOrderPresenterImpl(@NonNull ReviewOrderInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);
        EventBus.getDefault().register(this);

        User user = mInteractor.getUser();

        mInteractor.retrieveEnterprise();

        mView.setVendorData(new String[]{
            user.getEmployer().getName(),
            user.getEmployer().getLastname(),
            user.getEmployer().getIdentification()
        });

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
    public void onSuccessEnterpriseRetrieve(SuccessEnterpriseRetrieve retrieve) {
        Enterprise enterprise = retrieve.getEnterprise();
        mView.setEnterpriseData(new String[]{
                enterprise.getName(),
                enterprise.getRif(),
                enterprise.getPhone(),
                enterprise.getAddress()
        });
    }

    @Override
    @Subscribe( threadMode = ThreadMode.MAIN)
    public void onErrorEnterpriseRetrieve(ErrorEnterpriseRetrieve retrieve) {
        mView.showToast(Messages.errorMessage(retrieve.getApiError()));
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailEnterpriseRetrieve(FailEnterpriseRetrieve retrieve) {
        mView.showToast(Messages.failureMessage(retrieve.getThrowable()));
    }

    @Override
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onSelectedClientWallet(SelectedClientWallet selectedClientWallet) {
        Client selectedClient = selectedClientWallet.getClientWallet().getClient();
        mView.setClientData(new String[]{
                selectedClient.getFullname(),
                selectedClient.getIdentification(),
                selectedClient.getPhone1(),
                selectedClient.getPhone2().isEmpty() ? "No tiene segundo teléfono" : selectedClient.getPhone2(),
                selectedClient.getAddress().getName()
        });
    }

    @Override
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onValidOrderForm(ValidOrderForm validOrderForm) {
        mView.setOrderData(new String[]{
                mInteractor.getOrderType(validOrderForm.getTypePosition()),
                mInteractor.getDate().trim(),
                validOrderForm.getDescription().trim().equals("") ? "Sin descripción" : validOrderForm.getDescription()
        });
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNotifyOrderParams(NotifyOrderParams notifyOrderParams) {
        mView.setOrderCode(notifyOrderParams.getOrderCode());
        mView.setOrderTotal(notifyOrderParams.getOrderTotal());
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessCartProductsRetrieve(SuccessCartProductsRetrieve retrieve) {
        mView.setupRecyclerView(retrieve.getProducts());
    }
}