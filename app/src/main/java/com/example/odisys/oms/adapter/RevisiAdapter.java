package com.example.odisys.oms.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.odisys.oms.R;
import com.example.odisys.oms.model.RevisiModel;

import java.util.List;

/**
 * Created by eko on 7/17/17.
 */

public class RevisiAdapter extends RecyclerView.Adapter<RevisiAdapter.ViewHolder> {
    private View view;
    private List<RevisiModel> list;
    private Context context;

    @Override
    public RevisiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listreport,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RevisiAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView trans_code,
                revisi_id,
                product_code,
                barcode,
                product_name,
                store_name,
                store_lead,
                nik,
                nama,
                tanggal,
                waktu,
                quantity,
                value,
                total,
                missed,
                total_value_missed;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
