package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import com.gjdev.hugo.gjant.data.api.model.Zone;

import java.util.List;

@UiThread
public interface MapView {

    void setupActivity();

    void setupMapFragment();

    void setupCamera(Zone zone);

    void addMarkers(List<ClientWallet> clientWalletList);

}