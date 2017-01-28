package com.gjdev.hugo.gjant.interactor.impl;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.data.api.event.clientwallet.ErrorClientWalletRetrieve;
import com.gjdev.hugo.gjant.data.api.event.clientwallet.FailClientWalletRetrieve;
import com.gjdev.hugo.gjant.data.api.event.clientwallet.SuccessClientWalletRetrieve;
import com.gjdev.hugo.gjant.data.api.model.ApiError;
import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.data.event.ClickedClientWalletListItem;
import com.gjdev.hugo.gjant.data.event.SelectedClientWallet;
import com.gjdev.hugo.gjant.interactor.HomeInteractor;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class HomeInteractorImpl implements HomeInteractor {
    private ApiService mApiService;
    private ApiErrorHandler mApiErrorHandler;
    private InternalStorageHandler mInternalStorageHandler;

    private List<ClientWallet> clientWalletList;

    @Inject
    public HomeInteractorImpl(ApiService mApiService, ApiErrorHandler mApiErrorHandler,
                              InternalStorageHandler mInternalStorageHandler) {
        this.mApiService = mApiService;
        this.mApiErrorHandler = mApiErrorHandler;
        this.mInternalStorageHandler = mInternalStorageHandler;
    }

    @Override
    public void retrieveClientWallet() {
        User user = (User)mInternalStorageHandler.readObject(R.string.user_data);
        Call<List<ClientWallet>> clientWalletCall = mApiService.clientWallet(user.getAccessToken(), user.getEmployer().getId());

        if(clientWalletList == null) {
            clientWalletCall.enqueue(new Callback<List<ClientWallet>>() {
                @Override
                public void onResponse(Call<List<ClientWallet>> call, Response<List<ClientWallet>> response) {
                    if(response.isSuccessful()){
                        clientWalletList = response.body();
                        postEvent(SUCCESS_EVENT, null);
                    }
                    else
                        postEvent(ERROR_EVENT, mApiErrorHandler.parseError(response));
                }

                @Override
                public void onFailure(Call<List<ClientWallet>> call, Throwable t) {
                    postEvent(FAILURE_EVENT, t);
                }
            });
        }
        else
            postEvent(SUCCESS_EVENT, null);
    }

    @Override
    public void postSelectedClientWalletList(ClickedClientWalletListItem listItem) {
        ClientWallet clientWallet = clientWalletList.get(listItem.getAdapterPosition());
        EventBus.getDefault().postSticky(new SelectedClientWallet(clientWallet));
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {
        EventBus eventBus = EventBus.getDefault();
        switch (kindOfEvent) {
            case FAILURE_EVENT:
                eventBus.post(new FailClientWalletRetrieve((Throwable) object));
                break;
            case SUCCESS_EVENT:
                eventBus.post(new SuccessClientWalletRetrieve(clientWalletList));
                break;
            case ERROR_EVENT:
                eventBus.post(new ErrorClientWalletRetrieve((ApiError) object));
                break;
        }
    }
}