package com.gjdev.hugo.gjant.interactor.impl;

import javax.inject.Inject;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.ApiService;
import com.gjdev.hugo.gjant.data.api.event.product.ErrorProductRetrieve;
import com.gjdev.hugo.gjant.data.api.event.product.FailProductRetrieve;
import com.gjdev.hugo.gjant.data.api.event.product.SuccessProductRetrieve;
import com.gjdev.hugo.gjant.data.event.NotifyChangeOfFragment;
import com.gjdev.hugo.gjant.data.event.NotifyProductCartStatus;
import com.gjdev.hugo.gjant.data.event.SelectedProduct;
import com.gjdev.hugo.gjant.data.api.model.ApiError;
import com.gjdev.hugo.gjant.data.api.model.Children;
import com.gjdev.hugo.gjant.data.api.model.Product;
import com.gjdev.hugo.gjant.data.api.model.User;
import com.gjdev.hugo.gjant.data.sql.model.DaoSession;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;
import com.gjdev.hugo.gjant.data.sql.model.SQLProductDao;
import com.gjdev.hugo.gjant.interactor.ProductDetailInteractor;
import com.gjdev.hugo.gjant.util.ApiErrorHandler;
import com.gjdev.hugo.gjant.util.InternalStorageHandler;
import com.gjdev.hugo.gjant.util.Messages;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class ProductDetailInteractorImpl implements ProductDetailInteractor {
    private ApiService mApiService;
    private ApiErrorHandler mApiErrorHandler;
    private DaoSession mDaoSession;
    private InternalStorageHandler mInternalStorageHandler;
    private Product product;

    @Inject
    public ProductDetailInteractorImpl(ApiService apiService, ApiErrorHandler apiErrorHandler,
                                       DaoSession daoSession, InternalStorageHandler internalStorageHandler) {
        mApiService = apiService;
        mApiErrorHandler = apiErrorHandler;
        mDaoSession = daoSession;
        mInternalStorageHandler = internalStorageHandler;
    }

    @Override
    public void retrieveProductDetail(int id) {
        User user = (User)mInternalStorageHandler.readObject(R.string.user_data);

        Call<Product> productCall = mApiService.product(id, user.getAccessToken());

        if(product == null){
            productCall.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if(response.isSuccessful()) {
                        product = response.body();
                        postEvent(SUCCESS_EVENT, product);
                    }
                    else
                        postEvent(ERROR_EVENT, mApiErrorHandler.parseError(response));
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    postEvent(FAILURE_EVENT, t);
                }
            });
        }
        else
            postEvent(SUCCESS_EVENT, product);
    }

    @Override
    public Children getChildren(int position) {
        return product.getChildren().get(position);
    }

    @Override
    public void postSelectedChildren(int id) {
        EventBus.getDefault().postSticky(new SelectedProduct(id));
    }

    @Override
    public void addProductToCart(int productQuantity) {
        SQLProductDao sqlProductDao = mDaoSession.getSQLProductDao();
        String message = Messages.ProductCart.NO_ACTION_MESSAGE;

        List<SQLProduct> productList = sqlProductDao.queryBuilder()
                .where(SQLProductDao.Properties.Key.eq(product.getId()))
                .list();

        if(productList.isEmpty()) {
            sqlProductDao.insert(new SQLProduct(null, product.getId(),
                    product.get_links().getPoster().getHref(),product.getName(), product.getPrice(), productQuantity));
            message = Messages.ProductCart.SUCCESS_MESSAGE;
        }
        else {
            SQLProduct item =  productList.remove(0);
            if(productQuantity != item.getQuantity()) {
                item.setKey(product.getId());
                item.setPoster_url(product.get_links().getPoster().getHref());
                item.setName(product.getName());
                item.setPrice(product.getPrice());
                item.setQuantity(productQuantity);

                sqlProductDao.update(item);
                message = Messages.ProductCart.UPDATE_MESSAGE;
            }
        }

        EventBus.getDefault().post(new NotifyProductCartStatus(message));
    }

    @Override
    public void postEvent(int kindOfEvent, Object object) {
        EventBus eventBus = EventBus.getDefault();
        switch (kindOfEvent) {
            case FAILURE_EVENT:
                eventBus.post(new FailProductRetrieve((Throwable) object));
                break;
            case SUCCESS_EVENT:
                eventBus.post(new SuccessProductRetrieve((Product) object));
                break;
            case ERROR_EVENT:
                eventBus.post(new ErrorProductRetrieve((ApiError) object));
                break;
        }
    }
}