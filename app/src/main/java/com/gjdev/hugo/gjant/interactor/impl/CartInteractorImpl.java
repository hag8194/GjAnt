package com.gjdev.hugo.gjant.interactor.impl;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.data.event.SelectedProduct;
import com.gjdev.hugo.gjant.data.sql.event.SuccessCartProductsRetrieve;
import com.gjdev.hugo.gjant.data.sql.model.DaoSession;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;
import com.gjdev.hugo.gjant.interactor.CartInteractor;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public final class CartInteractorImpl implements CartInteractor {
    private DaoSession mDaoSession;
    private List<SQLProduct> mProductList;

    @Inject
    public CartInteractorImpl(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

    @Override
    public void retrieveProductsInCart() {
        if(mProductList == null) {
            mProductList = mDaoSession.getSQLProductDao().queryBuilder().list();
            postEvent(SUCCESS_EVENT, null);
        }
        else
            postEvent(SUCCESS_EVENT, null);
    }

    @Override
    public SQLProduct getProduct(int position) {
        return mProductList.get(position);
    }

    @Override
    public void postSelectedProduct(int id) {
        EventBus.getDefault().postSticky(new SelectedProduct(id));
    }

    @Override
    public void removeCartItem(SQLProduct product) {
        mProductList.remove(product);
        mDaoSession.getSQLProductDao().delete(product);
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {
        EventBus eventBus = EventBus.getDefault();
        switch (kindOfEvent){
            case SUCCESS_EVENT:
                eventBus.post(new SuccessCartProductsRetrieve(mProductList));
                break;
        }
    }
}