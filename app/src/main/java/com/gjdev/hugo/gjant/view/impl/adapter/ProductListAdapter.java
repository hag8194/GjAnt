package com.gjdev.hugo.gjant.view.impl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hugo on 31/12/2016.
 * Project: GjAnt2
 */

public class ProductListAdapter extends BaseAdapter <ProductListAdapter.ViewHolder> {
    private List<Product> mProducts;
    private Picasso mPicasso;

    public ProductListAdapter(List<Product> products, Picasso picasso) {
        mProducts = products;
        mPicasso = picasso;
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
            mItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mProducts.get(position);
        String post_link = product.get_links().getPoster().getHref();

        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.valueOf(product.getPrice()));
        mPicasso.load(post_link)
                .into(holder.productPoster);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
