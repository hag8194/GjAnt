package com.gjdev.hugo.gjant.view.impl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.gjdev.hugo.gjant.view.impl.OrderFormFragment;
import com.gjdev.hugo.gjant.view.impl.ReviewOrderFragment;
import com.gjdev.hugo.gjant.view.impl.SelectClientFragment;
import com.stepstone.stepper.adapter.AbstractStepAdapter;

/**
 * Created by Hugo on 12/01/2017.
 */

public class CreateOrderStepperAdapter extends AbstractStepAdapter {
    public CreateOrderStepperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment createStep(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new SelectClientFragment();
                break;
            case 1:
                fragment = new OrderFormFragment();
                break;
            case 2:
                fragment = new ReviewOrderFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
