package com.example.odisys.oms.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OKKI on 27/06/2017.
 */

public class PagerAdaper extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentsList = new ArrayList<>();
    private final List<String> mFraListTitle = new ArrayList<>();
    public PagerAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentsList.size();
    }

    public void addFragment(Fragment fragment, String title){
        mFragmentsList.add(fragment);
        mFraListTitle.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFraListTitle.get(position);
    }
}
