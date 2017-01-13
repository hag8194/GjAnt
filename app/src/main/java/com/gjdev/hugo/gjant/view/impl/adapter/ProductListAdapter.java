package com.gjdev.hugo.gjant.view.impl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.event.ClickedProductListItem;
import com.gjdev.hugo.gjant.data.api.model.Product;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hugo on 31/12/2016.
 * Project: GjAnt2
 */

public class ProductListAdapter extends BaseAdapter <ProductListAdapter.ViewHolder> {
    private List<Product> mProducts;

    public ProductListAdapter(List<Product> products) {
        mProducts = products;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.product_poster)
        ImageView productPoster;

        @BindView(R.id.product_name)
        TextView productName;

        @BindView(R.id.product_price)
        TextView productPrice;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new ClickedProductListItem(v, getAdapterPosition()));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mProducts.get(position);
        String post_link = product.get_links().getPoster().getHref();

        Picasso.with(holder.itemView.getContext())
                .load(post_link)
                .into(holder.productPoster);

        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
