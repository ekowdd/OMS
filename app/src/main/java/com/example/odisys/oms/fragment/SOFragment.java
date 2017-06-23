package com.example.odisys.oms.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.odisys.oms.R;

import static android.app.Activity.RESULT_OK;
import static com.example.odisys.oms.R.id.txtScan;

/**
 * Created by Odisys on 08/03/2017.
 */

public class SOFragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView mImageView;
    Context mContext;
    EditText mText;
    String TAG = "PRODUCT FRAGMENT";
    private static int CAMERA_REQEST = 0;
    SharedPreferences mPreferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.so_main, container, false);
        mText = (EditText)view.findViewById(txtScan);
        mPreferences = mContext.getSharedPreferences("scanresult", Context.MODE_PRIVATE);
        String mBarcode = mPreferences.getString("scanresult",null );
        mText.setText(mBarcode);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mContext.getSharedPreferences("pref", Context.MODE_PRIVATE).contains("scanresult")) {
            mText.setText(mContext.getSharedPreferences("pref", Context.MODE_PRIVATE).getString("scanresult", ""));

            mContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
                    .edit()
                    .remove("scanresult")
                    .apply();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
        switch (v.getId()){
//            case R.id.btnScan:
//                Intent mIntent = new Intent(getActivity(), CameraActivity.class);
//                startActivityForResult(mIntent, CAMERA_REQUEST);
//                break;
        }
    }


}
