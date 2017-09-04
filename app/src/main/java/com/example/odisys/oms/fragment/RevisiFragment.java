package com.example.odisys.oms.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odisys.oms.R;

/**
 * Created by Odisys on 06/05/2017.
 */

public class RevisiFragment extends Fragment {
    private View mView;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.revisi_main,null);
        return mView;
    }
}
