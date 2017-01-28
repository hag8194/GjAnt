package com.gjdev.hugo.gjant.data.api.event.orders;

import com.gjdev.hugo.gjant.data.api.model.Order;

import java.util.List;

/**
 * Created by Hugo on 04/01/2017.
 */

public class SuccessOrdersRetrieve {
    private List<Order> orderList;

    public SuccessOrdersRetrieve(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
