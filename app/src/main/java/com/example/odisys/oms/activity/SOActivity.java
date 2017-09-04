package com.example.odisys.oms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.odisys.oms.Api.ApiUrl;
import com.example.odisys.oms.R;
import com.example.odisys.oms.adapter.ProductFrontAdapter;
import com.example.odisys.oms.model.ProductModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.odisys.oms.R.id.txtScan;

public class SOActivity extends AppCompatActivity implements View.OnClickListener{
    View view;
    ImageView mImageView;
    Context mContext;
    EditText mText;
    String TAG = "PRODUCT FRAGMENT";
    private static int CAMERA_REQEST = 0;
    SharedPreferences mPreferences;
    ProgressDialog mDialog;
    String mBarcode = "";
    HttpURLConnection con;
    URL mUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so);
        mText = (EditText)findViewById(txtScan);
        mPreferences = getSharedPreferences("scanresult", Context.MODE_PRIVATE);
        mBarcode = mPreferences.getString("scanresult",null );
        mText.setText(mBarcode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSharedPreferences("pref", Context.MODE_PRIVATE).contains("scanresult")) {
            mText.setText(getSharedPreferences("pref", Context.MODE_PRIVATE).getString("scanresult", ""));

            this.getSharedPreferences("pref", Context.MODE_PRIVATE)
                    .edit()
                    .remove("scanresult")
                    .apply();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQEST) {
            if (resultCode == RESULT_OK) {
                String context = data.getStringExtra("scanresult");
                Log.d("MainActivity", context);

                mText.setText(context);
            } else {
                Log.e("MainActivity <:>", "Error while reading");
                Toast.makeText(mContext, "Some Error Occured", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent i = new Intent(SOActivity.this, ScannerActivity.class);
            i.putExtra("type", "scanConfirm");
            startActivityForResult(i, CAMERA_REQEST);
        }
        return super.onOptionsItemSelected(item);

    }

    private void submitSO(){
        final String nama_lengkap = "";
        final String product_code = "";
        final String product_name = "";
        final String store_name = "";
        final String store_lead = "";
        final String barcode ="";
        final String tanggal = "";
        final String jam     = "";
        final String nik = "";
        final String qty = "";
        final String value ="";
        final String total = "";
        final StringRequest mReq = new StringRequest(Request.Method.POST, ApiUrl.URL_SO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismissProgressDialog();
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> doPostSo = new HashMap<>();
                doPostSo.put("nama_lengkap",nama_lengkap);
                doPostSo.put("product_code",product_code);
                doPostSo.put("product_name",product_name);
                doPostSo.put("barcode",barcode);
                doPostSo.put("store_name", store_name);
                doPostSo.put("Store_lead",store_lead);
                doPostSo.put("tanggal",tanggal);
                doPostSo.put("jam",jam);
                doPostSo.put("nik",nik);
                doPostSo.put("qty",qty);
                doPostSo.put("value", value);
                doPostSo.put("total",total);
                return doPostSo;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(mReq);
    }

    public class Product extends AsyncTask<Void, Void, String> {
        JSONObject obj;
        JSONArray arr;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected String doInBackground(Void... params) {
            String url = "";
            try {

                mUrl = new URL(ApiUrl.URL_PRODUCT_SO_GET + "?barcode="+mBarcode);
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
        protected void onPostExecute(String sb) {
            super.onPostExecute(sb);
            dismissProgressDialog();
            Log.d("Response", sb);
            try {
                obj = new JSONObject(sb);
                arr = obj.getJSONArray("msg");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject mObj = arr.getJSONObject(i);
                    ProductModel pm = new ProductModel();
                    mObj.getString("product_code");
                    mObj.getString("product_name");
                    mObj.getString("barcode");
                    mObj.getString("quantity");
                    mObj.getString("value");
                    mObj.getString("total");
                    mObj.getString("type");
                    mObj.getString("image");

                }
            } catch (Exception ec) {
                ec.printStackTrace();
            }
        }
    }

    private void showProgressDialog(){
        if (mDialog == null){
            mDialog = new ProgressDialog(this);
            mDialog.setTitle(null);
            mDialog.setMessage("Loading....");
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
