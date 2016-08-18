package com.gu.cheng.tabviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

//页卡适配器类
public class CustomPagerAdapter extends PagerAdapter {
    private ArrayList<View>mViewList;
    private ArrayList<String> mTabList;
    public CustomPagerAdapter(ArrayList<View>viewList,ArrayList<String>tabList)
    {
        this.mTabList = tabList;
        this.mViewList = viewList;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }


    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }
}