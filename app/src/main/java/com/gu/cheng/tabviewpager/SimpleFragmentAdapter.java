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

    private List<String> mTitles;
    private List<Fragment> mFragments;

    public SimpleFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public SimpleFragmentAdapter(FragmentManager supportFragmentManager, ArrayList<String> mTabList,
                                 List<Fragment> fragments) {
        super(supportFragmentManager);
        this.mTitles = mTabList;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
//        if (position == 0){
//            return SwipeFragment.newInstance(position);
//        }
//        return PageFragment.newInstance(position);
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    public void refreshData(List<String> dataList,List<Fragment> fragments){
        mFragments = fragments;
        mTitles.clear();
        mTitles.addAll(dataList);
        notifyDataSetChanged();
    }
}
