package com.example.odisys.oms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
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
import com.example.odisys.oms.R;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScrollingActivity extends AppCompatActivity{
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView img;
    private String url = "";
    private TextView jam, taggal, keterangan;
    private Calendar mCalendar;
    private int jams, tanggals;
    private Handler handler;
    private View view;
    private ZXingScannerView mScannerView;
    private Bundle b;

    private ProgressDialog progress;

    private String tkn;
    private String usr;

    private EditText barcodes;
    private Context context;
    SharedPreferences pref;
    private static int CAMERA_REQEST = 0;
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
        view = (View) findViewById(R.id.view_scan);

        barcodes = (EditText)findViewById(R.id.barcodes);
        pref = getSharedPreferences("scanresult", Context.MODE_PRIVATE);
        String mBarcode = pref.getString("scanresult",null );
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScrollingActivity.this, ScannerActivity.class);
                i.putExtra("type", "scanConfirm");
                startActivityForResult(i, CAMERA_REQEST);
            }
        });
//        keterangan = (TextView)findViewById(R.id.keterangan);
//        keterangan.setMovementMethod(new ScrollingMovementMethod());
        handler.postDelayed(runnable, 1000);
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
        StringRequest mRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> doPost = new HashMap<>();
                doPost.put("", "");
                doPost.put("", "");
                doPost.put("", "");
                doPost.put("", "");
                doPost.put("", "");
                doPost.put("", "");
                doPost.put("", "");
                doPost.put("", "");
                doPost.put("", "");
                doPost.put("", "");
                doPost.put("", "");
                doPost.put("", "");
                return doPost;
            }
        };
        RequestQueue mQueue = Volley.newRequestQueue(this);
        mQueue.add(mRequest);
    }

    public void showLoadingDialog() {
        if (progress == null) {
            progress = new ProgressDialog(context);
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
}
