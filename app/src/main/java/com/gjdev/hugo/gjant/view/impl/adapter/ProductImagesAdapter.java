package com.gjdev.hugo.gjant.view.impl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.event.SelectProductImage;
import com.gjdev.hugo.gjant.data.model.ProductImages;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hugo on 05/01/2017.
 */

public class ProductImagesAdapter extends BaseAdapter<ProductImagesAdapter.ViewHolder> {
    private List<ProductImages> productImagesList;

    public ProductImagesAdapter(List<ProductImages> productImagesList) {
        this.productImagesList = productImagesList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.product_image)
        ImageView productImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

            if(getAdapterPosition() == 0)
                itemView.setSelected(true);
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new SelectProductImage(productImage.getDrawable()));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_images_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(holder.itemView.getContext())
                .load(productImagesList.get(position).getPath())
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return productImagesList.size();
    }
}
