package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.event.ClickedClientWalletListItem;

public interface HomeInteractor extends BaseInteractor {

    void retrieveClientWallet();

    void postSelectedClientWalletList(ClickedClientWalletListItem listItem);

}