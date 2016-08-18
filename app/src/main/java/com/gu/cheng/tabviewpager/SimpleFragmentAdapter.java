package com.gu.cheng.tabviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gc on 2016/8/18.
 */
public class SimpleFragmentAdapter extends FragmentPagerAdapter {

    private List<String> mTitles = new ArrayList<String>();

    public SimpleFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    public void refreshData(List<String> dataList){
        mTitles.clear();
        mTitles.addAll(dataList);
        notifyDataSetChanged();
    }
}
