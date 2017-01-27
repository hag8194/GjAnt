package com.gjdev.hugo.gjant.data.api.event.enterprise;

import com.gjdev.hugo.gjant.data.api.model.Enterprise;

/**
 * Created by Hugo on 04/01/2017.
 */

public class SuccessEnterpriseRetrieve {
    private Enterprise enterprise;

    public SuccessEnterpriseRetrieve(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }
}
