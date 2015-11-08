package com.doudou.cn.android60demo.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doudou.cn.android60demo.R;
import com.doudou.cn.android60demo.model.ViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jinliang on 15/11/5.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>

{
    private List<ViewModel> items;
    private Context context ;
    public RecyclerViewAdapter(Context context,List<ViewModel> items) {
        this.context =context;
        this.items = items;
    }

    // 创建viewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewModel model = items.get(position);
        holder.text.setText(model.getText());
        Picasso.with(context).load(model.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.msg);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
