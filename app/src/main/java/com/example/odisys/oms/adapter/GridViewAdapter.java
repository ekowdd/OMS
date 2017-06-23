package com.example.odisys.oms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.odisys.oms.R;
import com.example.odisys.oms.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Odisys on 24/05/2017.
 */

public class GridViewAdapter extends ArrayAdapter<ProductModel> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<ProductModel> mGridData = new ArrayList<ProductModel>();

    public GridViewAdapter(Context mContext, int layoutResourceId, ArrayList<ProductModel> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    /**
     * Updates grid data and refresh grid items.
     * @param mGridData
     */
    public void setGridData(ArrayList<ProductModel> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
//            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.listproduct, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.pName);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ProductModel item = mGridData.get(position);
        holder.titleTextView.setText(item.getProduct_name());
        Picasso.with(getContext()).load(item.getImgs()).resize(800,600).into(holder.imageView);
//        Picasso.with(mContext).load(item.getImgs()).resize(800,600).into(holder.imageView);
        return row;
    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
    }
}
