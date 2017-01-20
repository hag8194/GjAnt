package com.gjdev.hugo.gjant.presenter;

import com.gjdev.hugo.gjant.view.OrderFormView;
import com.stepstone.stepper.VerificationError;

public interface OrderFormPresenter extends BasePresenter<OrderFormView> {

    void onHasError(VerificationError error);

    void onNextClicked(int selectedItemPosition, String description);
}