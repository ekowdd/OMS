package com.example.odisys.oms.scsnner;

/**
 * Created by ODI-PC05 on 3/16/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CameraActivity extends Activity implements ZXingScannerView.ResultHandler{
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
//        Toast.makeText(context, b.getString("type"), Toast.LENGTH_LONG).show();
//        pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
//        tkn = this.getSharedPreferences("pref", Context.MODE_PRIVATE).getString("bearer", "");
//        usr = this.getSharedPreferences("pref", Context.MODE_PRIVATE).getString("usercode", "");
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
        // Do something with the result here
//        Log.v("", rawResult.getText()); // Prints scan results
//        Log.v("", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

//        Intent i = new Intent(this, LabelActivity.class);
//        i.putExtra("type", b.getString("type"));
//        i.putExtra("brcd", rawResult.getText());
//        i.putExtra("mfsno", b.getString("mfsno"));
//        startActivity(i);
        if (b.getString("type").equals("scanConfirm")){
            Toast.makeText(context, rawResult.getText(), Toast.LENGTH_LONG).show();
            SharedPreferences.Editor prefEdit = pref.edit();
            prefEdit.putString("scanresult", rawResult.getText());
            prefEdit.commit();
            finish();
        }
        finish();
//        Fragment fragment = null;
//        FragmentManager fragmentManager = getSupportFragmentManager();
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
