package com.example.odisys.oms.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.odisys.oms.R;
import com.example.odisys.oms.model.ReportFinishModel;

import java.util.List;


public class ReportFinishAdapter extends RecyclerView.Adapter<ReportFinishAdapter.ViewHolder> {
    private View mView;
    private List<ReportFinishModel> list;


    @Override
    public ReportFinishAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listreport,null);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ReportFinishAdapter.ViewHolder holder, int position) {
        holder.p_code.setText(list.get(position).getProduct_code());
        holder.p_name.setText(list.get(position).getProduct_name());
        holder.p_barcode.setText(list.get(position).getBarcode());
        holder.p_storeName.setText(list.get(position).getStore_name());
        holder.p_storeLead.setText(list.get(position).getStore_lead());
        holder.p_nik.setText(list.get(position).getNik());
        holder.p_namLengkap.setText(list.get(position).getNama());
        holder.p_tnaggal.setText(list.get(position).getTanggal());
        holder.p_jam.setText(list.get(position).getWaktu());
        holder.p_stok.setText(list.get(position).getQuantity());
        holder.p_tnaggal.setText(list.get(position).getTanggal());

        holder.p_hrgaA.setText(list.get(position).getValue());
        holder.p_total.setText(list.get(position).getTotal());

        holder.p_missed.setText(list.get(position).getMissed());
        holder.p_totalmissed.setText(list.get(position).getTotal_value_missed());

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView p_code,
                p_name,
                p_barcode,
                p_storeName,
                p_storeLead,
                p_nik,
                p_namLengkap,
                p_tnaggal,
                p_jam,
                p_stok,
                p_hrgaA,
                p_total,
                p_missed,
                p_totalmissed,
                p_rugi,
                p_bebanKaryawan;
        public ViewHolder(View itemView) {
            super(itemView);
            p_code = (TextView)itemView.findViewById(R.id.p_Code);
            p_name = (TextView)itemView.findViewById(R.id.p_Names);
            p_barcode = (TextView)itemView.findViewById(R.id.p_Barcode);
            p_storeName = (TextView)itemView.findViewById(R.id.p_StoreNames);
            p_storeLead = (TextView)itemView.findViewById(R.id.p_StoreLead);
            p_nik = (TextView)itemView.findViewById(R.id.p_NIK);
            p_namLengkap = (TextView)itemView.findViewById(R.id.p_NamaLengkap);
            p_tnaggal = (TextView)itemView.findViewById(R.id.p_Tanggal);
            p_jam = (TextView)itemView.findViewById(R.id.p_Jams);
            p_stok = (TextView)itemView.findViewById(R.id.p_Stok);
            p_hrgaA = (TextView)itemView.findViewById(R.id.p_Hargas);
            p_total = (TextView)itemView.findViewById(R.id.p_Totals);
            p_missed = (TextView)itemView.findViewById(R.id.p_Missed);
            p_totalmissed = (TextView)itemView.findViewById(R.id.p_TotalMissed);
            p_rugi = (TextView)itemView.findViewById(R.id.p_Rugi);
            p_bebanKaryawan = (TextView)itemView.findViewById(R.id.p_bebanKaryawan);
        }
    }
}
