package com.example.odisys.oms.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.odisys.oms.Api.ApiUrl;
import com.example.odisys.oms.R;

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

/**
 * Created by OKKI on 04/06/2017.
 */

public class StoreFragment extends android.support.v4.app.Fragment
        implements View.OnClickListener {
    private View view;
    private Spinner spinner;
    private Button cari;
    private Context context;
    private ProgressDialog mDialog;
    private ArrayList<String> list;
    ArrayAdapter<String> adapter;
    HttpURLConnection con;
    URL mUrl;
    String optToko = "";
    SharedPreferences mPref;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.store,container,false);
        spinner = (Spinner)view.findViewById(R.id.store_opt);
        cari = (Button)view.findViewById(R.id.submitStore);
        cari.setOnClickListener(this);
        new searchProductByStore().execute();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                optToko = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.getSelectedItem().equals("Select Store");
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = new ProductFragment();
        switch (v.getId()){
            case R.id.submitStore:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,fragment)
                        .commit();
                mPref = v.getContext().getSharedPreferences("share_store",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mPref.edit();
                editor.putString("store_name",optToko);
                editor.apply();
                break;
        }
    }

    private class searchProductByStore extends AsyncTask<Void, Void, String>{
        JSONObject obj;
        JSONArray arr;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showprogressDialog();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                mUrl = new URL(ApiUrl.URL_STORE);
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                con = (HttpURLConnection) mUrl.openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);
                con.setRequestMethod("GET");
            } catch (IOException ex) {
                ex.printStackTrace();
                return ex.toString();
            }
            try {
                int response_code = con.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream is = con.getInputStream();
                    BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = bf.readLine()) != null) {
                        sb.append(line);
                    }
                    return (sb.toString());
                } else {
                    return "unsuccessfull";
                }
            } catch (IOException exx) {
                exx.printStackTrace();
                return exx.toString();
            } finally {
                con.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String sb) {
            super.onPostExecute(sb);
            dissmissProgressDialog();
            Log.d("Response", sb);
            list = new ArrayList<>();
            try {
               obj= new JSONObject(sb);
                arr = obj.getJSONArray("msg");
                for (int i=0; i < arr.length();i++) {
                    JSONObject mObj = arr.getJSONObject(i);
                    String storeName = mObj.getString("store_name");
                    list.add(storeName);
                }



//                for (int i = 0; i < arr.length(); i++) {
//                    JSONObject mObj = arr.getJSONObject(i);
//                    Store pm = new Store();
//                    String nm_sore = pm.setStore_name(mObj.getString("store_name"));
//                    list.add(pm);
//                }
            } catch (Exception ec) {
                ec.printStackTrace();
            }
            adapter = new ArrayAdapter<>(view.getContext(),R.layout.support_simple_spinner_dropdown_item,list);
            spinner.setAdapter(adapter);
        }
    }

    private void showprogressDialog(){
        if (mDialog ==null){
            mDialog = new ProgressDialog(view.getContext());
            mDialog.setTitle(null);
            mDialog.setMessage("Loading....");
            mDialog.setCancelable(false);
        }
        mDialog.show();
    }

    private void dissmissProgressDialog(){
        if (mDialog !=null || mDialog.isShowing()){
            mDialog.dismiss();
        }
    }
}
