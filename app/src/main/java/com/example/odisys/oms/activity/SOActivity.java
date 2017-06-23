package com.example.odisys.oms.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.odisys.oms.R;

import static com.example.odisys.oms.R.id.txtScan;

public class SOActivity extends AppCompatActivity implements View.OnClickListener{
    View view;
    ImageView mImageView;
    Context mContext;
    EditText mText;
    String TAG = "PRODUCT FRAGMENT";
    private static int CAMERA_REQEST = 0;
    SharedPreferences mPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so);
        mText = (EditText)findViewById(txtScan);
        mPreferences = getSharedPreferences("scanresult", Context.MODE_PRIVATE);
        String mBarcode = mPreferences.getString("scanresult",null );
//        mText.setText(mBarcode);
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
}
