package com.gjdev.hugo.gjant.view.impl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.model.Order;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by Hugo on 27/01/2017.
 */

public class OrderListAdapter extends BaseAdapter<OrderListAdapter.ViewHolder> {
    private List<Order> mOrderList;

    public OrderListAdapter(List<Order> mOrderList) {
        this.mOrderList = mOrderList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.order_code, R.id.order_type, R.id.order_status})
        List<TextView> orderData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOrderData(String [] data){
            for (int i = 0; i < data.length; i++)
                orderData.get(i).setText(data[i]);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_list, parent, false);

        return new OrderListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order order = mOrderList.get(position);
        holder.setOrderData(new String[]{
            order.getCode(),
            String.valueOf(order.getType()),
            String.valueOf(order.getStatus())
        });
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }
}
