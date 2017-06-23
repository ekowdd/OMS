package com.example.odisys.oms.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.odisys.oms.R;
import com.example.odisys.oms.model.ProductModel;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Odisys on 20/05/2017.
 */

public class ProductFrontAdapter extends RecyclerView.Adapter<ProductFrontAdapter.ViewHolder> {
    private View mView;
    private Context context;
    private View.OnClickListener listener;
    private List<ProductModel> list = new ArrayList<ProductModel>();

    public ProductFrontAdapter( Context mContext,List<ProductModel> list) {
        this.list = list;
        this.context = mContext;
    }


    @Override
    public ProductFrontAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product,null);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ProductFrontAdapter.ViewHolder holder, int position) {

        Picasso.with(mView.getContext())
                .load(list.get(position).getImgs())
                .placeholder(R.drawable.circle_k)
                .resize(800,600)
                .into(holder.mImg);
//        holder.mImg.setImageResource(list.get(position).getImgs());
        holder.productName.setText(list.get(position).getProduct_name());
        holder.productCode.setText(list.get(position).getProduct_code());

    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }else {
            return list.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView productName, productCode;
        public ViewHolder(View itemView) {
            super(itemView);
            mImg = (ImageView)itemView.findViewById(R.id.image_v);
            productName = (TextView)itemView.findViewById(R.id.pName);
            productCode = (TextView)itemView.findViewById(R.id.pCode);
        }

    }

    @SuppressLint("ObsoleteSdkInt")
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }
}
