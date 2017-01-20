package com.gjdev.hugo.gjant.view.impl.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.view.impl.OrderFormFragment;
import com.gjdev.hugo.gjant.view.impl.ReviewOrderFragment;
import com.gjdev.hugo.gjant.view.impl.SelectClientFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.adapter.AbstractStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

/**
 * Created by Hugo on 12/01/2017.
 */

public class CreateOrderStepperAdapter extends AbstractFragmentStepAdapter {
    public CreateOrderStepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(@IntRange(from = 0L) int position) {
        Step stepFragment = null;
        switch (position){
            case 0:
                stepFragment = new SelectClientFragment();
                break;
            case 1:
                stepFragment = new OrderFormFragment();
                break;
            case 2:
                stepFragment = new ReviewOrderFragment();
                break;
        }

        return stepFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        int stringRes = R.string.app_name;
        switch (position){
            case 0:
                stringRes = R.string.select_client;
                break;
            case 1:
                stringRes = R.string.complete_form;
                break;
            case 2:
                stringRes = R.string.review_order;
                break;
        }
        return new StepViewModel.Builder(context)
                .setTitle(stringRes) //can be a CharSequence instead
                .create();
    }
}
