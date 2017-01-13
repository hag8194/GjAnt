package com.gjdev.hugo.gjant.data.event;

/**
 * Created by Hugo on 08/01/2017.
 */

public class NotifyChangeOfFragment {
    public static final int PRODUCT_DETAIL_FRAGMENT = 0;
    public static final int CART_FRAGMENT = 1;
    public static final int CREATE_ORDER_FRAGMENT = 2;

    private int fragment;

    public NotifyChangeOfFragment(int fragment) {
        this.fragment = fragment;
    }

    public int getFragment() {
        return fragment;
    }
}
