package com.example.storm.moviesinfo.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 10097 on 2017/3/26.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private Fragment[] fragments;
    private String[] titles;

    public TabAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    public void setPageTitle(String[] titles){
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles!=null){
            return titles[position];
        }
        return "";
    }
}
