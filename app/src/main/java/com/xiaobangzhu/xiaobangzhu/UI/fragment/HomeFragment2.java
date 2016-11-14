package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xiaobangzhu.xiaobangzhu.Adapter.MyFragmentPagerAdapter;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.PixelUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * HomeFragment2
 *
 * @author: MurphySL
 * @time: 2016/11/4 15:59
 */


public class HomeFragment2 extends Fragment implements ViewPager.OnPageChangeListener{
    private static final String TAG = "HomeFragment";
    View mRootView;
    TabLayout mTabLayout;
    ViewPager tabPager;
    ArrayList<Fragment> tabFragments;
    Fragment newsListFragment,activityListFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabFragments = new ArrayList<>();
        newsListFragment = new NewsListFragment2();
        activityListFragment = new ActivityListFragment();

        tabFragments.add(newsListFragment);
        tabFragments.add(activityListFragment);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home2, container, false);
        initViews(mRootView);
        return mRootView;
    }

    /**
     * 初始化界面布局
     * @param mRootView
     */
    void initViews(View mRootView){

        mTabLayout = (TabLayout) mRootView.findViewById(R.id.home_tablayout);
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        tabPager = (ViewPager) mRootView.findViewById(R.id.home_tab_pager);
        tabPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),tabFragments));
        mTabLayout.setupWithViewPager(tabPager);
        tabPager.setCurrentItem(0);

        mTabLayout.getTabAt(0).setText(R.string.news_ground);
        mTabLayout.getTabAt(1).setText(R.string.campus_activity);

        try {
            setTabWidth(mTabLayout);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     *设置tab的宽度
     * @param tabWidth
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void setTabWidth(TabLayout tabWidth) throws NoSuchFieldException, IllegalAccessException  {
        Class<?> tablayout = mTabLayout.getClass();
        Field tabStrip = tablayout.getDeclaredField("mTabStrip");
        tabStrip.setAccessible(true);
        LinearLayout ll_tab= (LinearLayout) tabStrip.get(mTabLayout);
        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0,0,0,0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1);
            params.setMarginStart(PixelUtil.dp2px(55f));
            params.setMarginEnd(PixelUtil.dp2px(55f));
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}
