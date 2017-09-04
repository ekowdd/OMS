package com.example.odisys.oms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    private Bundle b;

    private ProgressDialog progress;

    private String tkn;
    private String usr;

    private Context context;
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
        b = this.getIntent().getExtras();
        context = this;
        pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {

        if (b.getString("type").equals("scanConfirm")){
            Toast.makeText(context, rawResult.getText(), Toast.LENGTH_LONG).show();
            SharedPreferences.Editor prefEdit = pref.edit();
            prefEdit.putString("scanresult", rawResult.getText());
            prefEdit.commit();
            finish();
        }
        finish();
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
