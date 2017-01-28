package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.api.event.client.ErrorClientRetrieve;
import com.gjdev.hugo.gjant.data.api.event.client.FailClientRetrieve;
import com.gjdev.hugo.gjant.data.api.event.client.SuccessClientRetrieve;
import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.view.ClientDetailView;

public interface ClientDetailPresenter extends BasePresenter<ClientDetailView> {

    void onSelectedClientWallet(SelectedClientWallet clientWallet);

    void onSuccessClientRetrieve(SuccessClientRetrieve retrieve);

    void onErrorClientRetrieve(ErrorClientRetrieve retrieve);

    void onFailClientRetrieve(FailClientRetrieve retrieve);
}