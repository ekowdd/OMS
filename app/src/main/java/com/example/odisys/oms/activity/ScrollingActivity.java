package com.example.odisys.oms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.odisys.oms.Api.ApiUrl;
import com.example.odisys.oms.Api.WebServicesHelpers;
import com.example.odisys.oms.R;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ScrollingActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView img;
    private String url = "";
    private TextView jam, taggal, p_nams,pCodes;
    private EditText barcode,qty;
    private View view_scan;
    private Button Submit;
    private Calendar mCalendar;
    private int jams, tanggals;
    private Handler handler;
    private View view;
    private ZXingScannerView mScannerView;
    private Bundle b;

    private ProgressDialog progress;

    private String tkn;
    private String usr;

    private TextView barcodes;
    private Context context;
    SharedPreferences pref;
    String trans_code = "";
    String revisi_id = "";
    String product_code = "";
    String product_name = "";
    String store_name = "";
    String store_lead = "";
    String bCode = "";
    String nik = "";
    String nama = "";
    String email = "";
    String uStatus = "";
    String tanggal = "";
    String waktu = "";
    String quantity = "";
    int value = 0;
    String nama_file = "";
    int total = 0;
    int missed = 0;
    int total_value_missed = 0;
    private static int CAMERA_REQEST = 0;
    private HttpURLConnection con;
    private URL mUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Toko Kita");

        handler = new Handler();
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        img = (ImageView) findViewById(R.id.imgs);

        jam = (TextView) findViewById(R.id.jam);
        taggal = (TextView) findViewById(R.id.tanggal);
        barcodes = (TextView) findViewById(R.id.barcodes);
        p_nams = (TextView)findViewById(R.id.p_Name);
        pCodes = (TextView)findViewById(R.id.p_Code);
        qty = (EditText)findViewById(R.id.quantity);
        pref = getSharedPreferences("scanresult", Context.MODE_PRIVATE);
        String mBarcode = pref.getString("scanresult", null);
//        keterangan = (TextView)findViewById(R.id.keterangan);
//        keterangan.setMovementMethod(new ScrollingMovementMethod());
        handler.postDelayed(runnable, 1000);
        new getDataUser().execute((Void)null);
        Submit = (Button)findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitSo();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSharedPreferences("pref", Context.MODE_PRIVATE).contains("scanresult")) {
            barcodes.setText(getSharedPreferences("pref", Context.MODE_PRIVATE).getString("scanresult", ""));

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

                barcodes.setText(context);
            } else {
                Log.e("MainActivity <:>", "Error while reading");
                Toast.makeText(this, "Some Error Occured", Toast.LENGTH_LONG).show();
            }
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mCalendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
            SimpleDateFormat tFormat = new SimpleDateFormat("h:m:s a");
            String time = tFormat.format(mCalendar.getTime());
            String date = dateFormat.format(mCalendar.getTime());
            taggal.setText(date);
            jam.setText(time);
            handler.postDelayed(runnable, 1000);
        }

    };


    public void SubmitSo() {
        final String qtty = qty.getText().toString();
         tanggal = taggal.getText().toString();
         waktu = jam.getText().toString();
         quantity =  qtty;
         total= (int)(Integer.parseInt(qtty) * value);
         missed = (int)(Integer.parseInt(qtty) -  Integer.parseInt(qtty));
         total_value_missed = missed * value;

        StringRequest mRequest = new StringRequest(Request.Method.POST, ApiUrl.URL_SO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    dismissLoadingDialog();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showLoadingDialog();
                Log.d("Error :", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> doPost = new HashMap<>();
                doPost.put("product_code", product_code);
                doPost.put("product_name", product_name);
                doPost.put("barcode", bCode);
                doPost.put("store_name", "");
                doPost.put("quantity", qtty);
                doPost.put("value", String.valueOf(value));
                doPost.put("total", String.valueOf(total));
                doPost.put("nama_lengkap", nama);
                doPost.put("tanggal", tanggal);
                doPost.put("waktu", waktu);
                doPost.put("total", String.valueOf(total));
                doPost.put("missed", String.valueOf(missed));
                doPost.put("total_value_missed", String.valueOf(total_value_missed));
                doPost.put("image", nama_file);
                return doPost;
            }
        };
        RequestQueue mQueue = Volley.newRequestQueue(this);
        mQueue.add(mRequest);
    }

    private class CekDataBarang extends AsyncTask<Void, Void,String>{
        String status = "";
        String response = "";
        JSONObject obj;
        JSONArray arr;
        String mBarcode = barcodes.getText().toString();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoadingDialog();
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                RequestBody body = new FormBody.Builder()
                        .add("barcode", mBarcode)
                        .build();
                response = WebServicesHelpers.doPost(ApiUrl.JOIN_PRODUCT_STORE,body,"");
                obj = new JSONObject(response);
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
            dismissLoadingDialog();
            if (status.equals("OK")){
                try {
                    obj = new JSONObject(response);
                    arr = obj.getJSONArray("msg");
                    for (int i=0; i<arr.length();i++){
                        JSONObject mObj = arr.getJSONObject(i);
                        p_nams.setText(mObj.getString("product_name"));
                        pCodes.setText(mObj.getString("product_code"));
                        barcodes.setText(mObj.getString("barcode"));

                        product_code = mObj.getString("product_code");
                        product_name = mObj.getString("product_name");
                        bCode = mObj.getString("product_code");
                        nama_file = mObj.getString("nama_file");
                        store_name = mObj.getString("store_name");
                        store_lead = mObj.getString("store_lead");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    private class getDataUser extends AsyncTask<Void, Void, String>{
        SharedPreferences mshared = getSharedPreferences("isLoggedIn", Context.MODE_PRIVATE);
        String niks =mshared.getString("nik", null);
        String status = "";
        String response = "";
        JSONObject obj;
        JSONArray arr;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoadingDialog();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                RequestBody body = new FormBody.Builder()
                        .add("nik",niks)
                        .build();
                response = WebServicesHelpers.doGet(ApiUrl.GET_USER + "?nik="+niks,"");
                obj = new JSONObject(response);
                status = "OK";
            } catch (Exception exx) {
                status = "NOK";
                exx.printStackTrace();
            }
            return status;
        }

        @Override
        protected void onPostExecute(String sb) {
            super.onPostExecute(sb);
            dismissLoadingDialog();
            if (sb.equals("OK")){
                try {
                    obj = new JSONObject(sb);
                    arr = obj.getJSONArray("msg");
                    for (int i = 0; i < arr.length();i++) {
                        JSONObject mObj = arr.getJSONObject(i);
                        nik = mObj.getString("nik");
                        nama = mObj.getString("nama_lengkap");
                        email = mObj.getString("email");
                        uStatus = mObj.getString("status");
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void showLoadingDialog() {
        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setTitle(null);
            progress.setCancelable(false);
            progress.setMessage("Loading...");
        }
        progress.show();
    }

    public void dismissLoadingDialog() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.camera:
                Intent i = new Intent(ScrollingActivity.this, ScannerActivity.class);
                i.putExtra("type", "scanConfirm");
                startActivityForResult(i, CAMERA_REQEST);
                break;
            case R.id.search:
                new CekDataBarang().execute((Void)null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
