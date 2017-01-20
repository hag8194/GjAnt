package com.gjdev.hugo.gjant.data.api.event.createorder;

import com.gjdev.hugo.gjant.data.api.model.response.CreatedResponseMessage;

/**
 * Created by Hugo on 04/01/2017.
 */

public class SuccessCreateOrder {
    private CreatedResponseMessage responseMessage;

    public SuccessCreateOrder(CreatedResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    public CreatedResponseMessage getResponseMessage() {
        return responseMessage;
    }
}
