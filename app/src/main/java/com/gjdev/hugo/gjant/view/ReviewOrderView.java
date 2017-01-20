package com.gjdev.hugo.gjant.view;

import android.support.annotation.UiThread;

import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;

import java.util.List;

@UiThread
public interface ReviewOrderView {

    void setOrderData(String data[]);

    void setVendorData(String data[]);

    void setClientData(String data[]);

    void setupRecyclerView(List<SQLProduct> products);
}