package com.example.odisys.oms.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odisys.oms.Api.ApiUrl;
import com.example.odisys.oms.R;
import com.example.odisys.oms.model.ReportFinishModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by OKKI on 25/06/2017.
 */

@SuppressLint("ValidFragment")
public class ReportFinishFragment extends Fragment {
    private View view;
    private Context context;
    private List<ReportFinishModel> list;
    private ProgressDialog mDialog;
    private ShoAllData shoAllData;
    HttpURLConnection con;
    URL mUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.report_finish,container,false);
//        new ShoAllData().execute((Void)null);
        return view;
    }

    private class ShoAllData extends AsyncTask<Void, Void, String>{
        private String status = "";
        private String response = "";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgrssDialog();
        }

        @Override
        protected String doInBackground(Void... params) {
            String url = "";
            try {

                mUrl = new URL(ApiUrl.URL_PRODUCT + "?store_name=");
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                con = (HttpURLConnection) mUrl.openConnection();
                url = mUrl.getQuery();
                url.replaceAll("","%20");
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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dismissProgressDialog();
        }
    }

    private void showProgrssDialog(){
        if (mDialog == null){
            mDialog = new ProgressDialog(view.getContext());
            mDialog.setTitle(null);
            mDialog.setMessage("Loading.....");
            mDialog.setCancelable(false);
        }
        mDialog.show();
    }

    private void dismissProgressDialog(){
        if (mDialog !=null || mDialog.isShowing()){
            mDialog.dismiss();
        }
    }
}
