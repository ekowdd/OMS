package com.example.odisys.oms.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.odisys.oms.Api.ApiUrl;
import com.example.odisys.oms.Api.WebServicesHelpers;
import com.example.odisys.oms.R;
import com.example.odisys.oms.adapter.GridViewAdapter;
import com.example.odisys.oms.adapter.ProductFrontAdapter;
import com.example.odisys.oms.model.ItemsMenuAModels;
import com.example.odisys.oms.model.ProductModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by Odisys on 25/02/2017.
 */

public class ProductFragment extends Fragment{
    private Context context;
    Activity activity;
    ProductFrontAdapter adapter;
    private List<ItemsMenuAModels> obj;
    private List<ProductModel> list;
    GridLayoutManager grid;
    //    private final GridView gridView;
    RecyclerView recyclerView;
    private ProgressDialog mDialog;
    SwipeRefreshLayout mRefreshLayout;
    postProduct mProduct;
    String url = "http://192.168.23.3/oms2/index.php/";
    HttpURLConnection con;
    URL mUrl;
    private ImageView img;
    View.OnClickListener listener;
    private GridViewAdapter mGridAdapter;
    private ArrayList<ProductModel> mGridData;
    private GridView mGridView;
    private View view;
    SharedPreferences pref;
    String store ="";
    public ProductFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.product_main, container, false);
        grid = new GridLayoutManager(view.getContext(),2);
        recyclerView = (RecyclerView)view.findViewById(R.id.rcyc_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lsm = new LinearLayoutManager(getContext());
        lsm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(grid);
        adapter = new ProductFrontAdapter(getContext(),list);
        new postProduct().execute((Void)null);
        pref = view.getContext().getSharedPreferences("share_store",Context.MODE_PRIVATE);
        store = pref.getString("store_name",null);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new postProduct().execute((Void)null);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

//    public class Product extends AsyncTask<Void, Void, String> {
//        JSONObject obj;
//        JSONArray arr;
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            showProgresDialog();
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//            String url = "";
//            try {
//
//                mUrl = new URL(ApiUrl.URL_PRODUCT + "?store_name="+store);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return e.toString();
//            }
//            try {
//                con = (HttpURLConnection) mUrl.openConnection();
//                url = mUrl.getQuery();
//                url.replaceAll("","%20");
//                con.setConnectTimeout(15000);
//                con.setReadTimeout(10000);
//                con.setRequestMethod("GET");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                return ex.toString();
//            }
//            try {
//                int response_code = con.getResponseCode();
//                if (response_code == HttpURLConnection.HTTP_OK) {
//                    InputStream is = con.getInputStream();
//                    BufferedReader bf = new BufferedReader(new InputStreamReader(is));
//                    StringBuilder sb = new StringBuilder();
//                    String line;
//                    while ((line = bf.readLine()) != null) {
//                        sb.append(line);
//                    }
//                    return (sb.toString());
//                } else {
//                    return "unsuccessfull";
//                }
//            } catch (IOException exx) {
//                exx.printStackTrace();
//                return exx.toString();
//            } finally {
//                con.disconnect();
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String sb) {
//            super.onPostExecute(sb);
//            dismissProgresDialog();
//            Log.d("Response", sb);
//            list = new ArrayList<ProductModel>();
//            try {
//                obj = new JSONObject(sb);
//                arr = obj.getJSONArray("msg");
//                for (int i = 0; i < arr.length(); i++) {
//                    JSONObject mObj = arr.getJSONObject(i);
//                    ProductModel pm = new ProductModel();
//                    pm.setProduct_code(mObj.getString("product_code"));
//                    pm.setProduct_name(mObj.getString("product_name"));
//                    pm.setBarcode(mObj.getString("barcode"));
//                    pm.setQty(mObj.getString("quantity"));
//                    pm.setValue_s(mObj.getString("value"));
//                    pm.setTotal(mObj.getString("total"));
//                    pm.setType(mObj.getString("type"));
//                    pm.setNama_file(mObj.getString("nama_file"));
//                    pm.setImgs(mObj.getString("image"));
////                    pm.setProduct_code(list.get(i).getProduct_code());
////                    pm.setProduct_name(list.get(i).getProduct_name());
////                    pm.setBarcode(list.get(i).getBarcode());
////                    pm.setQty(list.get(i).getQty());
////                    pm.setValue_s(list.get(i).getValue_s());
////                    pm.setTotal(list.get(i).getTotal());
////                    pm.setType(list.get(i).getType());
////                    pm.setImgs(list.get(i).getImgs());
////                    Picasso.with(context).load(list.get(i).getImgs()).resize(120,60).into(img);
//                    list.add(pm);
//                }
//            } catch (Exception ec) {
//                ec.printStackTrace();
//            }
//            grid = new GridLayoutManager(view.getContext(),3);
//            adapter = new ProductFrontAdapter(context,list);
//            recyclerView.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
//        }
//    }


    private class postProduct extends AsyncTask<Void, Void, String>{
        String status = "";
        String response = "";
        JSONObject object;
        JSONArray array;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgresDialog();
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                list = new ArrayList<>();
                RequestBody body = new FormBody.Builder()
                        .add("store_name",store)
                        .build();
                response = WebServicesHelpers.doPost(ApiUrl.URL_PRODUCT,body,"");
                object = new JSONObject(response);
                status = "OK";
            }catch (Exception e){
                status = "NOK";
                e.printStackTrace();
            }
            return status;
        }

        @Override
        protected void onPostExecute(String status) {
            super.onPostExecute(status);
            dismissProgresDialog();
            if (status.equals("OK")){
                try {
                    object = new JSONObject(response);
                    array = object.getJSONArray("msg");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject mObj = array.getJSONObject(i);
                        ProductModel pm = new ProductModel();
                        pm.setProduct_code(mObj.getString("product_code"));
                        pm.setProduct_name(mObj.getString("product_name"));
                        pm.setBarcode(mObj.getString("barcode"));
                        pm.setQty(mObj.getString("quantity"));
                        pm.setValue_s(mObj.getString("value"));
                        pm.setTotal(mObj.getString("total"));
                        pm.setType(mObj.getString("type"));
                        pm.setNama_file(mObj.getString("nama_file"));
                        pm.setImgs(mObj.getString("image"));
//                    pm.setProduct_code(list.get(i).getProduct_code());
//                    pm.setProduct_name(list.get(i).getProduct_name());
//                    pm.setBarcode(list.get(i).getBarcode());
//                    pm.setQty(list.get(i).getQty());
//                    pm.setValue_s(list.get(i).getValue_s());
//                    pm.setTotal(list.get(i).getTotal());
//                    pm.setType(list.get(i).getType());
//                    pm.setImgs(list.get(i).getImgs());
//                    Picasso.with(context).load(list.get(i).getImgs()).resize(120,60).into(img);
                        list.add(pm);
                    }
                } catch (Exception ec) {
                    ec.printStackTrace();
                }
                grid = new GridLayoutManager(view.getContext(),1);
                adapter = new ProductFrontAdapter(context,list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    }
        private void showProgresDialog() {
            if (mDialog == null) {
                mDialog = new ProgressDialog(getContext());
                mDialog.setTitle(null);
                mDialog.setCancelable(false);
                mDialog.setMessage("Waiting.....");
            }
            mDialog.show();
        }

        private void dismissProgresDialog() {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
        }

}
