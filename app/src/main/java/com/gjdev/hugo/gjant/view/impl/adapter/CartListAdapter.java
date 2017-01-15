package com.gjdev.hugo.gjant.view.impl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.event.ClickedProductListItem;
import com.gjdev.hugo.gjant.data.event.LongClickedProductListItem;
import com.gjdev.hugo.gjant.data.sql.model.SQLProduct;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hugo on 07/01/2017.
 */

public class CartListAdapter extends BaseAdapter <CartListAdapter.ViewHolder> {
    private List<SQLProduct> mProducts;

    public CartListAdapter(List<SQLProduct> mProducts) {
        this.mProducts = mProducts;
    }

    static class ViewHolder extends ProductListAdapter.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.product_poster)
        ImageView productPoster;

        @BindView(R.id.product_name)
        TextView productName;

        @BindView(R.id.product_price)
        TextView productPrice;

        @BindView(R.id.product_quantity)
        TextView productQuantity;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new ClickedProductListItem(v, getAdapterPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            EventBus.getDefault().post(new LongClickedProductListItem(v, getAdapterPosition()));
            return true;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart_list, parent, false);

        return new CartListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SQLProduct product = mProducts.get(position);
        String poster_url = product.getPoster_url();

        Picasso.with(holder.itemView.getContext())
                .load(poster_url)
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.productPoster);

        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.valueOf(product.getPrice()));
        holder.productQuantity.setText(String.valueOf(product.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
