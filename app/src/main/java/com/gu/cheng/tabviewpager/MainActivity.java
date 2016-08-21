package com.gu.cheng.tabviewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;
    ArrayList<Fragment> mViewList;//Save each of the Page View
    ArrayList<String> mTabList;//Save each of the Tabs'Title

    LayoutInflater mLayoutInflater;

//    @BindView(R.id.id_gc_tab_layout)
    TabLayout mTabLayout;

//    @BindView(R.id.id_gc_view_pager)
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        unbinder = ButterKnife.bind(this);

        findViews();
        initList();

        //Add ViewList

//        View view_home = mLayoutInflater.inflate(R.layout.pager_home,null);
//        View view_info = mLayoutInflater.inflate(R.layout.page_information,null);
//        View view_intro = mLayoutInflater.inflate(R.layout.page_introduce,null);
//        mViewList.add(view_home);
//        mViewList.add(view_info);
//        mViewList.add(view_intro);

        //Add TabList

        mTabList.add("个人信息");
        mTabList.add("网易新闻");
        mTabList.add("娱乐资讯");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置标签的模式,默认系统模式

        //set the click event of Tag and change current page into the seleceted one

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());//点击哪个就跳转哪个界面
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //Add the tag elements
//        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(0)));
//        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(1)));
//        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(2)));

        //Bind the adapter with the mViewPager as well as mTablayout

//        CustomPagerAdapter myAdapter = new CustomPagerAdapter(mViewList, mTabList);


        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(SwipeFragment.newInstance(0));
        fragments.add(PageFragment.newInstance(1));
        fragments.add(PageFragment.newInstance(2));
        SimpleFragmentAdapter myAdapter = new SimpleFragmentAdapter(getSupportFragmentManager(),mTabList,fragments);
        mViewPager.setAdapter(myAdapter);
//        myAdapter.refreshData();

        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setTabsFromPagerAdapter(myAdapter);
    }

    private void initList(){
        mTabList = new ArrayList<String>();
        mViewList = new ArrayList<Fragment>();
    }

    private void findViews() {
        mTabLayout = (TabLayout) findViewById(R.id.id_gc_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.id_gc_view_pager);
        mLayoutInflater = LayoutInflater.from(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
    }
}
