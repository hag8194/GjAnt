package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.data.api.event.clientwallet.SuccessClientWalletRetrieve;
import com.gjdev.hugo.gjant.view.MapView;

public interface MapPresenter extends BasePresenter<MapView> {

    void onMapReady();

    void onSuccessClientWalletRetrieve(SuccessClientWalletRetrieve retrieve);

}