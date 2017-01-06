package com.gjdev.hugo.gjant.interactor;

import com.gjdev.hugo.gjant.data.model.Children;

public interface ProductDetailInteractor extends BaseInteractor {

    void retrieveProductDetail(int id);

    Children getChildren(int position);

    void postSelectedChildren(int id);
}