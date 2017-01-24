package com.gjdev.hugo.gjant.data.event;

/**
 * Created by Hugo on 20/01/2017.
 */

public class NotifyOrderParams {
    private String orderCode;
    private String orderTotal;

    public NotifyOrderParams(String orderCode, String orderTotal) {
        this.orderCode = orderCode;
        this.orderTotal = orderTotal;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public String getOrderTotal() {
        return orderTotal;
    }
}
