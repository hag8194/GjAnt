package com.gjdev.hugo.gjant.view.impl.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.model.Order;
import com.gjdev.hugo.gjant.data.api.model.OrderDetail;
import com.gjdev.hugo.gjant.data.api.model.Product;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;
import com.gjdev.hugo.gjant.util.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by Hugo on 27/01/2017.
 */

public class OrderListAdapter extends BaseAdapter<OrderListAdapter.ViewHolder> {
    private List<Order> mOrderList;
    private int mExpandedPosition;

    public OrderListAdapter(List<Order> mOrderList) {
        this.mOrderList = mOrderList;
        mExpandedPosition = -1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.order_code, R.id.order_created_at, R.id.order_description,
                R.id.order_type, R.id.order_status, R.id.client_fullname})
        List<TextView> orderData;

        @BindView(R.id.client_avatar)
        ImageView client_avatar;

        @BindView(R.id.expand_button)
        ImageButton expandButton;

        @BindView(R.id.product_list)
        RecyclerView mRecyclerView;

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
        final boolean isExpanded = position == mExpandedPosition;
        final int pos = position;

        Order order = mOrderList.get(position);
        Context context = holder.itemView.getContext();
        String[] type = context.getResources().getStringArray(R.array.order_type);

        Picasso.with(context).load(order.getClientWallet().getClient().get_links().getPoster().getHref())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .transform(new RoundedTransformation())
                .into(holder.client_avatar);
        DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance();
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-4"));

        holder.setOrderData(new String[]{
            order.getCode(),
            dateFormat.format(order.getCreated_at()* 1000L),
            order.getDescription().equals("") ? "Sin descripción" : order.getDescription(),
            type[order.getType() + 1],
            String.valueOf(order.getStatus()),
            order.getClientWallet().getClient().getFullname()
        });

        holder.mRecyclerView.setHasFixedSize(true);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.mRecyclerView.setAdapter(new CartListAdapter(this.generateProductListData(order.getOrderDetails())));
        holder.mRecyclerView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        holder.itemView.setActivated(isExpanded);
        holder.expandButton.setImageResource(isExpanded ? R.drawable.ic_keyboard_arrow_up_black_24dp :
                R.drawable.ic_keyboard_arrow_down_black_24dp);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1: pos;
                notifyDataSetChanged();
            }
        });

        holder.expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1: pos;
                notifyDataSetChanged();
            }
        });
    }

    private List<SQLProduct> generateProductListData(List<OrderDetail> orderDetails){
        List<SQLProduct> products = new ArrayList<>();

        for (int i = 0; i < orderDetails.size(); i++) {
            OrderDetail orderDetail = orderDetails.get(i);
            Product product = orderDetail.getProduct();
            products.add(new SQLProduct(
                    (long) i,
                    product.getId(),
                    product.get_links().getPoster().getHref(),
                    product.getName(),
                    product.getPrice(),
                    orderDetail.getQuantity()));
        }

        return products;
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }
}
