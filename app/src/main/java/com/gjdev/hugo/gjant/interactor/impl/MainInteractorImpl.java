package com.gjdev.hugo.gjant.interactor.impl;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.model.User;
import com.gjdev.hugo.gjant.interactor.MainInteractor;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;

public final class MainInteractorImpl implements MainInteractor {

    private InternalStorageHandler mInternalStorageHandler;

    @Inject
    public MainInteractorImpl(InternalStorageHandler internalStorageHandler) {
        mInternalStorageHandler = internalStorageHandler;
    }

    @Override
    public User getUser() {
        return (User)mInternalStorageHandler.readObject(R.string.user_data);
    }

    @Override
    public boolean deleteUserFileData() {
        return mInternalStorageHandler.deleteObject(R.string.user_data);
    }
}