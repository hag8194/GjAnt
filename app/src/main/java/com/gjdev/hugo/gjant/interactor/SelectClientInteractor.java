package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import com.gjdev.hugo.gjant.data.event.ClickedClientWalletListItem;

import java.util.List;

public interface SelectClientInteractor extends BaseInteractor {

    void retrieveClientWallet();

    ClientWallet getClientWallet(int position);

    void saveSelectedClientWalletPosition(int adapterPosition);

    int retrieveSelectedClientWalletPosition();
}