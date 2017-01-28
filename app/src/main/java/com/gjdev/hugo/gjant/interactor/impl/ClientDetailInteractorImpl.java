package com.gjdev.hugo.gjant.interactor.impl;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.data.api.event.client.ErrorClientRetrieve;
import com.gjdev.hugo.gjant.data.api.event.client.FailClientRetrieve;
import com.gjdev.hugo.gjant.data.api.event.client.SuccessClientRetrieve;
import com.gjdev.hugo.gjant.data.api.model.ApiError;
import com.gjdev.hugo.gjant.data.api.model.Client;
import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.interactor.ClientDetailInteractor;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class ClientDetailInteractorImpl implements ClientDetailInteractor {
    private ApiService mApiService;
    private ApiErrorHandler mApiErrorHandler;
    private InternalStorageHandler mInternalStorageHandler;

    private Client client;

    @Inject
    public ClientDetailInteractorImpl(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                      InternalStorageHandler internalStorageHandler) {
        mApiService = apiService;
        mApiErrorHandler = apiErrorHandler;
        mInternalStorageHandler = internalStorageHandler;
    }

    @Override
    public void retrieveClient(int id) {
        User user = (User)mInternalStorageHandler.readObject(R.string.user_data);

        Call<Client> productCall = mApiService.client(id, user.getAccessToken());

        if(client == null){
            productCall.enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    if(response.isSuccessful()) {
                        client = response.body();
                        postEvent(SUCCESS_EVENT, client);
                    }
                    else
                        postEvent(ERROR_EVENT, mApiErrorHandler.parseError(response));
                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {
                    postEvent(FAILURE_EVENT, t);
                }
            });
        }
        else
            postEvent(SUCCESS_EVENT, client);
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {
        EventBus eventBus = EventBus.getDefault();
        switch (kindOfEvent) {
            case FAILURE_EVENT:
                eventBus.post(new FailClientRetrieve((Throwable) object));
                break;
            case SUCCESS_EVENT:
                eventBus.post(new SuccessClientRetrieve((Client) object));
                break;
            case ERROR_EVENT:
                eventBus.post(new ErrorClientRetrieve((ApiError) object));
                break;
        }
    }
}