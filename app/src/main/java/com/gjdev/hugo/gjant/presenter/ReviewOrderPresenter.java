package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.view.ReviewOrderView;

public interface ReviewOrderPresenter extends BasePresenter<ReviewOrderView> {
    void onSelectedClientWallet(SelectedClientWallet selectedClientWallet);
}