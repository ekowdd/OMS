package com.example.odisys.oms.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odisys.oms.R;

/**
 * Created by Odisys on 06/05/2017.
 */

public class RevisiFragment extends Fragment {
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.revisi_main,null);
        return mView;
    }
}
