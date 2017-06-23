package com.example.odisys.oms.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.odisys.oms.R;
import com.example.odisys.oms.model.ProductModel;

import java.util.ArrayList;

/**
 * Created by Odisys on 25/02/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private View view;
    private Context context;
    private int layoutresourceId;
    ArrayList<ProductModel> data = new ArrayList<>();

    public ProductAdapter(View view, Context context, int layoutresourceId, ArrayList<ProductModel> data) {
        this.view = view;
        this.context = context;
        this.layoutresourceId = layoutresourceId;
        this.data = data;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name,product_code;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
