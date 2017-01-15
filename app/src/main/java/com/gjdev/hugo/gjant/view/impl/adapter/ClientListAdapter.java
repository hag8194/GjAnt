package com.gjdev.hugo.gjant.view.impl.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import com.gjdev.hugo.gjant.data.event.ClickedClientWalletListItem;
import com.gjdev.hugo.gjant.util.RoundedTransformation;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hugo on 12/01/2017.
 */

public class ClientListAdapter extends BaseAdapter<ClientListAdapter.ViewHolder> {
    private List<ClientWallet> mClientWalletList;

    public ClientListAdapter(List<ClientWallet> mClientWalletList) {
        this.mClientWalletList = mClientWalletList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.client_avatar)
        ImageView client_avatar;

        @BindView(R.id.client_fullname)
        TextView client_fullname;

        @BindView(R.id.select_client)
        ImageView add_image;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            add_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new ClickedClientWalletListItem(v, getAdapterPosition()));
                    Log.d(this.getClass().getName(), String.valueOf(getAdapterPosition()));
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_client_list, parent, false);

        return new ClientListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClientWallet clientWallet = mClientWalletList.get(position);

        holder.client_fullname.setText(clientWallet.getClient().getFullname());
        Picasso.with(holder.itemView.getContext())
                .load(clientWallet.getClient().get_links().getPoster().getHref())
                .transform(new RoundedTransformation())
                .into(holder.client_avatar);
    }

    @Override
    public int getItemCount() {
        return mClientWalletList.size();
    }
}
