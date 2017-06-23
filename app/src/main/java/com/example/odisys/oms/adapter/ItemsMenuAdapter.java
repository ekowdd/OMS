package com.example.odisys.oms.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.odisys.oms.R;
import com.example.odisys.oms.model.ItemsMenuAModels;

import java.util.List;

/**
 * Created by Odisys on 25/02/2017.
 */

public class ItemsMenuAdapter extends RecyclerView.Adapter<ItemsMenuAdapter.ViewHolder> {
    private List<ItemsMenuAModels> obj;
    private Context context;
    private RecyclerView.OnItemTouchListener listener;

    public ItemsMenuAdapter(Context context, List<ItemsMenuAModels> obj) {
        this.obj = obj;
        this.context = context;
        this.listener = listener;
    }


    @Override
    public ItemsMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listmenu,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemsMenuAdapter.ViewHolder holder, int position) {
        holder.icon.setImageResource(obj.get(position).getIcon());
        holder.title.setText(obj.get(position).getTitle());
        holder.desc.setText(obj.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return obj.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView title, desc;
        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icons);
            title = (TextView) itemView.findViewById(R.id.titles);
            desc = (TextView) itemView.findViewById(R.id.desc);
        }
    }

}
