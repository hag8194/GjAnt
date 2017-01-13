package com.gjdev.hugo.gjant.interactor.impl;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.interactor.ReviewOrderInteractor;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

public final class ReviewOrderInteractorImpl implements ReviewOrderInteractor {
    private ApiService mApiService;
    private ApiErrorHandler mApiErrorHandler;
    private InternalStorageHandler mInternalStorageHandler;

    @Inject
    public ReviewOrderInteractorImpl(ApiService mApiService, ApiErrorHandler mApiErrorHandler,
                                     InternalStorageHandler mInternalStorageHandler) {
        this.mApiService = mApiService;
        this.mApiErrorHandler = mApiErrorHandler;
        this.mInternalStorageHandler = mInternalStorageHandler;
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {

    }
}